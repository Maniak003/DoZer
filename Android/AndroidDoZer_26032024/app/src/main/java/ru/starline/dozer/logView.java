package ru.starline.dozer;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.format.DateFormat;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class logView extends AppCompatActivity {
    public TextView logTextView;
    private View mContentView;

    public int logIndex;
    public double[][] logArray;

    private void closeActivity() {
        this.finish();
    }

    /*
        Reload log data
     */
    private void reloadLog() {

        logTextView.setText("", TextView.BufferType.SPANNABLE); // Clear all text
        String s = "", colorLevel = "<font color=#646464>";
        SpannableStringBuilder ss, ssResult = new SpannableStringBuilder("");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dateCurrent = new Date(), dateOut = new Date();
        /*
        s = "01.01.2021 14:10 Power on Power level 98%\n";
        logTextView.append(s);

        s = "01.01.2021 14:10 Radiation level 3 150uR/h\n";
        ss = new SpannableString(s);
        ss.setSpan(colMagenta, 0, s.length() - 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        logTextView.append(ss);


        s = "01.01.2021 14:10 Radiation level 2 68uR/h\n";
        ss = new SpannableString(s);
        ss.setSpan(colRED, 0, s.length() - 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        logTextView.append(ss);

        s = "01.01.2021 14:10 Radiation level 1 32uR/h\n";
        ss =  new SpannableString(s);
        ss.setSpan(colYellow, 0, s.length() - 1, Spanned.SPAN_COMPOSING);
        logTextView.append(ss);
        logTextView.append(ss);

        s = "01.01.2021 14:10 Radiation normal 10uR/h\n";
        ss =  new SpannableString(s);
        ss.setSpan(colGreen, 0, s.length() - 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        logTextView.append(ss);

        s = "01.01.2021 14:10 Power on Power level 97%\n";
        logTextView.append(s);
         */

        for ( int i = 0; i < logIndex; i++) {
            String level = "Normal";
            colorLevel = "<font color=#646464>";
            if (logArray[i][1] == 3) {
                level = "Alert";
                colorLevel = "<font color=#B02EE8>";
            } else {
                if (logArray[i][1] == 2) {
                    level = "Alarm";
                    colorLevel = "<font color=#C80000>";
                } else {
                    if (logArray[i][1] == 1) {
                        level = "Warning";
                        colorLevel = "<font color=#FFBF00>";
                    } else {
                        if (logArray[i][1] == 0) {
                            level = "Normal";
                            colorLevel = "<font color=#1AFF00>";
                        }
                    }
                }
            }
            Log.i("DoZer","Curr time: " + dateCurrent.getTime() + " start time : " + logArray[0][0] + " event time : " + logArray[i][0]);
            if (i == 0) {
                dateOut.setTime(dateCurrent.getTime() - (long) ((logArray[0][0]) * 1000));
                s = "<font color=#646464>" + sdf.format(dateOut) + " Power on" + "</font><br>";
                ssResult.append(s);
            } else {
                dateOut.setTime(dateCurrent.getTime() - (long) ((logArray[0][0] - logArray[i][0]) * 1000));
                s = s + colorLevel + sdf.format(dateOut) + " " +  level + " " + String.valueOf(logArray[i][2]) + " uR/h</font><br>";
                //ss = new SpannableStringBuilder(s);
                //ss.setSpan(colLevel, 0, s.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                //ssResult.append(ss);
            }
        }
        logTextView.setText(Html.fromHtml(s, s.length() ));
    }

    /*
        Format layout for full screen
     */
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

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        reloadLog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_view);
        mContentView = findViewById(R.id.logViewLayout);
        logTextView = findViewById(R.id.logTextView);
        formatLayout();

        Bundle arguments = getIntent().getExtras();
        logIndex = arguments.getInt("LOGDATA0");
        if (logIndex > 0) {
            if (arguments != null) {
                logArray = (double[][]) arguments.getSerializable("LOGDATA1");
            }
        }

        // Save & Exit button
        final Button exitBtn = findViewById(R.id.exitButton);
        if (exitBtn != null) {
            exitBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.d("DoZer", "Pressed exit.");
                    closeActivity();
                }
            });
        } else {
            Log.d("DoZer", "exitBtn not found");
        }
    }
}