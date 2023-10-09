package functions;

public class IntegralCalculator implements MathFunction {
    private static final double INCREMENT = 1E-4;
    private final double lowerLimit;
    private final double upperLimit;
    private MathFunction function;

    public IntegralCalculator(MathFunction function, double lowerLimit, double upperLimit) {
        this.function = function;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    @Override
    public double apply(double x) {
        return integral(lowerLimit, upperLimit, function);
    }

    private double integral(double a, double b, MathFunction function) {
        double area = 0;
        double modifier = 1;
        if (a > b) {
            double tempA = a;
            a = b;
            b = tempA;
            modifier = -1;
        }
        for (double i = a + INCREMENT; i < b; i += INCREMENT) {
            double dFromA = i - a;
            area += (INCREMENT / 2) * (function.apply(a + dFromA) + function.apply(a + dFromA - INCREMENT));
        }
        return (Math.round(area * 1000.0) / 1000.0) * modifier;
    }
}