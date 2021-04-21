package com.r_21.calc.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.r_21.calc.R;

public class Calculator extends AppCompatActivity {
    private static double value;
    private static String strValue;
    private TextView screenStr;
    private final Button [] numButtons = new Button[10];
    private final int NUMBER_LENGTH = 8;

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenStr = findViewById(R.id.display);
        numButtons[0] = findViewById(R.id.bt0);
        numButtons[1] = findViewById(R.id.bt1);
        numButtons[2] = findViewById(R.id.bt2);
        numButtons[3] = findViewById(R.id.bt3);
        numButtons[4] = findViewById(R.id.bt4);
        numButtons[5] = findViewById(R.id.bt5);
        numButtons[6] = findViewById(R.id.bt6);
        numButtons[7] = findViewById(R.id.bt7);
        numButtons[8] = findViewById(R.id.bt8);
        numButtons[9] = findViewById(R.id.bt9);

        if (savedInstanceState == null) {
            //First launch
            screenStr.setText("0");
            //strValue = "0";
        } else {
            //TODO check

        }
        strValue = (String) screenStr.getText();

        findViewById(R.id.bt0).setOnClickListener(v -> {
            String st = (String) getText(R.string.strBt0);
            btnPressed(st);
        });

        findViewById(R.id.bt1).setOnClickListener(v -> {
            String st = (String) getText(R.string.strBt1);
            btnPressed(st);
        });
        findViewById(R.id.bt2).setOnClickListener(v -> {
            String st = (String) getText(R.string.strBt2);
            btnPressed(st);
        });
        findViewById(R.id.bt3).setOnClickListener(v -> {
            String st = (String) getText(R.string.strBt3);
            btnPressed(st);
        });
/*        numButtons[4].setOnClickListener(v -> {
            String st = (String) numButtons[4].getText();
            btnPressed(st);
        });*/
       for (int i = 4; i < 7; i++) {
            //int finalI = i;
           int finalI = i;
           numButtons[i].setOnClickListener(v -> {
                String st = (String) numButtons[finalI].getText();
                btnPressed(st);
            });
        }



    }

    private void btnPressed(String figure) {
        if (strValue.equals("0")) {
            if (!figure.equals("0")) {
                strValue = figure;
            }
        } else if (strValue.length() < NUMBER_LENGTH) {
            strValue += figure;
        }
        screenStr.setText(strValue);
    }


}