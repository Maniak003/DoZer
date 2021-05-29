package ru.starline.dozer;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
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

public class FullscreenActivity2 extends AppCompatActivity  {
    public Props PP;
    //public ColorPicker cp;
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
    public TextView editTextBackgroundFile;
    public CheckBox checkBoxLevel1_S;
    public CheckBox checkBoxLevel1_V;
    public CheckBox checkBoxLevel2_S;
    public CheckBox checkBoxLevel2_V;
    public CheckBox checkBoxLevel3_S;
    public CheckBox checkBoxLevel3_V;
    public CheckBox checkBoxLed;
    public CheckBox checkBoxSound;
    public CheckBox smoothSpecter;
    public RadioButton BGNone;
    public RadioButton BGDiff;
    public RadioButton BGOver;
    public RadioButton radioButtonResolution1;
    public RadioButton radioButtonResolution2;
    public RadioButton radioButtonResolution3;
    public Button scanBLEBTN;
    public Button mainColor, buttonLogHistogram, buttonFonHistogram;
    public Intent intent;
    public int propBitData;
    public TextView smoothWindow;
    public int colorLineHistogram, colorLogHistogram, colorFoneHistogram;
    public View mContentView;

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
        editTextBackgroundFile = findViewById(R.id.editTextBackgroundFile);
        BGNone = findViewById(R.id.radioButtonBGNone);
        BGDiff = findViewById(R.id.radioButtonBGDiff);
        BGOver = findViewById(R.id.radioButtonBGOver);
        smoothSpecter = findViewById(R.id.checkBoxSmooth);
        smoothWindow = findViewById(R.id.editTextSmoothWindow);

