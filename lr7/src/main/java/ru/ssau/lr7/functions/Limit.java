package ru.ssau.lr7.functions;

public class Limit {
    private Limit() {

    }

    public static final double limit(MathFunction function, double x, double approach) {
        double below = Limit.limitFromBelow(function, x, approach);
        double above = Limit.limitFromAbove(function, x, approach);
        return below == above ? below : Double.NaN;
    }

    public static final double limitFromBelow(MathFunction function, double x, double approach) {
        for (double d = approach - 10; d <= approach; d = approach - ((approach - d) / 10)) {
            double result = function.apply(d);
            if (result == Double.POSITIVE_INFINITY) {
                return Double.POSITIVE_INFINITY;
            }
            else if (result == Double.NEGATIVE_INFINITY) {
                return Double.NEGATIVE_INFINITY;
            }
            else if (Double.isNaN(result)) {
                return function.apply(approach + ((approach - d) * 10));
            }
            else {
                if (d == approach) {
                    return result;
                } else if (approach - d < 0.00000000001) {
                    d = approach;
                }
            }
        }
        return Double.NaN;
    }

    public static final double limitFromAbove(MathFunction function, double x, double approach) {
        for (double d = approach + 10; d >= approach; d = approach - ((approach - d) / 10)) {
            double result = function.apply(d);
            if (result == Double.POSITIVE_INFINITY) {
                return Double.POSITIVE_INFINITY;
            }
            else if (result == Double.NEGATIVE_INFINITY) {
                return Double.NEGATIVE_INFINITY;
            }
            else if (Double.isNaN(result)) {
                return function.apply(approach + ((approach - d) * 10));
            }
            else {
                if (d == approach) {
                    return result;
                } else if (d - approach < 0.00000000001) {
                    d = approach;
                }
            }
        }
        return Double.NaN;
    }
}