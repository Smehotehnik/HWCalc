package com.r_21.calc.logic;

public class CalculatorImplement implements Calculator{
    @Override
    public double binaryOperation(double val_1, double val_2, Operations operation) {
        switch (operation){
            case ADD:
                return val_1 + val_2;
            case SUB:
                return val_1 - val_2;
            case MUL:
                return val_1 * val_2;
            case DIV:
                return val_1 / val_2;
            default:
                return 0;
        }
        //return 0;
    }
}
