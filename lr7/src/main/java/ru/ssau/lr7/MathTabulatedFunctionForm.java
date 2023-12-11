package ru.ssau.lr7;

import ru.ssau.lr7.functions.MathFunction;

public class MathTabulatedFunctionForm {
    private double xFrom;
    private double xTo;
    private int count;

    private String function;
    // Геттеры
    public String getFunction() {
        return function;
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
}