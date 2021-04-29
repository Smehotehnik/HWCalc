package com.r_21.calc.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.r_21.calc.R;

public class ChoseActivity extends AppCompatActivity {

    public static final String ARG_THEME_ID = "ARG_THEME_ID";

    public static void show(Activity activity, int themeId) {
        Intent intent = new Intent(activity, ChoseActivity.class);
        activity.startActivityForResult(intent, themeId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);

        findViewById(R.id.bt_day_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra(ARG_THEME_ID, 1);

                setResult(RESULT_OK, intent);

                finish();
            }
        });

        findViewById(R.id.bt_night_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(ARG_THEME_ID, 2);

                setResult(RESULT_OK, intent);

                finish();

            }
        });
    }


}