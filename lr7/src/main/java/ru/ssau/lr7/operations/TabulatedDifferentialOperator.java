package ru.ssau.lr7.operations;

import ru.ssau.lr7.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.lr7.functions.Point;
import ru.ssau.lr7.functions.TabulatedFunction;
import ru.ssau.lr7.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.TabulatedFunctionFactory;
public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction>{
    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(){
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedDifferentialOperator(TabulatedFunctionFactory tabulatedFunctionFactory){
        this.factory = tabulatedFunctionFactory;
    }

    public TabulatedFunctionFactory getF(){
        return this.factory;
    }

    public void setF(TabulatedFunctionFactory tabulatedFunctionFactory){
        this.factory = tabulatedFunctionFactory;
    }
    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[function.getCount()];
        double[] yValues = new double[function.getCount()];
        int i = 0;
        for (i = 0; i < (function.getCount()-1); i++){
            xValues[i] = points[i].x;
            yValues[i] = (points[i+1].y - points[i].y)/(points[i+1].x - points[i].x);
        }
        xValues[i] = points[i].x;
        yValues[i] = yValues[i - 1];
        return factory.create(xValues, yValues);

    }
    public SynchronizedTabulatedFunction deriveSynchronously(TabulatedFunction function){
        SynchronizedTabulatedFunction synchronizedTabulatedFunction;
        if (function instanceof SynchronizedTabulatedFunction) {
            synchronizedTabulatedFunction = (SynchronizedTabulatedFunction) function;
        } else {
            synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);
        }

        return synchronizedTabulatedFunction.doSynchronously(func -> new SynchronizedTabulatedFunction(derive(func)));
    }

}