package com.r_21.calc.UI;

import com.r_21.calc.logic.Operations;

public interface CalcView {
    void  showResult(String result);
    void showAux(String aux);
    String readInput();
    void btDigitClicked(int digit);
    void btPointClicked();
    void btAddClicked();
    void btMinusClicked();
    void btMulClicked();
    void btDivClicked();
    void btEqualClicked();
    void btClearClicked();
    void btMemAddClicked();
    void btMemMinusClicked();
    void btRMClicked();
}
