package ru.ssau.lr7;

import ru.ssau.lr7.functions.ArrayTabulatedFunction;
import ru.ssau.lr7.functions.MathFunction;
import ru.ssau.lr7.functions.TabulatedFunction;
import ru.ssau.lr7.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.TabulatedFunctionFactory;
import ru.ssau.lr7.operations.TabulatedFunctionOperationService;

import java.util.HashMap;
import java.util.Map;

public class Operations {

    private TabulatedFunctionOperationService CombinedTabulatedFunction;
    public void setSetting(String setting){
        switch (setting) {
            case "array" -> CombinedTabulatedFunction = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
            case "linked" -> CombinedTabulatedFunction = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        }
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public TabulatedFunction getOperand1() {
        return operand1;
    }

    public void setOperand1(TabulatedFunction operand1) {
        this.operand1 = operand1;
    }

    TabulatedFunction operand1 = new ArrayTabulatedFunction(new double[]{0,0,0,0}, new double[]{0,0,0,0});

    public TabulatedFunction getOperand2() {
        return operand2;
    }

    public void setOperand2(TabulatedFunction operand2) {
        this.operand2 = operand2;
    }

    TabulatedFunction operand2=new ArrayTabulatedFunction(new double[]{0,0,0,0}, new double[]{0,0,0,0});;
    TabulatedFunction result;

    public TabulatedFunction getResult() {
        return result;
    }

    public void setResult(TabulatedFunction result) {
        this.result = result;
    }



    public TabulatedFunctionOperationService getCombinedTabulatedFunction() {
        return CombinedTabulatedFunction;
    }

    public void setCombinedTabulatedFunction(TabulatedFunctionOperationService combinedTabulatedFunction) {
        CombinedTabulatedFunction = combinedTabulatedFunction;
    }


}