package ru.starline.dozer;

import android.Manifest;
import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import static java.lang.Math.log10;
import static java.lang.Math.round;

public class FullscreenActivity extends AppCompatActivity  {
    public DrawAll DA = new DrawAll();
    public Handler h;
    public Props PP;
    public ImageView mainImage, historyDoze, cursorImage;
    public Button connIndicator;
    public int HSize, WSize;
    public double oldCounts = 0, specrtCRC;
    public getBluetooth BT;
    public BluetoothGatt gatt;
    public TextView textStatistic1, textStatistic2, textStatistic3, textStatistic4;
    public boolean connected = false;
    drawHistogram DH = new drawHistogram();
    public byte[] spectrData = new byte[4096];
    public int findDataSize = 210, firstCanal = 12, maxCanal = 2055;
    public float[] findData = new float[findDataSize], foneData = new float[4096], resultData = new float[4096];
    private View mContentView;
    private intervalTimer tmFull = new intervalTimer();
    public int histogramFlag = 1, smoothSpecter = 0, smoothWindow = 15;
    public String TAG = "!!!!! BLE report : ", FLAG = "", foneFlName = "";
    public int startFlag = 0, bufferIndex = 0, foneActive = 0;
    public float curentTime, tmpTime;
    public float tmpFindData, Trh1 = 40, Trh2 = 100;
    public double correctA, correctB, correctC, backgtoundTime;
    public double koeffR = (double) 0.5310015898;
    //public String MAC = "20:07:12:18:74:9E";
    //public String MAC = "20:06:03:20:02:A9";
    //public String MAC = "20:06:03:20:02:B3";
    //public String MAC = "20:06:12:09:74:3E"; // F103
    //public String MAC = "A4:C1:38:05:49:8E";
    public String defMAC = "20:06:11:11:66:CD", MAC = ""; // L412

