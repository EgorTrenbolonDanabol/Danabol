package ru.ssau.lr7.operations;

import ru.ssau.lr7.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}