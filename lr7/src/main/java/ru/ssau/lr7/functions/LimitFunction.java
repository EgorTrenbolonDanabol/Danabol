package ru.ssau.lr7.functions;

public class LimitFunction implements MathFunction {
    private final MathFunction function;
    private final double approach;

    public LimitFunction(MathFunction function, double approach) {
        this.function = function;
        this.approach = approach;
    }

    public double apply(double x) {
        return Limit.limit(function, x, approach);
    }
}