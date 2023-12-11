package ru.ssau.lr7.operations;
import ru.ssau.lr7.functions.Point;
import ru.ssau.lr7.functions.TabulatedFunction;
import ru.ssau.lr7.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.TabulatedFunctionFactory;
import ru.ssau.lr7.exceptions.InconsistentFunctionsException;

public class TabulatedFunctionOperationService {
    TabulatedFunctionFactory factory;
    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }
    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }
    public TabulatedFunctionFactory getFactory() {
        return this.factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int size = tabulatedFunction.getCount();
        Point[] points = new Point[size];

        int i = 0;

        for (Point point : tabulatedFunction) {
            points[i] = point;
            i++;
        }
        return points;
    }
    private interface BiOperation {
        double apply(double u, double v);
    }

    protected TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if (a.getCount() != b.getCount())
            throw new InconsistentFunctionsException("Arrays have different size");
        double[] xValues = new double[a.getCount()];
        double[] yValues = new double[a.getCount()];
        Point[] points_a = this.asPoints(a);
        Point[] points_b = this.asPoints(b);
        for (int i = 0; i < a.getCount(); i++) {
            if (points_a[i].x != points_b[i].x)
                throw new InconsistentFunctionsException("Arrays have different x");
            xValues[i] = points_a[i].x;
            yValues[i] = operation.apply(points_a[i].y, points_b[i].y);
        }
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction Addition(TabulatedFunction a, TabulatedFunction b) {
        BiOperation op = (u, v)->u+v;
        return doOperation(a, b, op);
    }

    public TabulatedFunction Subtraction(TabulatedFunction a, TabulatedFunction b) {
        BiOperation op = (u,v)->u-v;
        return doOperation(a, b, op);
    }
    public TabulatedFunction Multiplication(TabulatedFunction a, TabulatedFunction b) {
        BiOperation op = (u, v) -> u * v;
        return doOperation(a, b, op);
    }
    public TabulatedFunction Division(TabulatedFunction a, TabulatedFunction b) {
        BiOperation op = (u, v) -> {
            if (v == 0) {
                throw new ArithmeticException("Division by zero isn't allowed");
            }
            return u / v;
        };
        return doOperation(a, b, op);
    }
}