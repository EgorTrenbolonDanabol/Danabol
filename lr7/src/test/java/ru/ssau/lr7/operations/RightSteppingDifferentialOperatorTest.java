package ru.ssau.lr7.operations;

import ru.ssau.lr7.functions.MathFunction;
import ru.ssau.lr7.functions.SqrFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RightSteppingDifferentialOperatorTest {

    @Test
    void derive() {
        SteppingDifferentialOperator rightSteppingDifferentialOperator = new RightSteppingDifferentialOperator(1);
        SqrFunction sqrFunction = new SqrFunction();
        MathFunction deferentialSqrFunction = rightSteppingDifferentialOperator.derive(sqrFunction);
        assertEquals(15, deferentialSqrFunction.apply(7), 1e-5); // Замените 1 на желаемую погрешность (например, 1e-5)
        assertEquals(17, deferentialSqrFunction.apply(8), 1e-5); // Замените 1 на желаемую погрешность (например, 1e-5)
    }
}