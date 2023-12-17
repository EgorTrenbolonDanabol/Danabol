package ru.ssau.lr7;

import ru.ssau.lr7.functions.ArrayTabulatedFunction;
import ru.ssau.lr7.functions.TabulatedFunction;
import ru.ssau.lr7.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.lr7.operations.TabulatedFunctionOperationService;

public class DifferentialOperatorForm {


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;


    public TabulatedFunction getFunction() {
        return function;
    }

    public void setFunction(TabulatedFunction function) {
        this.function = function;
    }

    TabulatedFunction function=new ArrayTabulatedFunction(new double[]{0,0,0,0}, new double[]{0,0,0,0});


    public TabulatedFunction getResult() {
        return result;
    }

    public void setResult(TabulatedFunction result) {
        this.result = result;
    }

    TabulatedFunction result=new ArrayTabulatedFunction(new double[]{0,0,0,0}, new double[]{0,0,0,0});


}
