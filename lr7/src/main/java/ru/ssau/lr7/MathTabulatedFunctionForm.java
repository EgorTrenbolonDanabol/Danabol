package ru.ssau.lr7;

import ru.ssau.lr7.functions.MathFunction;
import ru.ssau.lr7.functions.TabulatedFunction;
import ru.ssau.lr7.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.TabulatedFunctionFactory;

import java.util.HashMap;
import java.util.Map;

public class MathTabulatedFunctionForm {
    public Map<String, MathFunction> getMapa() {
        return mapa;
    }

    public void setMapa(Map<String, MathFunction> mapa) {
        this.mapa = mapa;
    }

    private Map<String, MathFunction> mapa = new HashMap<>();
    private double xFrom;
    private double xTo;
    private int count;
    private TabulatedFunction tabulatedFunction;
    private String function;
    // Геттеры
    public String getFunction() {
        return function;
    }
    public TabulatedFunction getTabulatedFunction() {
        return tabulatedFunction;
    }
    public double getXFrom() {
        return xFrom;
    }

    public double getXTo() {
        return xTo;
    }

    public int getCount() {
        return count;
    }

    // Сеттеры
    public void setFunction(String function) {
        this.function = function;
    }
    public void setXFrom(double xFrom) {
        this.xFrom = xFrom;
    }

    public void setXTo(double xTo) {
        this.xTo = xTo;
    }

    public void setCount(int count) {
        this.count = count;
    }



    public void createArray(MathFunction mathFunction) {
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        tabulatedFunction = factory.create(mathFunction, xFrom, xTo, count);
    }

    public void createLinked(MathFunction mathFunction) {
        TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        tabulatedFunction = factory.create(mathFunction, xFrom, xTo, count);
    }
}

