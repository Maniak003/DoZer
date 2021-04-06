package ru.starline.ble;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {
    public View myView;
    public TextView textCount;
    public boolean connected = false;
    public int HSize, WSize;
    public double oldCounts = 0, specrtCRC;
    public float curentTime, tmpTime, countsAll1;
    public String TAG = "!!!!! BLE report : ", FLAG = "";
    //public String TAG1 = " ";
    public getBluetooth BT;
    public BluetoothGatt gatt;
    public byte[] spectrData = new byte[4096];
    public int findDataSize = 512, maxCanal = 2055;
    /* uR/h -- on 1 cps */
    //public float koeffR = (float) 0.5378061767;
    public float koeffR = (float) 0.5310015898;
    public float[] findData = new float[findDataSize];
    public float tmpFindData, Trh1 = 40, Trh2 = 100;
    public int startFlag = 0, bufferIndex = 0;
    drawHistogram DH = new drawHistogram();
    scanLE SLE = new scanLE();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        myView = new DrawView(this);

        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (event.getX() < 1100) {
                        DH.saveHistogramXML(); // сохранение данных в файл
                    } else {
                        try {
                            DH.resetAll();  // Очистка всех данных
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return true;
            }
        });
        /*
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DH.saveHistogram();
            }
        });

         */

        // Очищаем массив для поиска
        for ( int i = 0; i < findDataSize; i++) {
            findData[i] = 0;
        }
        setContentView(myView);
        BT = new getBluetooth();
        BT.initLeDevice();
        intervalTimer tm = new intervalTimer();
        tm.startTimer();
        // Убрать ActionBar
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );

         */
    }
    /*
    Настройка таймеров
    */
    class intervalTimer {
        Timer timer = new Timer();
        TimerTask mTimerTask = new MyTimerTask();
        public void startTimer() {
            // С задержкой 5 сек, с интервалом 20 сек.
            timer.schedule(mTimerTask, 5000, 20000);
        }
    }
    /*
        Задание выполняемое по таймеру
        Проверка состояния BT подключения.
     */
    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
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
Device Name: JDY-23
Device Address: 20:07:12:18:74:9E

Services:--------------------------
GAP (00001800-0000-1000-8000-00805F9B34FB)
Device Name (00002A00-0000-1000-8000-00805F9B34FB)
Appearance (00002A01-0000-1000-8000-00805F9B34FB)
Peripheral Privacy Flag (00002A02-0000-1000-8000-00805F9B34FB)
Peripheral Preferred Connection Parameters (00002A04-0000-1000-8000-00805F9B34FB)

GATT (00001801-0000-1000-8000-00805F9B34FB)

    mServiceUuidtry1 = "0000FFE0-0000-1000-8000-00805F9B34FB";
    mRxUuidtry1 = "0000FFE2-0000-1000-8000-00805F9B34FB";
    mTxUuidtry1 = "0000FFE1-0000-1000-8000-00805F9B34FB";

    mServiceUuidtry2 = "6E400001-B5A3-F393-E0A9-E50E24DCCA9E";
    mRxUuidtry2 = "6E400002-B5A3-F393-E0A9-E50E24DCCA9E";
    mTxUuidtry2 = "6E400003-B5A3-F393-E0A9-E50E24DCCA9E";

Unknown service (0000FFE0-0000-1000-8000-00805F9B34FB)
Unknown characteristic (0000FFE2-0000-1000-8000-00805F9B34FB)
Unknown characteristic (0000FFE1-0000-1000-8000-00805F9B34FB)

