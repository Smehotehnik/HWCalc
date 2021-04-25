package com.r_21.calc.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.r_21.calc.R;
import com.r_21.calc.logic.Calculator;
import com.r_21.calc.logic.OperationFlag;
import com.r_21.calc.logic.Operations;

import java.util.Locale;

public class Calc extends AppCompatActivity implements Calculator {
    private static Double value1, value2, answer;
    OperationFlag flag;
    Operations operation;
    private static String strValue;
    private TextView screenStr, auxScreen;
    private final Button[] numButtons = new Button[10];
    private Button btPoint;
    private final int NUMBER_LENGTH = 12;

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
        auxScreen = findViewById(R.id.aux_display);
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
            flag = OperationFlag.calcFinished;
            screenStr.setText("0");
            auxScreen.setText("");
            value1 = value2 = answer = 0d;
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
//--------------------Operations---------------------------------
        findViewById(R.id.btAdd).setOnClickListener(v -> {
            if (flag == OperationFlag.calcStated) {
                getAnswer();
                flag = OperationFlag.interimResult;
            } else if (flag == OperationFlag.calcFinished) {
                strValue = (String) screenStr.getText();
                value1 = Double.parseDouble(strValue);
                flag = OperationFlag.calcStated;
            }
            operation = Operations.ADD;
            strValue = "0";
        });

        findViewById(R.id.btMul).setOnClickListener(v -> {
            strValue = (String) screenStr.getText();
            value1 = Double.parseDouble(strValue);
            operation = Operations.MUL;
            strValue = "0";
        });

        findViewById(R.id.btMinus).setOnClickListener(v -> {
            if (flag == OperationFlag.calcStated) {
                getAnswer();
                flag = OperationFlag.interimResult;
            } else if (flag == OperationFlag.calcFinished) {
                strValue = (String) screenStr.getText();
                value1 = Double.parseDouble(strValue);
                flag = OperationFlag.calcStated;
            }
            operation = Operations.SUB;
            strValue = "0";
        });

        findViewById(R.id.btDiv).setOnClickListener(v -> {
            strValue = (String) screenStr.getText();
            value1 = Double.parseDouble(strValue);
            operation = Operations.DIV;
            strValue = "0";
        });
//===============================================================
        findViewById(R.id.btEq).setOnClickListener(v -> {
            getAnswer();
        });

        findViewById(R.id.btClear).setOnClickListener(v -> {
            strValue = "0";
            auxScreen.setText("");
            screenStr.setText(strValue);
            value1 = value2 = answer = 0d;
            flag = OperationFlag.calcFinished;
        });
    }

    private void getAnswer() {
        if (flag == OperationFlag.calcStated) value2 = Double.parseDouble(strValue);
        //if (flag == OperationFlag.interimResult)
        answer = binaryOperation(value1, value2, operation);
        value1 = answer;
        if (answer != 0) strValue = String.format(Locale.US, "%1.8g", answer);
        else strValue = "0";
        screenStr.setText(strValue);
        strValue = "0";
        flag = OperationFlag.calcFinished;
    }

    private void btnPressed(String figure) {
        if (strValue.equals("0")) {
            if (figure.equals(".")) strValue += figure;
            else strValue = figure;
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