        /* Color dialog */
        buttonFonHistogram = findViewById(R.id.buttonFonColor);
        if(buttonFonHistogram != null) {
            buttonFonHistogram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ColorPickerDialog CD = new ColorPickerDialog(FullscreenActivity2.this,
                            new ColorPickerDialog.OnColorChangedListener() {
                                @Override
                                public void colorChanged(String key, int color) {
                                    buttonFonHistogram.setBackgroundColor(color);
                                    colorFoneHistogram = color & 0xBFFFFFFF;
                                    formatLayoutSet();
                                }
                            }
                            , "10", colorFoneHistogram, colorFoneHistogram);
                    CD.show();
                }
            });
        }

        buttonLogHistogram = findViewById(R.id.buttonLogHistogram);
        if(buttonLogHistogram != null) {
            buttonLogHistogram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ColorPickerDialog CD = new ColorPickerDialog(FullscreenActivity2.this,
                            new ColorPickerDialog.OnColorChangedListener() {
                                @Override
                                public void colorChanged(String key, int color) {
                                    buttonLogHistogram.setBackgroundColor(color);
                                    colorLogHistogram = color & 0xC3FFFFFF;
                                    formatLayoutSet();
                                }
                            }
                            , "10", colorLogHistogram, colorLogHistogram);
                    CD.show();
                }
            });
        }

        mainColor = findViewById(R.id.buttonMainColor);
        if (mainColor != null) {
            mainColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public  void onClick(View v) {
                    Log.d("DoZer", "Main color clicked.");
                    ColorPickerDialog CD = new ColorPickerDialog(FullscreenActivity2.this,
                            new ColorPickerDialog.OnColorChangedListener() {
                                @Override
                                public void colorChanged(String key, int color) {
                                    mainColor.setBackgroundColor(color);
                                    colorLineHistogram = color;
                                    formatLayoutSet();
                                }
                            }
                            , "10", colorLineHistogram, colorLineHistogram);
                    CD.show();
                }
            });
        }

        // Scan BLE button
        scanBLEBTN = findViewById(R.id.scanBLEBTN);
        if (scanBLEBTN != null) {
            scanBLEBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DoZer", "Pressed scan BLE.");
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
                    if (sLE.scanRunning ) { // Stop scanning if active.
                        sLE.stopScanLE();
                    }
                    try {
                        PP.writeProp();  // Write to config
                    } catch (IOException e) {
                        e.printStackTrace();
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
                editTextMAC.setText("20:06:11:11:66:CD"); // My 1st BT module
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

            /* Colors */
            tmpData = PP.readProp("colorLinHhistogram");
            if (tmpData == null ) {
                colorLineHistogram = 0xFF2828FF;
            } else {
                colorLineHistogram = Integer.parseInt(tmpData);
            }
            mainColor.setBackgroundColor(colorLineHistogram);

            tmpData = PP.readProp("colorLogHhistogram");
            if (tmpData == null ) {
                colorLogHistogram = 0x64283CFF;
            } else {
                colorLogHistogram = Integer.parseInt(tmpData);
            }
            buttonLogHistogram.setBackgroundColor(colorLogHistogram);

            tmpData = PP.readProp("colorFoneHhistogram");
            if (tmpData == null ) {
                colorFoneHistogram = 0x6428FF28;
            } else {
                colorFoneHistogram = Integer.parseInt(tmpData);
            }
            buttonFonHistogram.setBackgroundColor(colorFoneHistogram);

            editTextEnergi_A.setText(PP.readProp("Energi_A"));
            editTextEnergi_B.setText(PP.readProp("Energi_B"));
            editTextEnergi_C.setText(PP.readProp("Energi_C"));
            smoothWindow.setText(PP.readProp("smoothWindow"));
            editTextBackgroundFile.setText(PP.readProp("BgrdFlName"));
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

            tmpData = PP.readProp("BgActive");
            Log.d("Dozer", "BGActive: " + tmpData);
            if (tmpData != null) {
                BGNone.setChecked(tmpData.equals("0"));
                BGDiff.setChecked(tmpData.equals("1"));
                BGOver.setChecked(tmpData.equals("2"));
            }

            tmpData = PP.readProp("Sound");
            if (tmpData != null) {
                checkBoxSound.setChecked(tmpData.equals("1"));
            }

            tmpData = PP.readProp("smoothSpectr");
            if (tmpData != null) {
                smoothSpecter.setChecked(tmpData.equals("1"));
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
            prop.setProperty("BgrdFlName", editTextBackgroundFile.getText().toString());
            prop.setProperty("colorLinHhistogram", String.valueOf(colorLineHistogram));
            prop.setProperty("colorLogHhistogram", String.valueOf(colorLogHistogram));
            prop.setProperty("colorFoneHhistogram", String.valueOf(colorFoneHistogram));
            if (BGNone.isChecked()) {
                prop.setProperty("BgActive", "0");
            } else {
                if (BGDiff.isChecked()) {  // Background radiation data active
                    prop.setProperty("BgActive", "1");  // BG different
                } else {
                    prop.setProperty("BgActive", "2");  // BG over
                }
            }
            prop.setProperty("smoothWindow", smoothWindow.getText().toString());
            if (smoothSpecter.isChecked()) {  // Smoothing specter
                prop.setProperty("smoothSpectr", "1");
            } else {
                prop.setProperty("smoothSpectr", "0");
            }

            if (checkBoxLevel1_S.isChecked()) {  // Sound for level 1
                prop.setProperty("Level1_S", "1");
                propBitData = propBitData + 1;
            } else {
                prop.setProperty("Level1_S", "0");
            }
            if (checkBoxLevel1_V.isChecked()) {  // Vibro for level 1
                prop.setProperty("Level1_V", "1");
                propBitData = propBitData + 2;
            } else {
                prop.setProperty("Level1_V", "0");
            }

            if (checkBoxLevel2_S.isChecked()) {  // Sound for level 2
                prop.setProperty("Level2_S", "1");
                propBitData = propBitData + 4;
            } else {
                prop.setProperty("Level2_S", "0");
            }
            if (checkBoxLevel2_V.isChecked()) {  // Vibro for level 2
                prop.setProperty("Level2_V", "1");
                propBitData = propBitData + 8;
            } else {
                prop.setProperty("Level2_V", "0");
            }

            if (checkBoxLevel3_S.isChecked()) {  // Sound for level 3
                prop.setProperty("Level3_S", "1");
                propBitData = propBitData + 16;
            } else {
                prop.setProperty("Level3_S", "0");
            }
            if (checkBoxLevel3_V.isChecked()) {  // Vibro for level 3
                prop.setProperty("Level3_V", "1");
                propBitData = propBitData + 32;
            } else {
                prop.setProperty("Level3_V", "0");
            }

            if (checkBoxLed.isChecked()) {  // LED active
                prop.setProperty("LED", "1");
                propBitData = propBitData + 64;
            } else {
                prop.setProperty("LED", "0");
            }

            if (checkBoxSound.isChecked()) {  // Sound active
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
            prop.store(fileOutputStream, null);
            fileOutputStream.close();

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

            String tmpdata3;
            if(editTextMAC.getText().toString().isEmpty()) {
                tmpdata3 = "20:06:11:11:66:CD";
            } else {
                tmpdata3 = editTextMAC.getText().toString();
            }
            intent.putExtra("CFGDATA6", tmpdata3);
            intent.putExtra("CFGDATA7", editTextBackgroundFile.getText().toString());
            intent.putExtra("CFGDATA10", smoothWindow.getText().toString());

            if (BGNone.isChecked()) {
                intent.putExtra("CFGDATA8", 0);
            } else {
                if (BGDiff.isChecked()) { // Active background radiation
                    intent.putExtra("CFGDATA8", 1);
                } else {
                    intent.putExtra("CFGDATA8", 2);
                }
            }

            if (smoothSpecter.isChecked()) {  // Smoothing specter
                intent.putExtra("CFGDATA9", 1);
            } else {
                intent.putExtra("CFGDATA9", 0);
            }

            /* Colors */
            intent.putExtra("CFGDATA11", colorLineHistogram);
            intent.putExtra("CFGDATA12", colorLogHistogram);
            intent.putExtra("CFGDATA13", colorFoneHistogram);

            setResult(1, intent);
        }
    }

    /* Scan BLE devices */
    class scanLE {
        private BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        private BluetoothLeScanner scanner = adapter.getBluetoothLeScanner();
        private List<ScanFilter> filters;
        public boolean scanRunning = false;

        public void stopScanLE() {
            scanBLEBTN.setTextColor(0xFF506C35);
            scanBLEBTN.setText("Scan");
            if (scanner != null) {
                scanner.stopScan(scanCallback);
                scanRunning = false;
            }
        }

        public void startScanLE() {
            scanBLEBTN.setTextColor(0xFFFF0000);
            scanBLEBTN.setText("Stop");
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
            } else {
                Log.e("DoZer", "could not get scanner object");
            }
        }

        private final ScanCallback scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                BluetoothDevice device = result.getDevice();
                String devName = device.getName();
                if (devName != null && devName.equalsIgnoreCase("DoZer")) {
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

    /*
        Color dialog
    */
    public static class ColorPickerDialog extends Dialog {

        public int HDSize = 600, WDSize = 785, HSize;
        public float kf = WDSize  / 256;
        public interface OnColorChangedListener {
            void colorChanged(String key, int color);
        }

        private OnColorChangedListener mListener;
        private int mInitialColor, mDefaultColor;
        private String mKey;



        private class ColorPickerView extends View {
            private Paint mPaint;
            private float mCurrentHue = 0;
            private int mCurrentX = 0, mCurrentY = 0;
            private int mCurrentColor, mDefaultColor;
            private final int[] mHueBarColors = new int[258];
            private int[] mMainColors = new int[65536];
            private OnColorChangedListener mListener;

            ColorPickerView(Context c, OnColorChangedListener l, int color, int defaultColor) {
                super(c);
                mListener = l;
                mDefaultColor = defaultColor;

                // Get the current hue from the current color and update the main
                // color field
                float[] hsv = new float[3];
                Color.colorToHSV(color, hsv);
                mCurrentHue = hsv[0];
                updateMainColors();

                mCurrentColor = color;

                // Initialize the colors of the hue slider bar
                int index = 0;
                for (float i = 0; i < 256; i += 256 / 42) // Red (#f00) to pink
                // (#f0f)
                {
                    mHueBarColors[index] = Color.rgb(255, 0, (int) i);
                    index++;
                }
                for (float i = 0; i < 256; i += 256 / 42) // Pink (#f0f) to blue
                // (#00f)
                {
                    mHueBarColors[index] = Color.rgb(255 - (int) i, 0, 255);
                    index++;
                }
                for (float i = 0; i < 256; i += 256 / 42) // Blue (#00f) to light
                // blue (#0ff)
                {
                    mHueBarColors[index] = Color.rgb(0, (int) i, 255);
                    index++;
                }
                for (float i = 0; i < 256; i += 256 / 42) // Light blue (#0ff) to
                // green (#0f0)
                {
                    mHueBarColors[index] = Color.rgb(0, 255, 255 - (int) i);
                    index++;
                }
                for (float i = 0; i < 256; i += 256 / 42) // Green (#0f0) to yellow
                // (#ff0)
                {
                    mHueBarColors[index] = Color.rgb((int) i, 255, 0);
                    index++;
                }
                for (float i = 0; i < 256; i += 256 / 42) // Yellow (#ff0) to red
                // (#f00)
                {
                    mHueBarColors[index] = Color.rgb(255, 255 - (int) i, 0);
                    index++;
                }

                // Initializes the Paint that will draw the View
                mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                mPaint.setTextAlign(Paint.Align.CENTER);
                mPaint.setTextSize(24);
            }

            // Get the current selected color from the hue bar
            private int getCurrentMainColor() {
                int translatedHue = 255 - (int) mCurrentHue;
                int index = 0;
                for (float i = 0; i < 256; i += 256 / 42) {
                    if (index == translatedHue)
                        return Color.rgb(255, 0, (int) i);
                    index++;
                }
                for (float i = 0; i < 256; i += 256 / 42) {
                    if (index == translatedHue)
                        return Color.rgb(255 - (int) i, 0, 255);
                    index++;
                }
                for (float i = 0; i < 256; i += 256 / 42) {
                    if (index == translatedHue)
                        return Color.rgb(0, (int) i, 255);
                    index++;
                }
                for (float i = 0; i < 256; i += 256 / 42) {
                    if (index == translatedHue)
                        return Color.rgb(0, 255, 255 - (int) i);
                    index++;
                }
                for (float i = 0; i < 256; i += 256 / 42) {
                    if (index == translatedHue)
                        return Color.rgb((int) i, 255, 0);
                    index++;
                }
                for (float i = 0; i < 256; i += 256 / 42) {
                    if (index == translatedHue)
                        return Color.rgb(255, 255 - (int) i, 0);
                    index++;
                }
                return Color.RED;
            }

            // Update the main field colors depending on the current selected hue
            private void updateMainColors() {
                int mainColor = getCurrentMainColor();
                int index = 0;
                int[] topColors = new int[256];
                for (int y = 0; y < 256; y++) {
                    for (int x = 0; x < 256; x++) {
                        if (y == 0) {
                            mMainColors[index] = Color.rgb(
                                    255 - (255 - Color.red(mainColor)) * x / 255,
                                    255 - (255 - Color.green(mainColor)) * x / 255,
                                    255 - (255 - Color.blue(mainColor)) * x / 255);
                            topColors[x] = mMainColors[index];
                        } else
                            mMainColors[index] = Color.rgb(
                                    (255 - y) * Color.red(topColors[x]) / 255,
                                    (255 - y) * Color.green(topColors[x]) / 255,
                                    (255 - y) * Color.blue(topColors[x]) / 255);
                        index++;
                    }
                }
            }

            @Override
            protected void onDraw(Canvas canvas) {
                //int translatedHue = 255 - (int) (mCurrentHue * 500 / 600);
                int translatedHue = 255 - (int) mCurrentHue, xx;
                // Display all the colors of the hue bar with lines
                for (int x = 0; x < 256; x++) {
                    // If this is not the current selected hue, display the actual
                    // color
                    if (translatedHue != x) {
                        mPaint.setColor(mHueBarColors[x]);
                        mPaint.setStrokeWidth(kf);
                    } else {    // else display a slightly larger black line -- cursor.
                        mPaint.setColor(Color.BLACK);
                        mPaint.setStrokeWidth(6);
                    }
                    xx = (int) (x * kf) + 10;
                    canvas.drawLine(xx, 0, xx, 70, mPaint);
                    // canvas.drawLine(0, x+10, 40, x+10, mPaint);
                }

                // Display the main field colors using LinearGradient
                for (int x = 0; x < 256; x++) {
                    int[] colors = new int[2];
                    colors[0] = mMainColors[x];
                    colors[1] = Color.BLACK;
                    Shader shader = new LinearGradient(0, 80, 0, HDSize - 60, colors, null, Shader.TileMode.REPEAT);
                    mPaint.setShader(shader);
                    xx = (int) (x * kf) + 10;
                    canvas.drawLine(xx, 80, xx, HDSize - 60, mPaint);
                }
                mPaint.setShader(null);

                // Display the circle around the currently selected color in the
                // main field
                if (mCurrentX != 0 && mCurrentY != 0) {
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setColor(Color.BLACK);
                    canvas.drawCircle(mCurrentX, mCurrentY, 10, mPaint);
                }

                // Draw a 'button' with the currently selected color
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(mCurrentColor);
                //canvas.drawRect(10, 316, 138, 356 , mPaint);
                canvas.drawRect(10, HDSize - 50, WDSize / 2 - 5, HDSize - 10 , mPaint);

                // Set the text color according to the brightness of the color
                if (Color.red(mCurrentColor) + Color.green(mCurrentColor) + Color.blue(mCurrentColor) < 384)
                    mPaint.setColor(Color.WHITE);
                else
                    mPaint.setColor(Color.BLACK);
                //canvas.drawText("Set", 74,340, mPaint);
                canvas.drawText("Set", WDSize / 4,HDSize - 22, mPaint);

                // Draw a 'button' with the default color
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(mDefaultColor);
                canvas.drawRect(WDSize / 2 + 5, HDSize - 50, WDSize - 10, HDSize - 10, mPaint);

                // Set the text color according to the brightness of the color
                if (Color.red(mDefaultColor) + Color.green(mDefaultColor) + Color.blue(mDefaultColor) < 384)
                    mPaint.setColor(Color.WHITE);
                else
                    mPaint.setColor(Color.BLACK);
                //canvas.drawText("Def", 202, 340, mPaint);
                canvas.drawText("Def", WDSize * 3 / 4, HDSize - 22, mPaint);
            }

            /* Size dialog window */
            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                setMeasuredDimension(WDSize, HDSize);
                //setMeasuredDimension(276, 366);
            }

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                if (event.getAction() != MotionEvent.ACTION_DOWN)
                    return true;
                float x = event.getX();
                float y = event.getY();

                // If the touch event is located in the hue bar
                if (x > 10 && x < WDSize - 10 && y > 0 && y < 70) {
                    // Update the main field colors
                    mCurrentHue = 255 - x * 255 / WDSize;
                    updateMainColors();

                    // Update the current selected color
                    int transX = mCurrentX - 10;
                    int transY = mCurrentY - 60;
                    int index = 256 * (transY - 1) + transX;
                    if (index > 0 && index < mMainColors.length)
                        mCurrentColor = mMainColors[256 * (transY - 1) + transX];

                    // Force the redraw of the dialog
                    invalidate();
                }

                // If the touch event is located in the main field
                if (x > 10 && x < WDSize - 10 && y > 80 && y < HDSize - 60) {
                    mCurrentX = (int) x;
                    mCurrentY = (int) y;
                    int transX = mCurrentX - 10;
                    int transY = mCurrentY - 80;
                    int index = 256 * (transY / (HDSize / 256) - 1) + transX / (WDSize / 256);
                    //Log.d("DoZer", "Index: " + index);
                    if (index > 0 && index < mMainColors.length) {
                        // Update the current color
                        mCurrentColor = mMainColors[index];
                        // Force the redraw of the dialog
                        invalidate();
                    }
                }

                // If the touch event is located in the left button, notify the
                // listener with the current color
                if (x > 10 && x < WDSize / 2 - 5 && y > HDSize - 50 && y < HDSize - 10)
                    mListener.colorChanged("", mCurrentColor);

                // If the touch event is located in the right button, notify the
                // listener with the default color
                if (x > WDSize / 2 + 5 && x < WDSize - 10 && y > HDSize - 50 && y < HDSize - 10)
                    mListener.colorChanged("", mDefaultColor);

                return true;
            }
        }

        public ColorPickerDialog(Context context, OnColorChangedListener listener, String key, int initialColor, int defaultColor) {
            super(context);

            mListener = listener;
            mKey = key;
            mInitialColor = initialColor;
            mDefaultColor = defaultColor;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            OnColorChangedListener l = new OnColorChangedListener() {
                public void colorChanged(String key, int color) {
                    mListener.colorChanged(mKey, color);
                    dismiss();
                }
            };

            setContentView(new ColorPickerView(getContext(), l, mInitialColor, mDefaultColor));
            setTitle("Color dialog");
        }
    }


}