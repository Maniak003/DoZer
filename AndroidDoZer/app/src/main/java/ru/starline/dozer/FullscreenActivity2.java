package ru.starline.dozer;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class FullscreenActivity2 extends AppCompatActivity {
    public Props PP;
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
                    closeActivity();
                }
            });
        } else {
            Log.d("DoZer", "exitBtn not found");
        }
        // Read parameters from config file.
        try {
            Log.d("DoZer", "Read parameters.");
            editTextMAC.setText(PP.readProp("MAC"));
            editTextKoefR.setText(PP.readProp("koefR"));
            editTextLevel1.setText(PP.readProp("Level1"));
            editTextLevel2.setText(PP.readProp("Level2"));
            editTextLevel3.setText(PP.readProp("Level3"));
            editTextCorrect_A.setText(PP.readProp("Correct_A"));
            editTextCorrect_B.setText(PP.readProp("Correct_B"));
            editTextCorrect_C.setText(PP.readProp("Correct_C"));
            editTextEnergi_A.setText(PP.readProp("Energi_A"));
            editTextEnergi_B.setText(PP.readProp("Energi_B"));
            editTextEnergi_C.setText(PP.readProp("Energi_C"));
            editTextEnergi_D.setText(PP.readProp("Energi_D"));
            String tmpStr;
            tmpStr = PP.readProp("Level1_S");
            if (tmpStr != null) {
                checkBoxLevel1_S.setChecked(tmpStr.equals("1"));
            }
            tmpStr = PP.readProp("Level1_V");
            if (tmpStr != null) {
                checkBoxLevel1_V.setChecked(tmpStr.equals("1"));
            }
            tmpStr = PP.readProp("Level2_S");
            if (tmpStr != null) {
                checkBoxLevel2_S.setChecked(tmpStr.equals("1"));
            }
            tmpStr = PP.readProp("Level2_V");
            if (tmpStr != null) {
                checkBoxLevel2_V.setChecked(tmpStr.equals("1"));
            }
            tmpStr = PP.readProp("Level3_S");
            if (tmpStr != null) {
                checkBoxLevel3_S.setChecked(tmpStr.equals("1"));
            }
            tmpStr = PP.readProp("Level3_V");
            if (tmpStr != null) {
                checkBoxLevel3_V.setChecked(tmpStr.equals("1"));
            }
            tmpStr = PP.readProp("LED");
            if (tmpStr != null) {
                checkBoxLed.setChecked(tmpStr.equals("1"));
            }
            tmpStr = PP.readProp("Sound");
            if (tmpStr != null) {
                checkBoxSound.setChecked(tmpStr.equals("1"));
            }
            tmpStr = PP.readProp("Resolution");
            if (tmpStr != null) {
                if (tmpStr.equals("3")) {
                    radioButtonResolution3.setChecked(true);
                } else if (tmpStr.equals("2")) {
                    radioButtonResolution2.setChecked(true);
                } else {
                    radioButtonResolution1.setChecked(true);
                }
            }
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

        public void writeProp() throws IOException {
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
            } else {
                prop.setProperty("Level1_S", "0");
            }
            if (checkBoxLevel1_V.isChecked()) {
                prop.setProperty("Level1_V", "1");
            } else {
                prop.setProperty("Level1_V", "0");
            }

            if (checkBoxLevel2_S.isChecked()) {
                prop.setProperty("Level2_S", "1");
            } else {
                prop.setProperty("Level2_S", "0");
            }
            if (checkBoxLevel2_V.isChecked()) {
                prop.setProperty("Level2_V", "1");
            } else {
                prop.setProperty("Level2_V", "0");
            }

            if (checkBoxLevel3_S.isChecked()) {
                prop.setProperty("Level3_S", "1");
            } else {
                prop.setProperty("Level3_S", "0");
            }
            if (checkBoxLevel3_V.isChecked()) {
                prop.setProperty("Level3_V", "1");
            } else {
                prop.setProperty("Level3_V", "0");
            }

            if (checkBoxLed.isChecked()) {
                prop.setProperty("LED", "1");
            } else {
                prop.setProperty("LED", "0");
            }

            if (checkBoxSound.isChecked()) {
                prop.setProperty("Sound", "1");
            } else {
                prop.setProperty("Sound", "0");
            }
            if (radioButtonResolution1.isChecked()) {
                prop.setProperty("Resolution", "1");
            } else  if (radioButtonResolution2.isChecked()) {
                prop.setProperty("Resolution", "2");
            } else {
                prop.setProperty("Resolution", "3");
            }

            prop.store(fileOutputStream, null);
        }
    }

}