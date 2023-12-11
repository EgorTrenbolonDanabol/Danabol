package ru.ssau.lr7.functions.factory;

import ru.ssau.lr7.functions.ArrayTabulatedFunction;
import ru.ssau.lr7.functions.LinkedListTabulatedFunction;
import ru.ssau.lr7.functions.MathFunction;
import ru.ssau.lr7.functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory{
    public TabulatedFunction create(double[] xValues, double[] yValues){
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    @Override
    public TabulatedFunction create(MathFunction func, double xF, double xT, int count) {
        return new ArrayTabulatedFunction(func, xF,xT,count);
    }
}