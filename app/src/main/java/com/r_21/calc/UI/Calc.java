package com.r_21.calc.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.r_21.calc.R;

public class Calc extends AppCompatActivity {
    private static double value1, value2, answer;
    private static int action = 10;
    private static String strValue;
    private TextView screenStr;
    private final Button[] numButtons = new Button[10];
    private Button btPoint;
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
        btPoint = findViewById(R.id.btPoint);

        if (savedInstanceState == null) {
            //First launch
            screenStr.setText("0");
            //strValue = "0";
        } else {
            //TODO check
        }
        strValue = (String) screenStr.getText();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            numButtons[i].setOnClickListener(v -> {
                String st = (String) numButtons[finalI].getText();
                btnPressed(st);
            });
        }

        btPoint.setOnClickListener(v -> {
            String st = (String) btPoint.getText();
            btnPressed(st);
        });

        findViewById(R.id.btClear).setOnClickListener(v -> {
            strValue = "0";
            screenStr.setText(strValue);
        });
    }

    private void btnPressed(String figure) {
        if (strValue.equals("0")) {
            if (figure.equals(".")) strValue += figure;
            else                    strValue = figure;
        } else if (figure.equals(".")) {
            if (!strValue.contains(".")) {
                strValue += figure;
            }
        } else if (strValue.length() < NUMBER_LENGTH) {
            strValue += figure;
        } else if (strValue.contains(".") && strValue.length() == NUMBER_LENGTH) {
            strValue += figure;
        }
        screenStr.setText(strValue);
    }


}