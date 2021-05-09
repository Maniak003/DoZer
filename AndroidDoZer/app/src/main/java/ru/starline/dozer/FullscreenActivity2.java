package ru.starline.dozer;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class FullscreenActivity2 extends AppCompatActivity {
    public Props PP;
    public  scanLE sLE = new scanLE();
    public TextView editTextMAC;
    public TextView editTextKoefR;
    public TextView editTextLevel1;
    public TextView editTextLevel2;
    public TextView editTextLevel3;
    public TextView editTextCorrect_A;
    public TextView editTextCorrect_B;
    public TextView editTextCorrect_C;
    public TextView editTextEnergi_A;
    public TextView editTextEnergi_B;
    public TextView editTextEnergi_C;
    public TextView editTextEnergi_D;
    public CheckBox checkBoxLevel1_S;
    public CheckBox checkBoxLevel1_V;
    public CheckBox checkBoxLevel2_S;
    public CheckBox checkBoxLevel2_V;
    public CheckBox checkBoxLevel3_S;
    public CheckBox checkBoxLevel3_V;
    public CheckBox checkBoxLed;
    public CheckBox checkBoxSound;
    public RadioButton radioButtonResolution1;
    public RadioButton radioButtonResolution2;
    public RadioButton radioButtonResolution3;
    public Intent intent;
    public int propBitData;

    private View mContentView;

    private void closeActivity() {
        this.finish();
    }
    private void formatLayoutSet() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mContentView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE
              | View.SYSTEM_UI_FLAG_FULLSCREEN
              | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
              | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
              | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
              | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen2);
        mContentView = findViewById(R.id.mainSetup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        PP = new Props();
        editTextMAC = findViewById(R.id.editTextMAC);
        editTextKoefR = findViewById(R.id.editTextKoefR);
        editTextLevel1 = findViewById(R.id.editTextLevel1);
        editTextLevel2 = findViewById(R.id.editTextLevel2);
        editTextLevel3 = findViewById(R.id.editTextLevel3);
        editTextCorrect_A = findViewById(R.id.editTextCorrect_A);
        editTextCorrect_B = findViewById(R.id.editTextCorrect_B);
        editTextCorrect_C = findViewById(R.id.editTextCorrect_C);
        editTextEnergi_A = findViewById(R.id.editTextEnergi_A);
        editTextEnergi_B = findViewById(R.id.editTextEnergi_B);
        editTextEnergi_C = findViewById(R.id.editTextEnergi_C);
        editTextEnergi_D = findViewById(R.id.editTextEnergi_D);
        checkBoxLevel1_S = findViewById(R.id.checkBoxLevel1_S);
        checkBoxLevel1_V= findViewById(R.id.checkBoxLevel1_V);
        checkBoxLevel2_S = findViewById(R.id.checkBoxLevel2_S);
        checkBoxLevel2_V= findViewById(R.id.checkBoxLevel2_V);
        checkBoxLevel3_S = findViewById(R.id.checkBoxLevel3_S);
        checkBoxLevel3_V = findViewById(R.id.checkBoxLevel3_V);
        checkBoxLed = findViewById(R.id.checkBoxLed);
        checkBoxSound = findViewById(R.id.checkBoxSound);
        radioButtonResolution1 = findViewById(R.id.radioButtonResolution1);
        radioButtonResolution2 = findViewById(R.id.radioButtonResolution2);
        radioButtonResolution3 = findViewById(R.id.radioButtonResolution3);


        // Scan BLE button
        final Button scanBLEBTN = findViewById(R.id.scanBLEBTN);
        if (scanBLEBTN != null) {
            scanBLEBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DoZer", "Pressed setup save & exit.");
                    if (sLE.scanRunning ) {
                        sLE.stopScanLE();
                    } else {
                        sLE.startScanLE();
                    }
                }
            });
        } else {
            Log.d("DoZer", "scanBLEBTN not found");
        }

        // Save & Exit button
        final Button saveExitBtn = findViewById(R.id.saveSetupBtn);
        if (saveExitBtn != null) {
            saveExitBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.d("DoZer", "Pressed setup save & exit.");
                    try {
                        PP.writeProp();  // Write to config
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (sLE.scanRunning ) {
                        sLE.stopScanLE();
                    }
                    closeActivity();
                }
            });
        } else {
            Log.d("DoZer", "exitBtn not found");
        }

        // Cancel button
        final Button cancelBtn = findViewById(R.id.cancelBtn);
        if (cancelBtn != null) {
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.d("DoZer", "Pressed setup cancel.");
                    if (sLE.scanRunning ) {
                        sLE.stopScanLE();
                    }
                    closeActivity();
                }
            });
        } else {
            Log.d("DoZer", "exitBtn not found");
        }
        // Read parameters from config file.
        try {
            String tmpData;
            Log.d("DoZer", "Read parameters.");
            // Read MAC from config
            tmpData = PP.readProp("MAC");
            if (tmpData == null) {
                editTextMAC.setText("20:06:11:11:66:CD");
            } else {
                editTextMAC.setText(tmpData);
            }
            // Read uR/h on 1 pulse from config
            tmpData = PP.readProp("koefR");
            if (tmpData == null) {
                editTextKoefR.setText("0.5310015898"); // For original DoZer NaI(Tl) 10x40 + FC60035
            } else {
                editTextKoefR.setText(tmpData);
            }
            editTextLevel1.setText(PP.readProp("Level1"));
            editTextLevel2.setText(PP.readProp("Level2"));
            editTextLevel3.setText(PP.readProp("Level3"));
            // Correction data
            tmpData = PP.readProp("Correct_A");
            if (tmpData == null) {
                editTextCorrect_A.setText("0.0025257686806495"); // For original DoZer NaI(Tl) 10x40 + FC60035
            } else {
                editTextCorrect_A.setText(tmpData);
            }
            tmpData = PP.readProp("Correct_B");
            if (tmpData == null) {
                editTextCorrect_B.setText("1.99778118743629"); // For original DoZer NaI(Tl) 10x40 + FC60035
            } else {
                editTextCorrect_B.setText(tmpData);
            }
            tmpData = PP.readProp("Correct_C");
            if (tmpData == null) {
                editTextCorrect_C.setText("6.03265776105158"); // For original DoZer NaI(Tl) 10x40 + FC60035
            } else {
                editTextCorrect_C.setText(tmpData);
            }

            editTextEnergi_A.setText(PP.readProp("Energi_A"));
            editTextEnergi_B.setText(PP.readProp("Energi_B"));
            editTextEnergi_C.setText(PP.readProp("Energi_C"));
            tmpData = PP.readProp("Energi_D");
            if (tmpData == null) {
                editTextEnergi_D.setText("1");
            } else {
                editTextEnergi_D.setText(tmpData);
            }
            tmpData = PP.readProp("Level1_S");
            if (tmpData != null) {
                checkBoxLevel1_S.setChecked(tmpData.equals("1"));
            }
            tmpData = PP.readProp("Level1_V");
            if (tmpData != null) {
                checkBoxLevel1_V.setChecked(tmpData.equals("1"));
            }
            tmpData = PP.readProp("Level2_S");
            if (tmpData != null) {
                checkBoxLevel2_S.setChecked(tmpData.equals("1"));
            }
            tmpData = PP.readProp("Level2_V");
            if (tmpData != null) {
                checkBoxLevel2_V.setChecked(tmpData.equals("1"));
            }
            tmpData = PP.readProp("Level3_S");
            if (tmpData != null) {
                checkBoxLevel3_S.setChecked(tmpData.equals("1"));
            }
            tmpData = PP.readProp("Level3_V");
            if (tmpData != null) {
                checkBoxLevel3_V.setChecked(tmpData.equals("1"));
            }
            tmpData = PP.readProp("LED");
            if (tmpData != null) {
                checkBoxLed.setChecked(tmpData.equals("1"));
            }
            tmpData = PP.readProp("Sound");
            if (tmpData != null) {
                checkBoxSound.setChecked(tmpData.equals("1"));
            }
            tmpData = PP.readProp("Resolution");
            if (tmpData != null) {
                if (tmpData.equals("3")) {
                    radioButtonResolution3.setChecked(true);
                } else if (tmpData.equals("2")) {
                    radioButtonResolution2.setChecked(true);
                } else {
                    radioButtonResolution1.setChecked(true);
                }
            }
            intent = new Intent(this, FullscreenActivity.class);
        } catch (IOException e) {
            Log.d("Dozer", "Error message: " + e.getMessage());
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        formatLayoutSet();
    }
    public class Props {
        public  String readProp(String key) throws IOException {
            String fname = "device.properties";
            Properties prop = new Properties();
            FileInputStream fis = null;
            fis = openFileInput(fname);
            prop.load(fis);
            return prop.getProperty(key);
        }

        //
        // Save configuration data
        //
        public void writeProp() throws IOException {
            propBitData = 0;
            Properties prop = new Properties();
            FileOutputStream fileOutputStream;
            fileOutputStream = openFileOutput("device.properties", MODE_PRIVATE);

            prop.setProperty("MAC", editTextMAC.getText().toString());
            prop.setProperty("koefR", editTextKoefR.getText().toString());
            prop.setProperty("Level1", editTextLevel1.getText().toString());
            prop.setProperty("Level2", editTextLevel2.getText().toString());
            prop.setProperty("Level3", editTextLevel3.getText().toString());
            prop.setProperty("Correct_A", editTextCorrect_A.getText().toString());
            prop.setProperty("Correct_B", editTextCorrect_B.getText().toString());
            prop.setProperty("Correct_C", editTextCorrect_C.getText().toString());
            prop.setProperty("Energi_A", editTextEnergi_A.getText().toString());
            prop.setProperty("Energi_B", editTextEnergi_B.getText().toString());
            prop.setProperty("Energi_C", editTextEnergi_C.getText().toString());
            prop.setProperty("Energi_D", editTextEnergi_D.getText().toString());
            if (checkBoxLevel1_S.isChecked()) {
                prop.setProperty("Level1_S", "1");
                propBitData = propBitData + 1;
            } else {
                prop.setProperty("Level1_S", "0");
            }
            if (checkBoxLevel1_V.isChecked()) {
                prop.setProperty("Level1_V", "1");
                propBitData = propBitData + 2;
            } else {
                prop.setProperty("Level1_V", "0");
            }

            if (checkBoxLevel2_S.isChecked()) {
                prop.setProperty("Level2_S", "1");
                propBitData = propBitData + 4;
            } else {
                prop.setProperty("Level2_S", "0");
            }
            if (checkBoxLevel2_V.isChecked()) {
                prop.setProperty("Level2_V", "1");
                propBitData = propBitData + 8;
            } else {
                prop.setProperty("Level2_V", "0");
            }

            if (checkBoxLevel3_S.isChecked()) {
                prop.setProperty("Level3_S", "1");
                propBitData = propBitData + 16;
            } else {
                prop.setProperty("Level3_S", "0");
            }
            if (checkBoxLevel3_V.isChecked()) {
                prop.setProperty("Level3_V", "1");
                propBitData = propBitData + 32;
            } else {
                prop.setProperty("Level3_V", "0");
            }

            if (checkBoxLed.isChecked()) {
                prop.setProperty("LED", "1");
                propBitData = propBitData + 64;
            } else {
                prop.setProperty("LED", "0");
            }

            if (checkBoxSound.isChecked()) {
                prop.setProperty("Sound", "1");
                propBitData = propBitData + 128;
            } else {
                prop.setProperty("Sound", "0");
            }
            if (radioButtonResolution1.isChecked()) {
                prop.setProperty("Resolution", "1");
                propBitData = propBitData + 256;
            } else  if (radioButtonResolution2.isChecked()) {
                prop.setProperty("Resolution", "2");
                propBitData = propBitData + 512;
            } else {
                prop.setProperty("Resolution", "3");
                propBitData = propBitData + 768;
            }
            /*
            00  -- Level 1 sound 1
            01  -- Level 1 vibro 2
            02  -- Level 2 sound 4
            03  -- Level 2 vibro 8
            04  -- Level 3 sound 16
            05  -- Level 3 vibro 32
            06  -- LED 64
            07  -- Sound 128
            08  -- 1 - 1024, 2 - 2048, 3 - 4096
            09  --
            10
            11
            12
            13
            14
            15
            16
             */
            prop.store(fileOutputStream, null);
            Intent intent = new Intent();
            int[] tmpData = new int[4];
            tmpData[0] = propBitData; // Flags
            if (editTextLevel1.getText().toString().isEmpty()) {
                tmpData[1] = 0;
            } else {
                tmpData[1] = Integer.parseInt(editTextLevel1.getText().toString());
            }
            if (editTextLevel2.getText().toString().isEmpty()) {
                tmpData[2] = 0;
            } else {
                tmpData[2] = Integer.parseInt(editTextLevel2.getText().toString());
            }
            if (editTextLevel3.getText().toString().isEmpty()) {
                tmpData[3] = 0;
            } else {
                tmpData[3] = Integer.parseInt(editTextLevel3.getText().toString());
            }
            intent.putExtra("CFGDATA1", tmpData);

            float tmpdata2;
            if (editTextKoefR.getText().toString().isEmpty()) {
                tmpdata2 = 1;
            } else {
                tmpdata2 = Float.parseFloat(editTextKoefR.getText().toString());
            }
            intent.putExtra("CFGDATA5", tmpdata2);
            setResult(1, intent);
        }
    }
    class scanLE {
        private BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        private BluetoothLeScanner scanner = adapter.getBluetoothLeScanner();
        private List<ScanFilter> filters;
        public boolean scanRunning = false;

        public void stopScanLE() {
            if (scanner != null) {
                scanner.stopScan(scanCallback);
                scanRunning = false;
            }
        }

        public void startScanLE() {
            ScanSettings scanSettings = new ScanSettings.Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                    //.setCallbackType(ScanSettings.CALLBACK_TYPE_FIRST_MATCH)
                    .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
                    .setMatchMode(ScanSettings.MATCH_MODE_AGGRESSIVE)
                    .setNumOfMatches(ScanSettings.MATCH_NUM_ONE_ADVERTISEMENT)
                    .setReportDelay(0L)
                    .setPhy(ScanSettings.PHY_LE_ALL_SUPPORTED)  // Need for scan BLE
                    .build();

            filters = null;
            if(scanner !=null) {
                scanRunning = true;
                scanner.startScan(filters, scanSettings, scanCallback);
                Log.d("DoZer", "Scan started.");
            }  else

            {
                Log.e("DoZer", "could not get scanner object");
            }
        }

        private final ScanCallback scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                BluetoothDevice device = result.getDevice();
                String devName = device.getName();
                if (devName != null && devName.equals("DoZer")) {
                    editTextMAC.setText(device.getAddress());
                    stopScanLE();
                    Log.d("DoZer", "---------------------scan finished-----------------");
                    Log.d("DoZer", "Dev: " + device.getName() + " Addr: " + device.getAddress());
                }
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                Log.d("DoZer", "---------------------scan result-----------------");
            }

            @Override
            public void onScanFailed(int errorCode) {
                Log.d("DoZer", "---------------------scan failed-----------------");
            }
        };
    }

}