"00002A19-0000-1000-8000-00805F9B34FB", "Battery Level"
Unknown service (0000180F-0000-1000-8000-00805F9B34FB)
Unknown characteristic (00002A19-0000-1000-8000-00805F9B34FB)
*/
    class scanLE {
        public void scnLE() {
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothLeScanner scanner = adapter.getBluetoothLeScanner();

            //String[] peripheralAddresses = new String[]{"20:06:12:09:74:3E"};
            //List<ScanFilter> filters = new ArrayList<>();
            //for (String address : peripheralAddresses) {
            //    ScanFilter filter = new ScanFilter.Builder().setDeviceAddress(address).build();
            //    filters.add(filter);
            //}
            String[] names = new String[]{"DoZer"};
            List<ScanFilter> filters = null;
            if (names != null) {
                filters = new ArrayList<>();
                for (String name : names) {
                    ScanFilter filter = new ScanFilter.Builder().setDeviceName(name).build();
                    filters.add(filter);
                }
            }

            ScanSettings scanSettings = new ScanSettings.Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                    .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
                    .setMatchMode(ScanSettings.MATCH_MODE_AGGRESSIVE)
                    .setNumOfMatches(ScanSettings.MATCH_NUM_ONE_ADVERTISEMENT)
                    .setReportDelay(0L)
                    .build();

            if(scanner !=null) {
                scanner.startScan(filters, scanSettings, scanCallback);
                Log.d(TAG, "Scan started.");
            }  else

            {
                Log.e(TAG, "could not get scanner object");
            }
        }

        private final ScanCallback scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                BluetoothDevice device = result.getDevice();
                Log.d(TAG, "---------------------scan finished-----------------");
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                Log.d(TAG, "---------------------scan result-----------------");
            }

            @Override
            public void onScanFailed(int errorCode) {
                Log.d(TAG, "---------------------scan failed-----------------");
            }
        };
    }

    class getBluetooth {
        Context context = getApplicationContext();
        public BluetoothAdapter bluetooth;
        private BluetoothSocket btSocket = null;
        //private String MAC = "20:07:12:18:74:9E";
        //private String MAC = "20:06:03:20:02:A9";
        //private String MAC = "20:06:03:20:02:B3";
        //private String MAC = "20:06:12:09:74:3E"; // F103
        //private String MAC = "A4:C1:38:05:49:8E";
        private String MAC = "20:06:11:11:66:CD"; // L412
        public BluetoothDevice device;
        private BluetoothGattCharacteristic readCharacteristic, writeCharacteristic;
        private boolean canceled;
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
        //public final UUID SPP_UUID                    = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        //public final UUID BLUETOOTH_LE_NRF_SERVICE    = UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e");
        //public final UUID BLUETOOTH_LE_NRF_CHAR_RW2   = UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e"); // read on microbit, write on adafruit
        //public final UUID BLUETOOTH_LE_NRF_CHAR_RW3   = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e");
        //public final UUID BLUETOOTH_LE_RN4870_SERVICE = UUID.fromString("49535343-FE7D-4AE5-8FA9-9FAFD205E455");
        //public final UUID BLUETOOTH_LE_RN4870_CHAR_RW = UUID.fromString("49535343-1E4D-4BD9-BA61-23C647249616");

        //public final UUID BLUETOOTH_LE_TIO_SERVICE          = UUID.fromString("0000FEFB-0000-1000-8000-00805F9B34FB");
        //public final UUID BLUETOOTH_LE_TIO_CHAR_TX          = UUID.fromString("00000001-0000-1000-8000-008025000000"); // WNR
        //public final UUID BLUETOOTH_LE_TIO_CHAR_RX          = UUID.fromString("00000002-0000-1000-8000-008025000000"); // N
        //public final UUID BLUETOOTH_LE_TIO_CHAR_TX_CREDITS  = UUID.fromString("00000003-0000-1000-8000-008025000000"); // W
        //public final UUID BLUETOOTH_LE_TIO_CHAR_RX_CREDITS  = UUID.fromString("00000004-0000-1000-8000-008025000000"); // I

        //public final static String ACTION_GATT_CONNECTED = "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
        //public final static String ACTION_GATT_DISCONNECTED = "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
        //public final static String ACTION_GATT_SERVICES_DISCOVERED = "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
        //public final static String ACTION_DATA_AVAILABLE = "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
        //public final static String EXTRA_DATA = "com.example.bluetooth.le.EXTRA_DATA";

        //private static final int STATE_DISCONNECTED = 0;
        //private static final int STATE_CONNECTING = 1;
        //private static final int STATE_CONNECTED = 2;

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
            gatt.disconnect();
            gatt.close();
            if (delegate != null)
                delegate.disconnect();
            delegate = null;
            device = null;
            writeBuffer.clear();
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
                return;
            }
            device = bluetooth.getRemoteDevice(MAC);  // Подключаемся по MAC адресу.
            Log.d(TAG, "Статус: " + bluetooth.getState());
            if ( device == null ) {
                Log.i(TAG, "Device: " + TAG + " not connected.");
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
                    myView.invalidate();
                    writePending = false;
                    Log.i(TAG, "Disconnect.");
                }
            }

            // Сюда попадаем после выполнения gatt.discoverServices
            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                Log.d(TAG, "servicesDiscovered, status " + status);
                if (canceled)
                    return;
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
                if(canceled)
                    return;
                if(descriptor.getCharacteristic() == readCharacteristic) {
                    Log.d(TAG,"writing read characteristic descriptor finished, status=" + status);
                    if (status != BluetoothGatt.GATT_SUCCESS) {
                        Log.d(TAG,"Write characteristic failed.");
                    } else {
                        connected = true;
                        myView.invalidate();
                        Log.d(TAG, "Write descriptor Ok.");
                    }
                }
            }
            @Override
            public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                if (canceled || !connected || writeCharacteristic == null)
                    return;
                if (status != BluetoothGatt.GATT_SUCCESS) {
                    return;
                }
                delegate.onCharacteristicWrite(gatt, characteristic, status);
                if (canceled)
                    return;
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
                if(canceled)
                    return;
                delegate.onCharacteristicChanged(gatt, characteristic);
                if(canceled)
                    return;
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
                                    if (bufferIndex < 2080) {
                                        specrtCRC = specrtCRC + (char) (data[i2] & 0xFF);   // CRC
                                    }
                                    spectrData[bufferIndex++] = (byte) (data[i2] & 0xFF);
                                    if (bufferIndex == 2082) {     //Transmission complete.
                                        startFlag = 0;
                                        double tmpCRC = (char) (spectrData[2080] << 8 | (spectrData[2081] & 0xFF));
                                        specrtCRC = specrtCRC - (Math.floor(specrtCRC / 65536) * 65536);     // Facking Java
                                        //Log.i(TAG, "tmpCRC : " + tmpCRC + ", spectrCRC : " + specrtCRC + ", diff : " + (tmpCRC - specrtCRC));
                                        if (tmpCRC == specrtCRC) { // Update if CRC correct.
                                            /* Debug */
                                            //tmpTime = (char) (spectrData[0] << 8 | (spectrData[1] & 0xff)); // Total time from device.
                                            //tmpTime = tmpTime + ((char) (spectrData[2] << 8 | (spectrData[3] & 0xff)) * 65536);
                                            //Log.i(TAG, "{0, 1, 2, 3} : " + (spectrData[0]  + spectrData[1] + spectrData[2] + spectrData[3]) + ", Time: " + tmpTime);
                                            myView.invalidate();    // Redraw screen.
                                        }
                                    }
                            }
                        }
                    }
                }
            }
        };

        /*
                Этими парамертрами корректно инициализируется JDY-23, JDY-10
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
                    Toast.makeText(getApplicationContext(), "Нет поддержки Bluetooth BLE.", Toast.LENGTH_LONG).show();
                }
            }
        }

        /*
         *  Передача данных
         */
        void write(byte[] data) throws IOException {
            if(canceled || !connected || writeCharacteristic == null)
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
                    for(int i = 1; i < (data.length + payloadSize - 1)/payloadSize; i++) {
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
    class DrawView extends View {
        public DrawView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            HSize = canvas.getHeight();
            WSize = canvas.getWidth();
            //Log.i(TAG,"onDraw flag: " + FLAG);
            DH.writeHistogram(canvas);
            if ( connected ) {
                DH.connectIndicator(canvas, 0);
            } else {
                oldCounts = 0;
                DH.connectIndicator(canvas, 1);
            }
        }
    }

    class drawHistogram {
        double countsAll, interval;
        //char ; /* Unsigned short. Долбаная Java */
        float maxPoint, mastab, tmpVal, mastabLog, maxPointLog, penSize = 2, pen2Size = 1, pen3Size = 1, hsizeFindData = 100;
        private Paint p = new Paint(), pLog = new Paint(), pText = new Paint(), pTextR1 = new Paint(), pTextR2 = new Paint(), pTextR3 = new Paint(), pInd = new Paint(), pFindData = new Paint(), pFindData1 = new Paint(), pFindData2 = new Paint();

        // Перерисовка индикатора подключения.
        public void connectIndicator(Canvas canvas, int cl) {
            if ( cl == 0 ) {
                pInd.setColor(Color.argb(255, 0, 255, 0));
            } else {
                pInd.setColor(Color.argb(255, 255, 0, 0));
            }
            pInd.setStrokeWidth(pen2Size);
            canvas.drawCircle(WSize - 20, 20, 6, pInd);
        }

        // Обнуление данных в приборе
        public void resetAll() throws IOException {
            for ( int i = 0; i < findDataSize; i++) {
                findData[i] = 0;
            }
            byte[] sndData = new byte[1];
            sndData[0] = 'C';
            BT.write(sndData);
        }


        //  Сохранение гистограммы CSV format
        public void saveHistogram() {
            String dataStr = null, fileName;
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'DoZer_'yyyy-MM-dd'_'HH:mm:ss");
            fileName = simpleDateFormat.format(now);
            Toast toast = Toast.makeText(getApplicationContext(),"Сохранено.", Toast.LENGTH_SHORT);
            try {
                File direct = new File(Environment.getExternalStorageDirectory()+"/DoZer");
                if(!direct.exists()) {
                    if(direct.mkdir()); // Создаем каталог если его нет;
                }
                /*
                 * Создается объект файла, при этом путь к файлу находиться методом класcа Environment
                 * Обращение идёт, как и было сказано выше к внешнему накопителю
                 */
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
                                + " Measurement time: " + Math.round(tmpTime2);
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

        //  Сохранение гистограммы XML BqMonitor format
        public void saveHistogramXML() {
            String dataStr, fileName, startTime, endTime;
            double sTime;
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'_'HHmmss");
            fileName = simpleDateFormat.format(now);
            Toast toast = Toast.makeText(getApplicationContext(),"Сохранено.", Toast.LENGTH_SHORT);
            try {
                File direct = new File(Environment.getExternalStorageDirectory()+"/DoZer");
                if(!direct.exists()) {
                    if(direct.mkdir()); // Создаем каталог если его нет;
                }
                /*
                 * Создается объект файла, при этом путь к файлу находиться методом класcа Environment
                 * Обращение идёт, как и было сказано выше к внешнему накопителю
                 */
                File myFile = new File(Environment.getExternalStorageDirectory().toString() + "/DoZer/" + fileName + ".xml");
                myFile.createNewFile();                                         // Создается файл, если он не был создан
                FileOutputStream outputStream = new FileOutputStream(myFile);   // После чего создаем поток для записи

                float tmpTime2 = (char) (spectrData[0] << 8 | (spectrData[1] & 0xff)); // Total time from device.
                tmpTime2 = tmpTime2 + ((char) (spectrData[2] << 8 | (spectrData[3] & 0xff)) * 65536);
                double pulseSumm = 0;
                String locationStr = " Unknown.";
                            /*
                Определение GPS координат
             */
                try {
                    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    if (lm != null) {
                        @SuppressLint("MissingPermission") Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null) {
                            locationStr = " Lat: " + loc.getLatitude() + " Lng: " + loc.getLongitude()
                                    + " Alt: " + loc.getAltitude() + " Speed: " + loc.getSpeed();
                        } else {
                            Toast.makeText(getBaseContext(), "Ошибка получения координат.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getBaseContext(), "Ошибка создания LocationManager.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Ошибка получения и записи координат." + e.getMessage(), Toast.LENGTH_LONG).show();
                }

                simpleDateFormat = new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SZZZZZ");
                startTime = simpleDateFormat.format(now);
                sTime = System.currentTimeMillis() - tmpTime2 * 1000;
                startTime = simpleDateFormat.format(sTime);
                endTime = simpleDateFormat.format(now);

                for (int i = 8; i < maxCanal; i++) {
                    pulseSumm = pulseSumm + (float) (spectrData[i] << 8 | (spectrData[++i] & 0xff));
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
                        "<PresetTime>" + Math.round(tmpTime) + "</PresetTime>\n" +
                        "<EnergySpectrum>\n" +
                        "<NumberOfChannels>1024</NumberOfChannels>\n" +
                        "<ChannelPitch>0.0221</ChannelPitch>\n" +
                        "<EnergyCalibration>\n" +
                        "<PolynomialOrder>2</PolynomialOrder>\n" +
                        "<Coefficients>\n" +
                        "<Coefficient>0</Coefficient>\n" +
                        "<Coefficient>3.11965811965812</Coefficient>\n" +
                        "<Coefficient>0</Coefficient>\n" +
                        "</Coefficients>\n" +
                        "</EnergyCalibration>\n" +
                        "<ValidPulseCount>" + Math.round(pulseSumm) + "</ValidPulseCount>\n" +
                        "<TotalPulseCount>" + Math.round(pulseSumm) + "</TotalPulseCount>\n" +
                        "<MeasurementTime>" + Math.round(tmpTime2) + "</MeasurementTime>\n" +
                        "<NumberOfSamples>0</NumberOfSamples>\n" +
                        "<Spectrum>\n";
                outputStream.write(dataStr.getBytes());

                for (int i = 8; i < maxCanal; i++) {
                    tmpVal = (char) (spectrData[i] << 8 | (spectrData[++i] & 0xff));
                    dataStr = "<DataPoint>" + String.format("%.0f", tmpVal) + "</DataPoint>\n";
                    outputStream.write(dataStr.getBytes());                            // и производим непосредственно запись
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
                e.printStackTrace();
                toast = Toast.makeText(getApplicationContext(),"Ошибка. " + e.getMessage(), Toast.LENGTH_SHORT);
            }
            toast.show();
        }


        //  Перерисовка графика
        public void writeHistogram(Canvas canvas) {
            //
            //  Вычисление масштабирования для линейного и логарифмического представления.
            //
            mastab = 1;
            mastabLog = 1;
            maxPoint = 1;
            maxPointLog = 0;
            for (int i = 8; i < maxCanal; i++) {
                tmpVal = (char) (spectrData[i] << 8 | (spectrData[++i] & 0xFF));
                if (tmpVal > maxPoint) {
                    maxPoint = tmpVal;
                }
                if (maxPointLog < (float) Math.log10(tmpVal)) {
                    maxPointLog = (float) Math.log10(tmpVal);
                }
            }
            mastab = (float) HSize / maxPoint;
            mastabLog = (float) HSize / maxPointLog;

            //
            //  Отрисовка графика с учетом масштаба.
            //
            // Фон
            canvas.drawColor(Color.argb(255, 0, 0, 0));
            // Линейная гистограмма
            p.setColor(Color.argb(200, 40, 40, 255));
            p.setStrokeWidth(penSize);
            // Логарифмическая гистограмма
            pLog.setColor(Color.argb(100, 40, 60, 255));
            pLog.setStrokeWidth(penSize);
            // Текст статистики
            pText.setColor(Color.argb(100, 255, 40, 255));
            pText.setStrokeWidth(penSize);
            pText.setTextSize(35.0f);
            // Текст значений в мкР/ч
            pTextR1.setColor(Color.argb(100, 40, 255, 40));
            pTextR1.setStrokeWidth(penSize);
            pTextR1.setTextSize(60.0f);
            pTextR2.setColor(Color.argb(100, 255, 255, 40));
            pTextR2.setStrokeWidth(penSize);
            pTextR2.setTextSize(60.0f);
            pTextR3.setColor(Color.argb(100, 255, 40, 40));
            pTextR3.setStrokeWidth(penSize);
            pTextR3.setTextSize(60.0f);
            // График дла поиска
            pFindData.setColor(Color.argb(200, 40, 255, 40));
            pFindData.setStrokeWidth(pen3Size);
            pFindData1.setColor(Color.argb(200, 255, 255, 40));
            pFindData1.setStrokeWidth(pen3Size);
            pFindData2.setColor(Color.argb(200, 255, 40, 40));
            pFindData2.setStrokeWidth(pen3Size);
            /*
                    Прорисовка гистограмм
             */
            countsAll = 0;
            countsAll1 = 0;
            for (int i = 8; i < 2080; i++) {
                tmpVal = (char) (spectrData[i] << 8 | (spectrData[++i] & 0xff));
                countsAll1 = countsAll1 + tmpVal;
                if ( i < maxCanal) {
                    float X = Math.round((i) / 2) * penSize - 2;
                    // В линейном представлении
                    canvas.drawLine(X, HSize - tmpVal * mastab, X, HSize, p);
                    // В логарифмическом представлении
                    canvas.drawLine(X, HSize - (float) Math.log10(tmpVal) * mastabLog, X, HSize, pLog);
                }
            }
            // Output total counts and cps.
            countsAll = (char) (spectrData[4] << 8 | (spectrData[5] & 0xff));  // Total counts from device
            countsAll = countsAll + ((char) (spectrData[6] << 8 | (spectrData[7] & 0xff)) * 65536);
            tmpTime = (char) (spectrData[0] << 8 | (spectrData[1] & 0xff)); // Total time from device.
            tmpTime = tmpTime + ((char) (spectrData[2] << 8 | (spectrData[3] & 0xff)) * 65536);
            //Log.i(TAG, "tmpTime : " + tmpTime + ", curentTime : " + curentTime + ", countsAll: " + countsAll + ", countsAll - oldCounts: " + (countsAll - oldCounts) + ", countsAll1: " + countsAll1);

            if (oldCounts <= 0 ) {
                oldCounts = countsAll;
                curentTime = tmpTime;
                //curentTime = System.currentTimeMillis() / 1000;
            } else {
                //interval = System.currentTimeMillis() / 1000 - curentTime;
                //curentTime = System.currentTimeMillis() / 1000;
                interval = tmpTime - curentTime;
                curentTime = tmpTime;
                mastab = 0;

                // Смещение графика поиска на одну позицию.
                tmpFindData = Math.round((countsAll - oldCounts) / interval);
                if (tmpFindData > 0) {
                    for (int i = findDataSize - 2; i >= 0; i--) {
                        if (mastab < findData[i])
                            mastab = findData[i];
                        findData[i + 1] = findData[i];
                    }
                    findData[0] = tmpFindData;
                    if (mastab < findData[0])
                        mastab = findData[0];
                    mastab = hsizeFindData / mastab;

                    // Перерисовка графика поиска
                    float X, Y;
                    for (int i = 0; i < findDataSize - 1; i++) {
                        X = (WSize - 20) - i * pen3Size;
                        Y = 190 - findData[i] * mastab;
                        if (190 - Y < 1) {
                            Y = 189;
                        }
                        if (findData[i] < Trh1) {
                            canvas.drawLine(X, Y, X, 190, pFindData);
                        } else if (findData[i] < Trh2) {
                            canvas.drawLine(X, Y, X, 190, pFindData1);
                        } else {
                            canvas.drawLine(X, Y, X, 190, pFindData2);
                        }
                    }

                    // Вывод статистики
                    float acps = 0;
                    X = WSize - 470;
                    if (tmpTime > 0) {
                        acps = (float) (countsAll / tmpTime);
                    }
                    canvas.drawText("total: " + Math.round(countsAll) + " cps: " + String.format("%.0f", findData[0]), X, 40, pText);
                    canvas.drawText("time: " + String.format("%.0f", tmpTime) + " avg: " + String.format("%.2f", acps), X, 80, pText);
                    /* Текущее значение */
                    if (findData[0] < Trh1) {
                        canvas.drawText("Now: " + String.format("%.1f", findData[0] * koeffR) + " uR/h", X - 50, 250, pTextR1);
                    } else if ( findData[0] < Trh2 ) {
                        canvas.drawText("Now: " + String.format("%.1f",findData[0] * koeffR) + " uR/h", X - 50, 250, pTextR2);
                    } else {
                        canvas.drawText("Now: " + String.format("%.1f",findData[0] * koeffR) + " uR/h", X - 50, 250, pTextR3);
                    }
                    /* Среднее значение */
                    if (acps < Trh1) {
                        canvas.drawText("Avg: " + String.format("%.2f", acps * koeffR) + " uR/h", X - 50, 310, pTextR1);
                    } else if (acps < Trh2) {
                        canvas.drawText("Avg: " + String.format("%.2f", acps * koeffR) + " uR/h", X - 50, 310, pTextR2);
                    } else {
                        canvas.drawText("Avg: " + String.format("%.2f", acps * koeffR) + " uR/h", X - 50, 310, pTextR3);

                    }
                    oldCounts = countsAll;
                } else {
                    oldCounts = countsAll;
                    //curentTime = System.currentTimeMillis() / 1000;
                    curentTime = tmpTime;
                }
            }
        }
    }
}

