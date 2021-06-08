package ru.starline.dozer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

public class logView extends AppCompatActivity {
    public TextView logTextView;
    private View mContentView;

    private void closeActivity() {
        this.finish();
    }

    /*
        Reload log data
     */
    private void reloadLog() {

        ForegroundColorSpan colMagenta = new ForegroundColorSpan(Color.MAGENTA),
                colRED = new ForegroundColorSpan(Color.RED),
                colYellow =  new ForegroundColorSpan(Color.YELLOW),
                colGreen = new ForegroundColorSpan(Color.GREEN);

        logTextView.setText("");
        String s = "01.01.2021 14:10 Power on Power level 98%\n";
        logTextView.append(s);

        s = "01.01.2021 14:10 Radiation level 3 150uR/h\n";
        SpannableString ss = new SpannableString(s);
        ss.setSpan(colMagenta, 0, s.length() - 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        logTextView.append(ss);


        s = "01.01.2021 14:10 Radiation level 2 68uR/h\n";
        ss = new SpannableString(s);
        ss.setSpan(colRED, 0, s.length() - 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        logTextView.append(ss);

        s = "01.01.2021 14:10 Radiation level 1 32uR/h\n";
        ss =  new SpannableString(s);
        ss.setSpan(colYellow, 0, s.length() - 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        logTextView.append(ss);

        s = "01.01.2021 14:10 Radiation normal 10uR/h\n";
        ss =  new SpannableString(s);
        ss.setSpan(colGreen, 0, s.length() - 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        logTextView.append(ss);

        s = "01.01.2021 14:10 Power on Power level 97%\n";
        logTextView.append(s);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_view);
        mContentView = findViewById(R.id.logViewLayout);
        logTextView = findViewById(R.id.logTextView);
        formatLayout();


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
        final Button reloadLog = findViewById(R.id.reloadButton);
        if (reloadLog != null) {
            reloadLog.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    reloadLog();
                }
            });
        } else {
            Log.d("DoZer", "reloadButton not found");
        }
    }
}