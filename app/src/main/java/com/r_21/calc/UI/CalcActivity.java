package com.r_21.calc.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.r_21.calc.R;
import com.r_21.calc.logic.CalculatorImplement;
import com.r_21.calc.logic.Operations;

import java.io.Serializable;

public class CalcActivity extends AppCompatActivity implements CalcView, Serializable {
    private CalcPresenter presenter;
    TextView screenStr, auxScreen;
    private final  static  String VAL1 = "VAL1";
    private final  static  String VAL2 = "VAL2";
    private final  static  String STVAL = "STVAL";
    private final  static  String SCR = "SCR";
    private final  static  String OP = "OP";
    private static int themeId = 0;

    private static final int REQUEST_CODE = 43;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.Theme_CalcDark);

        setContentView(R.layout.activity_main);
        screenStr = findViewById(R.id.display);
        auxScreen = findViewById(R.id.aux_display);
        presenter = new CalcPresenter(this, new CalculatorImplement());


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt0:
                        presenter.btDigitClicked(0);
                        break;
                    case R.id.bt1:
                        presenter.btDigitClicked(1);
                        break;
                    case R.id.bt2:
                        presenter.btDigitClicked(2);
                        break;
                    case R.id.bt3:
                        presenter.btDigitClicked(3);
                        break;
                    case R.id.bt4:
                        presenter.btDigitClicked(4);
                        break;
                    case R.id.bt5:
                        presenter.btDigitClicked(5);
                        break;
                    case R.id.bt6:
                        presenter.btDigitClicked(6);
                        break;
                    case R.id.bt7:
                        presenter.btDigitClicked(7);
                        break;
                    case R.id.bt8:
                        presenter.btDigitClicked(8);
                        break;
                    case R.id.bt9:
                        presenter.btDigitClicked(9);
                        break;
                    case R.id.btAdd:
                        presenter.btAddClicked();
                        break;
                    case R.id.btMinus:
                        presenter.btMinusClicked();
                        break;
                    case R.id.btMul:
                        presenter.btMulClicked();
                        break;
                    case R.id.btDiv:
                        presenter.btDivClicked();
                        break;
                    case R.id.btEq:
                        presenter.btEqualClicked();
                        break;
                    case R.id.btPoint:
                        presenter.btPointClicked();
                        break;
                    case R.id.btClear:
                        presenter.btClearClicked();
                        break;
                    case R.id.btMemAdd:
                        presenter.btMemAddClicked();
                        break;
                    case R.id.btMemMinus:
                        presenter.btMemMinusClicked();
                        break;
                    case R.id.btRM:
                        presenter.btRMClicked();
                        break;
                    case R.id.day_night_toggle:
                        presenter.dayNightToggled();
                        break;
                    default:
                        break;
                }
            }
        };
        findViewById(R.id.bt0).setOnClickListener(onClickListener);
        findViewById(R.id.bt1).setOnClickListener(onClickListener);
        findViewById(R.id.bt2).setOnClickListener(onClickListener);
        findViewById(R.id.bt3).setOnClickListener(onClickListener);
        findViewById(R.id.bt4).setOnClickListener(onClickListener);
        findViewById(R.id.bt5).setOnClickListener(onClickListener);
        findViewById(R.id.bt6).setOnClickListener(onClickListener);
        findViewById(R.id.bt7).setOnClickListener(onClickListener);
        findViewById(R.id.bt8).setOnClickListener(onClickListener);
        findViewById(R.id.bt9).setOnClickListener(onClickListener);
        findViewById(R.id.btPoint).setOnClickListener(onClickListener);
        findViewById(R.id.btAdd).setOnClickListener(onClickListener);
        findViewById(R.id.btMinus).setOnClickListener(onClickListener);
        findViewById(R.id.btMul).setOnClickListener(onClickListener);
        findViewById(R.id.btDiv).setOnClickListener(onClickListener);
        findViewById(R.id.btClear).setOnClickListener(onClickListener);
        findViewById(R.id.btEq).setOnClickListener(onClickListener);
        findViewById(R.id.btMemAdd).setOnClickListener(onClickListener);
        findViewById(R.id.btMemMinus).setOnClickListener(onClickListener);
        findViewById(R.id.btRM).setOnClickListener(onClickListener);

        findViewById(R.id.bt_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoseActivity.show(CalcActivity.this, REQUEST_CODE);
            }
        });

        if (savedInstanceState == null) {
            //First launch
            screenStr.setText("0");
            auxScreen.setText("");
        } else {
            //TODO check
            auxScreen.setText("");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Toast.makeText(CalcActivity.this, String.valueOf(data.getIntExtra(ChoseActivity.ARG_THEME_ID, -1)), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(SCR, (String) screenStr.getText());
        outState.putDouble(VAL1, presenter.value1);
        outState.putDouble(VAL2, presenter.value2);
        outState.putString(STVAL, presenter.strValue);
        ///outState.putInt(OP, (Integer) presenter.nextOperation);

        super.onSaveInstanceState(outState);
        outState.putSerializable(OP, presenter.nextOperation);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        screenStr.setText(savedInstanceState.getString(SCR));
        presenter.value1 = savedInstanceState.getDouble(VAL1);
        presenter.value2 = savedInstanceState.getDouble(VAL2);
        presenter.strValue = savedInstanceState.getString(STVAL);
        presenter.nextOperation = (Operations) savedInstanceState.getSerializable(OP);
    }

    @Override
    public void showResult(String result) {
        screenStr.setText(result);
    }

    @Override
    public void showAux(String aux) {
        auxScreen.setText(aux);
    }

    @Override
    public String readInput() {
        return (String) screenStr.getText();
    }

    @Override
    public void btDigitClicked(int digit) {

    }

    @Override
    public void btPointClicked() {

    }

    @Override
    public void btAddClicked() {

    }

    @Override
    public void btMinusClicked() {

    }

    @Override
    public void btMulClicked() {

    }

    @Override
    public void btDivClicked() {

    }

    @Override
    public void btEqualClicked() {

    }

    @Override
    public void btClearClicked() {

    }

    @Override
    public void btMemAddClicked() {

    }

    @Override
    public void btMemMinusClicked() {

    }

    @Override
    public void btRMClicked() {

    }

    @Override
    public void dayNightToggled() {

    }
}