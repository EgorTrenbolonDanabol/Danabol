package ru.ssau.lr7.operations;

import ru.ssau.lr7.functions.MathFunction;
import ru.ssau.lr7.functions.SqrFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeftSteppingDifferentialOperatorTest {

    @Test
    void derive() {
        SteppingDifferentialOperator leftSteppingDifferentialOperator = new LeftSteppingDifferentialOperator(1);
        SqrFunction sqrFunction = new SqrFunction();
        MathFunction deferentialSqrFunction = leftSteppingDifferentialOperator.derive(sqrFunction);
        assertEquals(9, deferentialSqrFunction.apply(5), 1e-5); // Замените 1 на желаемую погрешность (например, 1e-5)
        assertEquals(29, deferentialSqrFunction.apply(15), 1e-5); // Замените 1 на желаемую погрешность (например, 1e-5)
    }
}