    private void formatLayout() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //actionBar.hide();
            if (mContentView != null) {
                mContentView.setSystemUiVisibility(
                          View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                );
            }
        }
    }

    // Run setup activity
    public void setupActivity() {
        Intent intent = new Intent(this, FullscreenActivity2.class);
        startActivityForResult(intent, 2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if((data != null) && (requestCode == 2)) {
            byte[] resData = {0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,0};
            for ( int jj = 0; jj < 4; jj++) {
                for (int i = 0; i < 2; i++) {
                    resData[1 - i + jj * 2] = ByteBuffer.allocate(4).putInt(data.getIntArrayExtra("CFGDATA1")[jj]).get(i + 2);
                }
            }

            for (int i = 0; i < 4; i++) {
                resData[i + 8] = ByteBuffer.allocate(4).putFloat(data.getFloatExtra("CFGDATA5", 1)).get(i);
            }

            /* Update MAC address */
            String tmpMAC;
            tmpMAC = data.getStringExtra("CFGDATA6");
            if ( ! tmpMAC.equals(MAC)) {
                MAC = tmpMAC;
                if (connected) {
                    connected = false;
                    BT.destroyDevice();
                }
            }

            /* Load background radiation file */
            foneActive = data.getIntExtra ("CFGDATA8", 0);
            if (foneActive > 0) {
                foneFlName = data.getStringExtra("CFGDATA7");
                if (!foneFlName.isEmpty()) {
                    readBackgroundFile(foneFlName);
                }
            }
            /* Smoothing specter */
            smoothSpecter = data.getIntExtra ("CFGDATA9", 0);
            String tmpStr = data.getStringExtra("CFGDATA10");  // Smooth window size
            if (tmpStr.isEmpty()) {
                smoothWindow = 15;
            } else {
                smoothWindow = Integer.parseInt(tmpStr);
            }

            Log.d("DoZer", "onActivityResult: " + resultCode
                    + " CFGDATA1: " + resData[1] + resData[0]
                    + " CFGDATA2: " + resData[3] + resData[2]
                    + " CFGDATA3: " + resData[5] + resData[4]
                    + " CFGDATA4: " + resData[7] + resData[6]
                    + " CFGDATA5: " + resData[11] + resData[10] + resData[9] + resData[8]
                    + " CFGDATA6: " + MAC
                    + " CFGDATA7: " + foneFlName
                    + " CFGDATA8: " + foneActive
            );

            if (resultCode == 1) {
                try {
                    DA.sendCfg(resData);
                } catch (IOException e) {
                    Log.d("DoZer", "Error send config: " + e.getMessage());
                }
            }
        }
    }


    // Change main screen
    public void selectTypeScreen() {
        final Button gistoBtn = findViewById(R.id.gistoBtn);
        if ( histogramFlag == 1) {
            gistoBtn.setText(getResources().getString(R.string.histiryBtn));
            histogramFlag = 0;
        } else {
            gistoBtn.setText(getResources().getString(R.string.gistogramBtn));
            histogramFlag = 1;
        }
    }

    // Callback request permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 200) {
            if (permissions.length > 0 && grantResults.length > 0) {
                boolean flg = true;
                for (int ii = 0; ii < grantResults.length; ii++) {
                    if (grantResults[ii] != PackageManager.PERMISSION_GRANTED) {
                        flg = false;
                    }
                }
                if (flg) {
                    initApplication();
                } else {
                    finish();
                }
            } else {
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /* Load background data from file */
    public void readBackgroundFile(String flname) {
        File bgFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DoZer/" + flname);
        if(bgFile.exists()) {
            try {
                BufferedReader fonBuf = new BufferedReader(new FileReader(bgFile));
                String tmpStr;
                int foneIdx = 0;
                /* Clear background data */
                for ( int ii = 0; ii < 4096; ii++) {
                    foneData[ii] = 0;
                }
                while ((tmpStr = fonBuf.readLine()) != null) {
                    if (tmpStr.indexOf("<DataPoint>") >= 0 ) {
                        tmpStr = tmpStr.replace("<DataPoint>", "").replace("</DataPoint>", "");
                        foneData[foneIdx++] = Integer.parseInt(tmpStr);
                    } else {
                        if (tmpStr.indexOf("<MeasurementTime>") >= 0) { // Measurment time value
                            tmpStr = tmpStr.replace("<MeasurementTime>", "").replace("</MeasurementTime>", "");
                            backgtoundTime = Integer.parseInt(tmpStr);
                            Log.d("DoZer", "backgtoundTime: " + backgtoundTime);
                        }
                    }
                }
                float sm;
                /* Сглаживание методом скользящего простого среднего */
                int windSmooth = 0;
                if (windSmooth > 1) {
                    smoothArray(foneData, windSmooth, foneIdx);
                }
                Log.d("DoZer", "Load foneData idx: " + foneIdx);
                fonBuf.close();
            } catch (IOException e) {
                Toast.makeText(getBaseContext(), "Error read bgFile with: " + e.getMessage() , Toast.LENGTH_LONG).show();
            }
        } else {
            foneActive = 0;
            Toast.makeText(getBaseContext(), "File: " + bgFile.getAbsolutePath() + " not found." , Toast.LENGTH_LONG).show();
        }
    }

    /*
        Smoothing array
    */
    public void smoothArray(float[] arr, int win, int sz) {
        if (win > 2) {
            float[] tmpArr = new float[sz];
            float sm;
            for (int i = 0; i < sz - win / 2; i++) {
                sm = 0;
                for (int k = 0; k < win; k++) {
                    sm = sm + arr[i + k];
                }
                tmpArr[i] = sm / win;
            }
            for ( int i = 0; i < sz - win; i++) {
                arr[i + (int) win / 2] = tmpArr[i];
            }
        }
    }

    @SuppressLint({"ClickableViewAccessibility", "HandlerLeak"})
    public void initApplication() {
        //
        //  Read configuration
        //
        PP = new Props();
        MAC = PP.readProp("MAC");
        if (MAC == null) {
            MAC = defMAC;
        } else {
            MAC = MAC.toUpperCase();
        }

        // For calculate radiation for pulses
        String kR = PP.readProp("koefR");
        if (kR != null && ! kR.isEmpty()) {
            koeffR = Double.parseDouble(kR);
        }
        // Correction coefficients
        kR = PP.readProp("Correct_A");
        if (kR != null && ! kR.isEmpty()) {
            correctA = Double.parseDouble(kR);
        } else {
            correctA = 0;
        }
        kR = PP.readProp("Correct_B");
        if (kR != null && ! kR.isEmpty()) {
            correctB = Double.parseDouble(kR);
        } else {
            correctB = 1;
        }
        kR = PP.readProp("Correct_C");
        if (kR != null && ! kR.isEmpty()) {
            correctC = Double.parseDouble(kR);
        } else {
            correctC = 0;
        }

        kR = PP.readProp("BgActive");
        if (kR != null && ! kR.isEmpty()) {
            foneActive = Integer.parseInt(kR);
            if (foneActive > 0 ) {
                kR = PP.readProp("BgrdFlName");
                if (kR != null && ! kR.isEmpty()) {
                    readBackgroundFile(kR);
                } else {
                    foneActive = 0;
                }
            }
        } else {
            foneActive = 0;
        }

        kR = PP.readProp("smoothSpectr");
        if (kR != null && ! kR.isEmpty()) {
            smoothSpecter = Integer.parseInt(kR);
        } else {
            smoothSpecter = 0;
        }

        Log.d("DoZer", "koefR: " + koeffR);
        Log.d("DoZer", "MAC: " + MAC);
        if (MAC.isEmpty()) {
            MAC = defMAC;
        }
        BT = new getBluetooth();
        BT.initLeDevice();
        tmFull.startTimer();

        mainImage.setOnTouchListener((v, event) -> {
            float x = event.getX();
            float y = event.getY();
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                DA.drawCursor(x, y);
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                DA.drawCursor(x, y);
            }
            return true;
        });

        // Log button
        final Button logBtn = findViewById(R.id.logBtn);
        if (logBtn != null) {
            logBtn.setOnClickListener(v -> {
                /*
                Log.d("DoZer", "Pressed Log.");
                Log.d(TAG, "Path: " + android.os.Environment.getExternalStorageDirectory().toString());
                try {
                    File myFile = new File("dozer.txt");
                    Log.d(TAG, "Absolute path: " + myFile.getAbsolutePath());
                    myFile.createNewFile();                                         // Создается файл, если он не был создан
                } catch (IOException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                */
            });
        } else {
            Log.d("DoZer", "exitBtn not found");
        }

        // Exit button
        final Button exitBtn = findViewById(R.id.exitBtn);
        if (exitBtn != null) {
            exitBtn.setOnClickListener(v -> {
                Log.d("DoZer", "Pressed exit.");
                System.exit(0);
            });
        } else {
            Log.d("DoZer", "exitBtn not found");
        }

        // Specter / Dose toggle button
        final Button gistoBtn = findViewById(R.id.gistoBtn);
        if (gistoBtn != null) {
            gistoBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    selectTypeScreen();
                }
            });
        } else {
            Log.d("DoZer", "gistoBtn not found");
        }

        // Setup button
        final Button setupBtn = findViewById(R.id.setupBtn);
        if (setupBtn != null) {
            setupBtn.setOnClickListener(v -> setupActivity());
        } else {
            Log.d("DoZer", "Setup not found");
        }

        // Save button to BqMonitor format
        final Button saveBtn = findViewById(R.id.SaveBtn);
        if (saveBtn != null) {
            saveBtn.setOnClickListener(v -> DH.saveHistogramXML());
        } else {
            Log.d("DoZer", "saveBtn not found");
        }

        // Clear button
        final Button clearBtn = findViewById(R.id.clearBtn);
        if (clearBtn != null) {
            clearBtn.setOnClickListener(v -> {
                try {
                    DA.resetAll();
                    DA.hideCursor();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            Log.d("DoZer", "clearBtn not found");
        }

        // Handler for redraw in main context
        h = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(android.os.Message msg) {
                // Redraw screen
                DA.redraw();
            }
        };
        formatLayout();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        mContentView = findViewById(R.id.mainLayout);
        mainImage = findViewById(R.id.mainImage);
        historyDoze = findViewById(R.id.historyDose);
        cursorImage = findViewById(R.id.cursorImage);
        connIndicator = findViewById(R.id.connectIndicator);
        textStatistic1 = findViewById(R.id.textStatistic1);
        textStatistic2 = findViewById(R.id.textStatistic2);
        textStatistic3 = findViewById(R.id.textStatistic3);
        textStatistic4 = findViewById(R.id.textStatistic4);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        selectTypeScreen();
        // Check permission for write storage.
        int permissionStatus1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionStatus2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if ((permissionStatus1 == PackageManager.PERMISSION_GRANTED) && (permissionStatus2 == PackageManager.PERMISSION_GRANTED)) {
            initApplication();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE },200);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        formatLayout();
    }
    /*
        Timer check BT connect
    */
    class intervalTimer {
        Timer timer = new Timer();
        TimerTask mTimerTask = new MyTimerTask();
        public void startTimer() {
            timer.schedule(mTimerTask, 5000, 5000);
        }
    }
    class MyTimerTask extends TimerTask {
        @Override
        public void run () {
            if ( connected ) {
                //String str = "<DATA>";
                //Log.i(TAG, "Timer tick.");
                //try {
                //    BT.write(str.getBytes());
                //} catch (IOException e) {
                //    Log.i(TAG, "Write error : " + e.getMessage());
                //}
            } else {
                if ( BT == null ) {
                    BT = new getBluetooth();
                }
                BT.destroyDevice();
                BT.initLeDevice();
            }
        }
    }
    /*
        Called on restart screen
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart.");

        formatLayout();
    }

    public class Props {
        public  String readProp(String key) {
            String fname = "device.properties";
            Properties prop = new Properties();
            FileInputStream fis = null;
            try {
                fis = openFileInput(fname);
                if (fis != null) {
                    prop.load(fis);
                } else {
                    Log.d(TAG, "file not found.");
                }
            } catch (IOException e) {
                Log.d(TAG, "Error message: " + e.getMessage());
            }
            if (fis == null ) {  // Create config file if not exist.
                FileOutputStream fos;
                try {
                    fos = openFileOutput(fname, MODE_PRIVATE);
                    prop.setProperty("MAC", defMAC);
                    prop.store(fos, null);
                } catch (IOException e) {
                    Log.d(TAG, "Error message: " + e.getMessage());
                }
            }
            return prop.getProperty(key);
        }
    }


    class getBluetooth {
        Context context = getApplicationContext();
        public BluetoothAdapter bluetooth;
        //private final BluetoothSocket btSocket = null;
        public BluetoothDevice device;
        private BluetoothGattCharacteristic readCharacteristic, writeCharacteristic;
        //private boolean canceled = false;
        private static final int MAX_MTU = 512; // BLE standard does not limit, some BLE 4.2 devices support 251, various source say that Android has max 512
        private int payloadSize = DEFAULT_MTU - 3;
        private static final int DEFAULT_MTU = 23;
        private DeviceDelegate delegate;
        private boolean writePending;
        private ArrayList<byte[]> writeBuffer;

        // https://play.google.com/store/apps/details?id=com.telit.tiosample
        // https://www.telit.com/wp-content/uploads/2017/09/TIO_Implementation_Guide_r6.pdf
        public final UUID BLUETOOTH_LE_CCCD = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
        public final UUID BLUETOOTH_LE_CC254X_SERVICE = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
        public final UUID BLUETOOTH_LE_CC254X_CHAR_RW = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");

        /**
         * delegate device specific behaviour to inner class
         */
        private class DeviceDelegate {
            boolean connectCharacteristics(BluetoothGattService s) { return true; }
            // following methods only overwritten for Telit devices
            void onDescriptorWrite(BluetoothGatt g, BluetoothGattDescriptor d, int status) { /*nop*/ }
            void onCharacteristicChanged(BluetoothGatt g, BluetoothGattCharacteristic c) {/*nop*/ }
            void onCharacteristicWrite(BluetoothGatt g, BluetoothGattCharacteristic c, int status) { /*nop*/ }
            boolean canWrite() { return true; }
            void disconnect() {/*nop*/ }
        }

        public void destroyDevice() {
            if (gatt == null) {
                //Toast.makeText(getBaseContext(), "BlueTooth disabled ?.", Toast.LENGTH_LONG).show();
                finish();
            } else {
                gatt.disconnect();
                gatt.close();
                if (delegate != null)
                    delegate.disconnect();
                delegate = null;
                device = null;
                writeBuffer.clear();
            }
        }
        /*
                Настройка BLE.
        */
        public void initLeDevice() {
            writeBuffer = new ArrayList<>();    // Буфер для передачи.
            Log.d(TAG, "...Установка соединенния...");
            bluetooth = BluetoothAdapter.getDefaultAdapter();
            if  ( ! bluetooth.isEnabled()) {
                Log.d(TAG, "Bluetooth disabled. Exit.");
                Toast.makeText(getBaseContext(), "BlueTooth disable ? \nProgram terminated.", Toast.LENGTH_LONG).show();
                return;
            }
            device = bluetooth.getRemoteDevice(MAC);  // Подключаемся по MAC адресу.
            Log.d(TAG, "Status: " + bluetooth.getState());
            if ( device == null ) {
                Log.i(TAG, "Device: " + MAC + " not connected.");
                return;
            } else {
                Log.i(TAG, "Try gatt connect.");
                gatt = device.connectGatt(context, false, gattCallback, BluetoothDevice.TRANSPORT_LE);
                if (gatt == null) {
                    Log.i(TAG, "Gatt create failed.");
                    finish();
                }
                Log.i(TAG, "End init.");
            }
        }

        // Сюда попадаем после завершения работы connectGatt
        private final BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
            @Override
            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    Log.i(TAG, "Connect success.");
                    if (!gatt.discoverServices()) {
                        Log.i(TAG, "Discover service failed.");
                        finish();
                    }
                    if (!gatt.requestMtu(MAX_MTU)) {  // Изменяем MTU
                        Log.i(TAG, "MTU set failed.");
                        finish();
                    }
                } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                    connected = false;
                    if ( DA != null) {
                        DA.connectIndicator();
                        //Log.i(TAG, "Change connect indicator");
                    }
                    writePending = false;
                    Log.i(TAG, "Disconnect.");
                }
            }

            // Сюда попадаем после выполнения gatt.discoverServices
            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                Log.d(TAG, "servicesDiscovered, status " + status);
                //if ( canceled)
                //    return;
                //connectCharacteristics1(gatt);
                boolean sync = true;
                writePending = false;
                Log.d(TAG, "Set gatt Characteristics.");
                for (BluetoothGattService gattService : gatt.getServices()) {
                    if (gattService.getUuid().equals(BLUETOOTH_LE_CC254X_SERVICE)) {
                        delegate = new Cc245XDelegate();
                    }
                    if ( delegate != null) {
                        sync = delegate.connectCharacteristics(gattService);
                        break;
                    }
                }
                if (sync) {
                    if (!gatt.requestMtu(MAX_MTU))
                        Log.d(TAG, "Error set MTU.");
                }
            }


            @Override
            public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                delegate.onDescriptorWrite(gatt, descriptor, status);
                //if( canceled)
                //    return;
                if(descriptor.getCharacteristic() == readCharacteristic) {
                    Log.d(TAG,"writing read characteristic descriptor finished, status=" + status);
                    if (status != BluetoothGatt.GATT_SUCCESS) {
                        Log.d(TAG,"Write characteristic failed.");
                    } else {
                        connected = true;
                        //myView.invalidate();
                        if (DA != null) {
                            DA.connectIndicator();
                            //Log.i(TAG, "Change connect indicator");
                        }
                        Log.d(TAG, "Write descriptor Ok.");
                    }
                }
            }
            @Override
            public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                if ( /*canceled ||*/ !connected || writeCharacteristic == null)
                    return;
                if (status != BluetoothGatt.GATT_SUCCESS) {
                    return;
                }
                delegate.onCharacteristicWrite(gatt, characteristic, status);
                //if ( canceled)
                //    return;
                if (characteristic == writeCharacteristic) { // NOPMD - test object identity
                    //Log.d(TAG, "write finished, status=" + status);
                    writeNext();
                }
            }

            @Override
            public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
                Log.d(TAG,"mtu size " + mtu + ", status = " + status);
                if(status ==  BluetoothGatt.GATT_SUCCESS) {
                    payloadSize = mtu - 3;
                    Log.d(TAG, "payload size " + payloadSize);
                }
                if ( writeCharacteristic == null ) {
                    Log.d(TAG, "write characteristic not writable - 1");
                    return;
                } else {
                    int writeProperties = writeCharacteristic.getProperties();
                    if ((writeProperties & (BluetoothGattCharacteristic.PROPERTY_WRITE +     // Microbit,HM10-clone have WRITE
                            BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE)) == 0) { // HM10,TI uart,Telit have only WRITE_NO_RESPONSE
                        Log.d(TAG, "write characteristic not writable - 2");
                        return;
                    }
                }
                if(!gatt.setCharacteristicNotification(readCharacteristic,true)) {
                    Log.d(TAG, "no notification for read characteristic");
                    return;
                }
                BluetoothGattDescriptor readDescriptor = readCharacteristic.getDescriptor(BLUETOOTH_LE_CCCD);
                if(readDescriptor == null) {
                    Log.d(TAG, "no CCCD descriptor for read characteristic");
                    return;
                }
                int readProperties = readCharacteristic.getProperties();
                if((readProperties & BluetoothGattCharacteristic.PROPERTY_INDICATE) != 0) {
                    Log.d(TAG, "enable read indication");
                    readDescriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                }else if((readProperties & BluetoothGattCharacteristic.PROPERTY_NOTIFY) != 0) {
                    Log.d(TAG, "enable read notification");
                    readDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                } else {
                    Log.d(TAG, "no indication/notification for read characteristic ("+readProperties+")");
                    return;
                }
                Log.d(TAG,"writing read characteristic descriptor");
                if(!gatt.writeDescriptor(readDescriptor)) {
                    Log.d(TAG, "read characteristic CCCD descriptor not writable");
                }
                // continues asynchronously in onDescriptorWrite()
            }
            /*
             *      Прием данных
             */
            @Override
            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                //if( canceled)
                //    return;
                delegate.onCharacteristicChanged(gatt, characteristic);
                //if( canceled)
                //    return;
                if(characteristic == readCharacteristic) { // NOPMD - test object identity
                    byte[] data = readCharacteristic.getValue();
                    /*
                         Заполнение массива
                    */
                    // Ищем стартовую последовательность <B>.
                    if (data.length > 0) {
                        for (int i2 = 0; i2 < data.length; i2++) {
                            switch (startFlag) {
                                case 0:
                                    if (data[i2] == '<') {
                                        startFlag++;
                                    }
                                    break;
                                case 1:
                                    if (data[i2] == 'B') {
                                        startFlag++;
                                    } else {
                                        startFlag = 0;
                                    }
                                    break;
                                case 2:
                                    if (data[i2] == '>') {
                                        startFlag++;
                                        //Log.i(TAG, "Start marker found.");
                                        bufferIndex = 0;
                                        specrtCRC = 0;
                                    } else {
                                        startFlag = 0;
                                    }
                                    break;
                                default:   // Start sequence found, data load.
                                    if (bufferIndex < 2084) {
                                        specrtCRC = specrtCRC + (char) (data[i2] & 0xFF);   // CRC
                                    }
                                    spectrData[bufferIndex++] = (byte) (data[i2] & 0xFF);
                                    if (bufferIndex == 2086) {     //Transmission complete.
                                        startFlag = 0;
                                        double tmpCRC = (char) (spectrData[2084] << 8 | (spectrData[2085] & 0xFF));
                                        specrtCRC = specrtCRC - (Math.floor(specrtCRC / 65536) * 65536);     // Facking Java
                                        //Log.i(TAG, "tmpCRC : " + tmpCRC + ", spectrCRC : " + specrtCRC + ", diff : " + (tmpCRC - specrtCRC));
                                        if (tmpCRC == specrtCRC) { // Update if CRC correct.
                                            /* Debug */
                                            //tmpTime = (char) (spectrData[0] << 8 | (spectrData[1] & 0xff)); // Total time from device.
                                            //tmpTime = tmpTime + ((char) (spectrData[2] << 8 | (spectrData[3] & 0xff)) * 65536);
                                            //Log.i(TAG, "{0, 1, 2, 3} : " + (spectrData[0]  + spectrData[1] + spectrData[2] + spectrData[3]) + ", Time: " + tmpTime);
                                            //myView.invalidate();    // Redraw screen.
                                            if ( DA != null ) {
                                                // Redraw in thread
                                                Thread t = new Thread(new Runnable() {
                                                    public void run() {
                                                        h.sendEmptyMessage(1);
                                                        //DA.redraw();
                                                    }
                                                });
                                                t.start();
                                            }
                                        }
                                    }
                            }
                        }
                    }
                }
            }
        };

        /*
                Parameters for JDY-23, JDY-10, JDY-19
         */
        private class Cc245XDelegate extends DeviceDelegate {
            //@Override
            boolean connectCharacteristics(BluetoothGattService gattService) {
                Log.i(TAG, "Service cc254x uart");
                readCharacteristic = gattService.getCharacteristic(BLUETOOTH_LE_CC254X_CHAR_RW);
                writeCharacteristic = gattService.getCharacteristic(BLUETOOTH_LE_CC254X_CHAR_RW);
                return true;
            }
        }

        // Broadcast приемник
        private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String action = intent.getAction();
                Log.i(TAG, "Broadcast.");
            }
        };

        /*
            Выводит имя и MAC адрес подключенного устройства
         */
        public void getNameBluetooth()
        {
            String status;
            if(bluetooth.isEnabled()){
                status = bluetooth.getName() + " : " + bluetooth.getAddress();
            } else {
                status="Bluetooth выключен";
            }
            Log.i(TAG, "Status:" + status);
        }

        /*
            Проверяет поддержку Bluetooth
         */
        public void checkBluetooth()
        {
            if(bluetooth == null) { // Bluetooth отсутствует
                Toast.makeText(getApplicationContext(), "Не работает Bluetooth.", Toast.LENGTH_LONG).show();
            } else {
                if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {

                    if (bluetooth.isEnabled()) {
                        Toast.makeText(getApplicationContext(), "Bluetooth BLE - Ok", Toast.LENGTH_LONG).show();
                    } else {
                        // Bluetooth выключен. Предложим пользователю включить его.
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, RESULT_OK);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Bluetooth BLE not support", Toast.LENGTH_LONG).show();
                }
            }
        }

        /*
         *  Передача данных
         */
        void write(byte[] data) throws IOException {
            if( /*canceled ||*/ !connected || writeCharacteristic == null)
                throw new IOException("not connected");
            byte[] data0;
            synchronized (writeBuffer) {
                if(data.length <= payloadSize) {
                    data0 = data;
                } else {
                    data0 = Arrays.copyOfRange(data, 0, payloadSize);
                }
                if(!writePending && writeBuffer.isEmpty() && delegate.canWrite()) {
                    writePending = true;
                } else {
                    writeBuffer.add(data0);
                    Log.d(TAG,"write queued, len=" + data0.length);
                    data0 = null;
                }
                if(data.length > payloadSize) {
                    for(int i = 1; i < (data.length + payloadSize - 1) / payloadSize; i++) {
                        int from = i * payloadSize;
                        int to = Math.min(from + payloadSize, data.length);
                        writeBuffer.add(Arrays.copyOfRange(data, from, to));
                        Log.d(TAG,"write queued, len=" + (to - from));
                    }
                }
            }
            if(data0 != null) {
                writeCharacteristic.setValue(data0);
                if (!gatt.writeCharacteristic(writeCharacteristic)) {
                    Log.d(TAG,"Write Characteristic error");
                    //onSerialIoError(new IOException("write failed"));
                } else {
                    Log.d(TAG,"write started, len=" + data0.length);
                }
            }
            // continues asynchronously in onCharacteristicWrite()
        }

        // Передача данных в ассинхронном режиме.
        private void writeNext() {
            final byte[] data;
            synchronized (writeBuffer) {
                if (!writeBuffer.isEmpty() && delegate.canWrite()) {
                    writePending = true;
                    data = writeBuffer.remove(0);
                } else {
                    writePending = false;
                    data = null;
                }
            }
            if(data != null) {
                writeCharacteristic.setValue(data);
                if (!gatt.writeCharacteristic(writeCharacteristic)) {
                    Log.d(TAG,"Write Characteristic error");
                    //onSerialIoError(new IOException("write failed"));
                } else {
                    Log.d(TAG,"write started from next, len=" + data.length);
                }
            }
        }
    }


    /*
            Вызывается при перерисовке.
    */
    public class DrawAll {
        double countsAll, interval, oldValX;
        float batVoltage = 0, oldX = -1 , oldY = -1, maxPoint, mastab, mastab2, tmpVal, tmpVal2, mastabLog, maxPointLog, penSize = 2, pen2Size = 1, pen3Size = 1, hsizeFindData = 100;
        private Paint curs = new Paint(), empt = new Paint(), p = new Paint(), pm = new Paint(), pLog = new Paint(), pText = new Paint(), pTextR1 = new Paint(), pTextR2 = new Paint(),
                emptFindData = new Paint(), pTextR3 = new Paint(), pInd = new Paint(), pFindData = new Paint(), pFindData1 = new Paint(), pFindData2 = new Paint(), pBackground = new Paint();
        public Bitmap bitmap, bitmap2, bitmap3;
        public Canvas mainCanvas, historyCanvas, cursorCanvas;
        public int WSizeHist, HSizeHist;
        private int textVShift = 45;

        public void redraw() {
            //
            // Layout size
            //
            if (mainCanvas == null) {
                HSize = mainImage.getHeight();
                WSize = mainImage.getWidth();
                bitmap = Bitmap.createBitmap(WSize, HSize, Bitmap.Config.ARGB_8888);
                mainCanvas = new Canvas(bitmap);
                Log.d(TAG, " WSize: " + WSize + " HSize: " + HSize);
            }
            if (historyCanvas == null) {
                WSizeHist = 210;
                HSizeHist = 50;
                Log.d(TAG, " WSizeHist: " + WSizeHist + " HSizeHist: " + HSizeHist);
                bitmap2 = Bitmap.createBitmap(WSizeHist, HSizeHist, Bitmap.Config.ARGB_8888);
                historyCanvas = new Canvas(bitmap2);
            }
            if (mainCanvas != null &&  historyCanvas != null) {
                    //
                    //  Draw histogram
                    //
                    writeHistogram(mainCanvas, historyCanvas);
                    //connectIndicator();

                    //
                    //  Update image
                    //
                    mainImage.setImageBitmap(bitmap);
                    historyDoze.setImageBitmap(bitmap2);
                }
            }

         public void hideCursor() {
             if (oldX > 0) {
                 cursorCanvas.drawLine(oldX, oldY, oldX, HSize, empt); // erase vertical line
                 cursorCanvas.drawLine(0, oldY, oldX, oldY, empt);
                 cursorCanvas.drawText("" + round(tmpVal2), 1, oldY, empt);
                 cursorCanvas.save();
                 cursorCanvas.rotate((float) 90, oldX, HSize - textVShift);
                 cursorCanvas.drawText("" + round(oldValX), oldX, HSize - textVShift, empt);
                 cursorCanvas.restore();
             }
         }

        public void drawCursor(float X, float Y) {
            if (cursorCanvas == null) {
                HSize = mainImage.getHeight();
                WSize = mainImage.getWidth();
                bitmap3 = Bitmap.createBitmap(WSize, HSize, Bitmap.Config.ARGB_8888);
                cursorCanvas = new Canvas(bitmap3);
                empt.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                empt.setTextSize(20.0f);
                curs.setColor(Color.argb(255, 0, 255, 0));
                curs.setTextSize(20.0f);
            } else {
                hideCursor();
                oldX = X;
                oldY = Y;
                // Calculate Energy over channel.
                if ( correctB == 0) {
                    oldValX = (double) X / penSize;
                } else {
                    oldValX = Math.pow((X / penSize), 2) * correctA + ((double) X / penSize) * correctB + correctC;
                }
                // Calculate index specter array
                int i = (int) Math.floor(X / penSize ) ;
                if ( i > 11 ) {
                    // Get specter data
                    tmpVal2 = resultData[i];
                    oldY = (float) (HSize - log10(tmpVal2) * mastabLog);
                    if (X > 0 && oldY > 0) {
                        cursorCanvas.drawLine(X, oldY, X, HSize, curs);  // Vertical line
                        cursorCanvas.drawLine(0, oldY, X, oldY, curs); // Horizontal line
                        cursorCanvas.drawText("" + round(tmpVal2), 1, oldY, curs);
                        cursorCanvas.save();
                        cursorCanvas.rotate((float) 90, X, HSize - textVShift);
                        cursorCanvas.drawText("" + round(oldValX), X, HSize - textVShift, curs);
                        cursorCanvas.restore();
                        cursorImage.setImageBitmap(bitmap3);
                    }
                }
            }
        }

        // Save config data to spectrometer
        public void sendCfg(byte [] cfgData) throws IOException  {
            int CS = 0;
            if (connected) {
                                //01234567890123456789
                byte[] sndData = "<2>.................".getBytes();
                sndData[3] = cfgData[0];
                sndData[4] = cfgData[1];
                sndData[5] = cfgData[2];
                sndData[6] = cfgData[3];
                sndData[7] = cfgData[4];
                sndData[8] = cfgData[5];
                sndData[9] = cfgData[6];
                sndData[10] = cfgData[7];
                for (int i = 0; i < 18; i++) {
                    CS = CS + (char) (sndData[i] & 0xFF);
                }
                // Check summ
                sndData[18] = (byte) (CS & 0xFF);
                sndData[19] = (byte) ((CS >> 8) & 0xFF);
                BT.write(sndData);
            }
        }

        // Reset statistic
        public void resetAll() throws IOException {
            int CS = 0;
            for ( int i = 0; i < findDataSize; i++) {
                findData[i] = 0;
            }
            if (connected) {
                                //01234567890123456789
                byte[] sndData = "<1>.................".getBytes();
                for (int i = 0; i < 18; i++) {
                    CS = CS + (char) (sndData[i] & 0xFF);
                }
                // Check summ
                sndData[18] = (byte) (CS & 0xFF);
                sndData[19] = (byte) ((CS >> 8) & 0xFF);
                BT.write(sndData);
            }
        }


        public void connectIndicator() {
            //
            //  Connect indicator
            //
            Log.i(TAG, "Change connect indicator");
            if (connected) {
                connIndicator.setBackgroundColor(Color.argb(255, 0, 255, 0));
            } else {
                hideCursor();
                connIndicator.setBackgroundColor(Color.argb(255, 255, 0, 0));
            }
        }

        public void writeHistogram(Canvas canvas, Canvas histCanvas) {
            // Background
            canvas.drawColor(Color.argb(255, 0, 0, 0));
            //
            //  Отрисовка графика с учетом масштаба.
            //
            // Линейная гистограмма
            p.setColor(Color.argb(255, 40, 40, 255));
            p.setStrokeWidth(penSize);

            // For markers
            pm.setColor(Color.argb(200, 255, 40, 40));
            pm.setStrokeWidth(penSize);

            // Логарифмическая гистограмма
            pLog.setColor(Color.argb(100, 40, 60, 255));
            pLog.setStrokeWidth(penSize);

            // Background radiation data

            pBackground.setColor(Color.argb(100, 40, 255, 40));
            pBackground.setStrokeWidth(penSize);

            // Текст статистики
            pText.setColor(Color.argb(100, 255, 40, 255));
            pText.setStrokeWidth(penSize);
            pText.setTextSize(10.0f);

            // График дла поиска
            pFindData.setColor(Color.argb(200, 40, 255, 40)); // Normal
            pFindData.setStrokeWidth(pen3Size);
            pFindData1.setColor(Color.argb(200, 255, 255, 40)); // Warning
            pFindData1.setStrokeWidth(pen3Size);
            pFindData2.setColor(Color.argb(200, 255, 40, 40)); // Alarm
            pFindData2.setStrokeWidth(pen3Size);
            emptFindData.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

            /*
                Get time and counter from device
             */
            countsAll = (char) (spectrData[4] << 8 | (spectrData[5] & 0xff));  // Total counts from device
            countsAll = countsAll + ((char) (spectrData[6] << 8 | (spectrData[7] & 0xff)) * 65536);
            batVoltage = (char) (spectrData[9] & 0xff);
            tmpTime = (char) (spectrData[0] << 8 | (spectrData[1] & 0xff)); // Total time from device.
            tmpTime = tmpTime + ((char) (spectrData[2] << 8 | (spectrData[3] & 0xff)) * 65536);

            //
            //  Вычисление масштабирования для линейного и логарифмического представления.
            //
            mastab = 1;
            mastabLog = 1;
            maxPoint = 1;
            maxPointLog = 0;
            countsAll = 0;
            int j = 0;
            if ((foneActive == 1) && (tmpTime > 0) && (backgtoundTime > 0) ) {
                mastab2 = tmpTime / (float) backgtoundTime;  // Calculate mashtab for background radiation
            } else {
                mastab2 = 0;
            }
            for (int i = firstCanal; i < 2084; i++) {
                tmpVal = (char) (spectrData[i] << 8 | (spectrData[++i] & 0xFF));
                countsAll = countsAll + tmpVal;  // Total pulses
                if ( i < maxCanal) {
                    if (mastab2 == 0) {  // background radiation disabled
                        resultData[j] = tmpVal;
                    } else {
                        resultData[j] = tmpVal - foneData[j] *  mastab2;
                        if (resultData[j] < 0) {
                            resultData[j] = 0;
                        }
                    }
                    if (resultData[j] > maxPoint) {
                        maxPoint = resultData[j];
                    }
                    j++;
                }
            }

            /*
                Smoothing specter
            */
            float sm;
            if (smoothSpecter == 1) {
                smoothArray(resultData,smoothWindow, maxCanal / 2 + smoothWindow);
                maxPoint = 1;
                maxPointLog = 1;
                for (int i = 0; i < maxCanal / 2 + smoothWindow; i++) {
                    if (resultData[i] > maxPoint) {
                        maxPoint = resultData[i];
                    }
                }
            }

            mastab = (float) HSize / maxPoint;
            mastabLog = (float) HSize / (float) Math.log10(maxPoint);
            /*
                    Redraw histogram
             */
            for (int i = 0; i < maxCanal / 2; i++) {
                float X = i * penSize - 2;
                canvas.drawLine(X, HSize - (float) Math.log10(resultData[i]) * mastabLog, X, HSize, pLog);
                canvas.drawLine(X, HSize - resultData[i] * mastab, X, HSize, p);
            }

            /*
                Draw background radiation
             */
            if ((foneActive == 2) && (tmpTime > 0) && (backgtoundTime > 0) ) {
                mastab2 =  tmpTime / (float) backgtoundTime * mastab;  // Calculate mashtab for background radiation
                for (int i = 0; i < 1024; i++) {
                    float X = i * penSize - 2;
                    canvas.drawLine(X, HSize - foneData[i] * mastab2, X, HSize, pBackground);
                }
            }
            // Output total counts and cps.
            //Log.i(TAG, "tmpTime : " + tmpTime + ", curentTime : " + curentTime + ", countsAll: " + countsAll + ", countsAll - oldCounts: " + (countsAll - oldCounts));

            if (oldCounts <= 0 ) {
                oldCounts = countsAll;
                curentTime = tmpTime;
            } else {
                interval = tmpTime - curentTime;
                curentTime = tmpTime;
                mastab = 0;

                // Смещение графика поиска на одну позицию.
                tmpFindData = round((countsAll - oldCounts) / interval);
                if (tmpFindData > 0) {
                    for (int i = findDataSize - 2; i >= 0; i--) {
                        if (mastab < findData[i]) { // Max data
                            mastab = findData[i];
                        }
                        findData[i + 1] = findData[i];
                    }
                    findData[0] = tmpFindData;
                    if (mastab < findData[0]) // Max data
                        mastab = findData[0];
                    mastab = HSizeHist / mastab;

                    // Перерисовка графика поиска
                    float X, Y;
                    //histCanvas.drawColor(0xff000000);
                    for (int i = 0; i < findDataSize - 1; i++) {
                        X = WSizeHist - i * pen3Size - 1;
                        Y = HSizeHist - findData[i] * mastab + 1;
                        if (HSizeHist - Y < 1) {
                            Y = HSizeHist - 1;
                        }
                        // Clear old data
                        histCanvas.drawLine(X, 1, X, HSizeHist, emptFindData);
                        // Draw new line
                        //if ( findData[i] > 0) {
                            if (findData[i] < Trh1) {
                                histCanvas.drawLine(X, Y, X, HSizeHist, pFindData);
                            } else if (findData[i] < Trh2) {
                                histCanvas.drawLine(X, Y, X, HSizeHist, pFindData1);
                            } else {
                                histCanvas.drawLine(X, Y, X, HSizeHist, pFindData2);
                            }
                        //}
                    }

                    // Вывод статистики
                    float acps = 0;
                    if (tmpTime > 0) {
                        acps = (float) (countsAll / tmpTime);
                    }
                    /* Battery capacity */
                    float batCapacity =  (float) ((batVoltage - 192) * 1.7);
                    if (batCapacity > 100) {
                        batCapacity = 100;
                    } else {
                        if (batCapacity < 0) {
                            batCapacity = 0;
                        }
                    }
                    textStatistic1.setText(String.format("%.0f%% total: %.0f cps: %.0f", batCapacity, countsAll, findData[0]));
                    if (countsAll == 0) {
                        textStatistic2.setText(String.format("time: %.0f avg: %.2f (100", tmpTime, acps) + "%)");
                    } else {
                        textStatistic2.setText(String.format("time: %.0f avg: %.2f (%.2f%%)", tmpTime, acps, 300 / Math.sqrt(countsAll)));
                    }
                    /* Текущее значение */
                    if (findData[0] < Trh1) {
                        textStatistic3.setTextColor(0xFF00FF00);
                    } else if ( findData[0] < Trh2 ) {
                        textStatistic3.setTextColor(0xFFFFFF00);
                    } else {
                        textStatistic3.setTextColor(0xFFFF0000);
                    }
                    textStatistic3.setText(String.format("Now: %.1f uR/h", findData[0] * koeffR));

                    /* Среднее значение */
                    if (acps < Trh1) {
                        textStatistic4.setTextColor(0xFF00FF00);
                    } else if (acps < Trh2) {
                        textStatistic4.setTextColor(0xFFFFFF00);
                    } else {
                        textStatistic4                                                   .setTextColor(0xFFFF0000);
                    }
                    textStatistic4.setText(String.format(" Avg: %.2f uR/h", acps * koeffR));

                    oldCounts = countsAll;
                } else {
                    oldCounts = countsAll;
                    curentTime = tmpTime;
                }
            }
        }
    }


    class drawHistogram {
        float tmpVal;

        //  Save histogram in CSV format
        public void saveHistogram() {
            String dataStr = null, fileName;
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'DoZer_'yyyy-MM-dd'_'HH:mm:ss");
            fileName = simpleDateFormat.format(now);
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                Log.d(TAG, "SD-storage not available: " + Environment.getExternalStorageState());
                Toast.makeText(getBaseContext(), "SD-storage not available: " + Environment.getExternalStorageState() , Toast.LENGTH_LONG).show();
                return;
            }
            Toast toast = Toast.makeText(getApplicationContext(),"Сохранено.", Toast.LENGTH_SHORT);
            try {
                File direct = new File(Environment.getExternalStorageDirectory()+"/DoZer");
                if(!direct.exists()) {
                    if(direct.mkdir()); // Создаем каталог если его нет;
                }
                File myFile = new File(Environment.getExternalStorageDirectory().toString() + "/DoZer/" + fileName + ".csv");
                myFile.createNewFile();                                         // Создается файл, если он не был создан
                FileOutputStream outputStream = new FileOutputStream(myFile);   // После чего создаем поток для записи
                int j = 0;
                for (int i = 8; i < maxCanal; i++) {
                    tmpVal = (char) (spectrData[i] << 8 | (spectrData[++i] & 0xff));
                    dataStr = String.valueOf(j++);
                    outputStream.write(dataStr.getBytes());
                    outputStream.write(0x2c);
                    dataStr = String.valueOf((int) tmpVal);
                    outputStream.write(dataStr.getBytes());                            // и производим непосредственно запись
                    outputStream.write(0x0a);
                }
                outputStream.write(dataStr.getBytes());
                outputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
                toast = Toast.makeText(getApplicationContext(),"Ошибка. " + e.getMessage(), Toast.LENGTH_SHORT);
            }
            toast.show();
            /*
                Определение GPS координат
             */
            try {
                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (lm != null) {
                    @SuppressLint("MissingPermission") Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (loc != null) {
                        //Toast.makeText(getBaseContext(), "Lat: " + loc.getLatitude() + " Lng: " + loc.getLongitude() + " Alt: " + loc.getAltitude(), Toast.LENGTH_SHORT).show();
                        /*
                            Запись координат в файл
                         */
                        File myFile = new File(Environment.getExternalStorageDirectory().toString() + "/DoZer/" + fileName + ".txt");
                        myFile.createNewFile();                                         // Создается файл, если он не был создан
                        FileOutputStream outputStream = new FileOutputStream(myFile);   // После чего создаем поток для записи
                        //String timeStr = java.text.DateFormat.getDateTimeInstance().format(loc.getTime());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        String timeStr = sdf.format(loc.getTime());
                        String nowStr = sdf.format(now);
                        float tmpTime2 = (char) (spectrData[0] << 8 | (spectrData[1] & 0xff)); // Total time from device.
                        tmpTime2 = tmpTime2 + ((char) (spectrData[2] << 8 | (spectrData[3] & 0xff)) * 65536);
                        dataStr = "Date: " + nowStr + " Lat: " + loc.getLatitude() + " Lng: " + loc.getLongitude()
                                + " Alt: " + loc.getAltitude() + " Speed: " + loc.getSpeed() + " GPS last update: " + timeStr
                                + " Measurement time: " + round(tmpTime2);
                        outputStream.write(dataStr.getBytes());
                        outputStream.close();
                    } else {
                        Toast.makeText(getBaseContext(), "Ошибка получения координат.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Ошибка создания LocationManager.", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Ошибка получения и записи координат." + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        //  Save histogram in XML BqMonitor format
        public void saveHistogramXML() {
            String dataStr, fileName, startTime, endTime;
            double sTime;
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'_'HHmmss");
            fileName = simpleDateFormat.format(now);
            Toast toast = Toast.makeText(getApplicationContext(),"Saved.", Toast.LENGTH_SHORT);
            // Check mount devices
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                Log.d(TAG, "SD-storage not available: " + Environment.getExternalStorageState());
                Toast.makeText(getBaseContext(), "SD-storage not available: " + Environment.getExternalStorageState() , Toast.LENGTH_LONG).show();
                return;
            }
            try {
                File SDPath = Environment.getExternalStorageDirectory();
                File direct = new File(SDPath.getAbsolutePath() + "/DoZer");
                Log.d(TAG, "SD Path: " +  SDPath.getAbsolutePath() + "/DoZer");
                if(!direct.exists()) {
                    if(direct.mkdir()) {  // Создаем каталог если его нет;
                        Log.d(TAG, "SD Path: " +  SDPath.getAbsolutePath() + "/DoZer");
                    } else {
                        Log.d(TAG, "Create dir error.");
                        Toast.makeText(getBaseContext(), "Directory create error. ", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                File myFile = new File( SDPath.getAbsolutePath() + "/DoZer/" + fileName + ".xml");
                if (myFile.createNewFile()) {  // Create new file if not exist
                    Log.d(TAG, "File create Ok.");
                } else {
                    Toast.makeText(getBaseContext(), "File create error. ", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Create file error.");
                    return;
                }
                FileOutputStream outputStream = new FileOutputStream(myFile);   // Create stream

                float tmpTime2 = (char) (spectrData[0] << 8 | (spectrData[1] & 0xff)); // Total time from device.
                tmpTime2 = tmpTime2 + ((char) (spectrData[2] << 8 | (spectrData[3] & 0xff)) * 65536);
                double pulseSumm = 0;
                String locationStr = " Unknown.";
             /*
                Get GPS location
             */
                try {
                    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    if (lm != null) {
                        @SuppressLint("MissingPermission")
                        Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null) {
                            locationStr = " Lat: " + loc.getLatitude() + " Lng: " + loc.getLongitude()
                                    + " Alt: " + loc.getAltitude() + " Speed: " + loc.getSpeed();
                        } else {
                            Toast.makeText(getBaseContext(), "GPS error.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getBaseContext(), "Error create LocationManager.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "GPS write error." + e.getMessage(), Toast.LENGTH_LONG).show();
                }

                simpleDateFormat = new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SZZZZZ");
                startTime = simpleDateFormat.format(now);
                sTime = System.currentTimeMillis() - tmpTime2 * 1000;
                startTime = simpleDateFormat.format(sTime);
                endTime = simpleDateFormat.format(now);

                for (int i = 0; i < maxCanal / 2; i++) {
                    pulseSumm = pulseSumm + resultData[i];
                }
                dataStr = (String) "<?xml version=\"1.0\"?>\n" +
                        "<ResultDataFile xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                        "<FormatVersion>120920</FormatVersion>\n" +
                        "<ResultDataList>\n" +
                        "<ResultData>\n" +
                        "<SampleInfo>\n" +
                        "<Name />\n" +
                        "<Location>" + locationStr + "</Location>\n" +
                        "<Time>" + startTime + "</Time>\n" +
                        "<Weight>1</Weight>\n" +
                        "<Volume>1</Volume>\n" +
                        "<Note />\n" +
                        "</SampleInfo>\n" +
                        "<DeviceConfigReference>\n" +
                        "<Name>DoZer</Name>\n" +
                        "<Guid>fb3c0393-034b-495b-8ab1-f3011c558a4d</Guid>\n" +
                        "</DeviceConfigReference>\n" +
                        "<ROIConfigReference>\n" +
                        "<Name>10x40NaI(Tl)_Sensl</Name>\n" +
                        "<Guid>63afa7cf-0dc5-44d7-8933-535c84c4c18c</Guid>\n" +
                        "</ROIConfigReference>\n" +
                        "<BackgroundSpectrumFile />\n" +
                        "<StartTime>" + startTime + "</StartTime>\n" +
                        "<EndTime>" + endTime + "</EndTime>\n" +
                        "<PresetTime>" + round(tmpTime) + "</PresetTime>\n" +
                        "<EnergySpectrum>\n" +
                        "<NumberOfChannels>1024</NumberOfChannels>\n" +
                        "<ChannelPitch>0.0221</ChannelPitch>\n" +
                        "<EnergyCalibration>\n" +
                        "<PolynomialOrder>2</PolynomialOrder>\n" +
                        "<Coefficients>\n" +  //correctA
                        //"<Coefficient>6.03265776105158</Coefficient>\n" +
                        //"<Coefficient>1.99778118743629</Coefficient>\n" +
                        //"<Coefficient>0.0025257686806495</Coefficient>\n" +
                        "<Coefficient>" + correctC + "</Coefficient>\n" +
                        "<Coefficient>" + correctB + "</Coefficient>\n" +
                        "<Coefficient>" + correctA + "</Coefficient>\n" +
                        "</Coefficients>\n" +
                        "</EnergyCalibration>\n" +
                        "<ValidPulseCount>" + round(pulseSumm) + "</ValidPulseCount>\n" +
                        "<TotalPulseCount>" + round(pulseSumm) + "</TotalPulseCount>\n" +
                        "<MeasurementTime>" + round(tmpTime2) + "</MeasurementTime>\n" +
                        "<NumberOfSamples>0</NumberOfSamples>\n" +
                        "<Spectrum>\n";
                outputStream.write(dataStr.getBytes());

                for (int i = 0; i < maxCanal / 2; i++) {
                    dataStr = "<DataPoint>" + String.format("%.0f", resultData[i]) + "</DataPoint>\n";
                    outputStream.write(dataStr.getBytes());                            // Write to file
                }
                dataStr = (String) "</Spectrum>\n" +
                        "</EnergySpectrum>\n" +
                        "<Visible>true</Visible>\n" +
                        "<PulseCollection>\n" +
                        "<Format>Base64 encoded binary</Format>\n" +
                        "<Pulses />\n" +
                        "</PulseCollection>\n" +
                        "</ResultData>\n" +
                        "</ResultDataList>\n" +
                        "</ResultDataFile>\n";
                outputStream.write(dataStr.getBytes());

                outputStream.close();
            } catch (Exception e) {
                //e.printStackTrace();
                toast = Toast.makeText(getApplicationContext(),"Ошибка. " + e.getMessage(), Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }
}

