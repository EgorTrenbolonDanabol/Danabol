package ru.ssau.lr7.operations;

import ru.ssau.lr7.functions.MathFunction;

public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator{
    RightSteppingDifferentialOperator(double step) {
        super(step);
    }
    public MathFunction derive(MathFunction function) {
        return ((x)->(function.apply(x+step)-function.apply(x))/step);
    }
}