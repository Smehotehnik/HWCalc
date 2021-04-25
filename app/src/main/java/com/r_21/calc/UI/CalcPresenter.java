package com.r_21.calc.UI;

import android.graphics.Path;

import com.r_21.calc.logic.Calculator;
import com.r_21.calc.logic.Operations;

import java.io.Serializable;
import java.util.Locale;

public class CalcPresenter implements CalcView, Serializable {
    private CalcView view;
    private Calculator calculator;
    double value1, value2;
    final int NUMBER_LENGTH = 12;
    String strValue;
    Operations nextOperation;

    public CalcPresenter(CalcView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
        strValue = "0";
        nextOperation = Operations.NO;
    }

    public void btDigitClicked(int digit) {
        btnPressed(String.valueOf(digit));
    }

    public void btPointClicked() {
        btnPressed(".");
    }

    public void btAddClicked() {
        prepareToSolve(Operations.ADD);
    }

    public void btMinusClicked() {
        prepareToSolve(Operations.SUB);
    }

    public void btMulClicked() {
        prepareToSolve(Operations.MUL);
    }

    public void btDivClicked() {
        prepareToSolve(Operations.DIV);
    }

    public void btEqualClicked() {
        getAnswer();
    }

    public void btClearClicked() {
        strValue = "0";
        value1 = value2 = 0;
        view.showResult(strValue);
        view.showAux("");
    }

    public void btMemAddClicked() {

    }

    public void btMemMinusClicked() {

    }

    public void btRMClicked() {

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
        view.showResult(strValue);
    }

    private void prepareToSolve(Operations operation) {
        strValue = view.readInput();
        value1 = Double.parseDouble(strValue);
        nextOperation = operation;
        this.strValue = "0";
    }

    private void getAnswer() {
        double answer;
        strValue = view.readInput();
        value2 = Double.parseDouble(strValue);
        String answerStr;
        answer = calculator.binaryOperation(this.value1, this.value2, this.nextOperation);
        this.value1 = answer;
        answerStr = formatAnswer(String.format(Locale.US, "%1.8g", answer));
        if (answerStr.contains("NaN"))
            view.showAux("ERROR");
        view.showResult(answerStr);
        this.strValue = "0";
    }

    private String formatAnswer(String s) {
        if (s.contains(".")) ;
        {
            if (s.contains("e") == false) {
                while (s.endsWith("0")) {
                    s = s.substring(0, s.length() - 1);
                }
                if (s.endsWith(".")) s = s.substring(0, s.length() - 1);
            }
        }
        return s;
    }


    @Override
    public void showResult(String result) {

    }

    @Override
    public void showAux(String aux) {

    }

    @Override
    public String readInput() {
        return null;
    }

}
