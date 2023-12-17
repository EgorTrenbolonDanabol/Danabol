
package ru.ssau.lr7;


import ru.ssau.lr7.functions.TabulatedFunction;
import ru.ssau.lr7.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionForm {
    private int amount=0;
    private double[] xValues;
    private double[] yValues;
    private TabulatedFunction function;


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.xValues = new double[amount];
        this.yValues = new double[amount];
    }

    public double[] getXValues() {
        return xValues;
    }

    public void setXValues(double[] xValues) {
        this.xValues = xValues;
    }

    public double[] getYValues() {
        return yValues;
    }

    public void setYValues(double[] yValues) {
        this.yValues = yValues;
    }
    public TabulatedFunction getFunction(){
        return function;
    }
    public void createArrayTabulatedFunction() {
       TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
       function = factory.create(xValues, yValues); // сохраняем созданный объект в поле
    }

    public void createLinkedListTabulatedFunction() {
        TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        function = factory.create(xValues, yValues); // сохраняем созданный объект в поле
    }

}