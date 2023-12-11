package ru.ssau.lr7.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathFunctionTest {
    @Test
    void testAndThenWithAddition() {
        MathFunction square = x -> x * x;
        MathFunction increment = x -> x + 1;

        // Создаем цепочку функций: сначала возводим в квадрат, затем прибавляем 1
        MathFunction resultFunction = square.andThen(increment);

        // Проверяем, что цепочка функций работает правильно
        assertEquals(5.0, resultFunction.apply(2.0));
        assertEquals(10.0, resultFunction.apply(3.0));
        assertEquals(26.0, resultFunction.apply(5.0));
    }

    @Test
    void testAndThenWithSubtraction() {
        MathFunction cube = x -> x * x * x;
        MathFunction decrement = x -> x - 1;

        // Создаем цепочку функций: сначала возводим в куб, затем вычитаем 1
        MathFunction resultFunction = cube.andThen(decrement);

        // Проверяем, что цепочка функций работает правильно
        assertEquals(7.0, resultFunction.apply(2.0));
        assertEquals(26.0, resultFunction.apply(3.0));
        assertEquals(124.0, resultFunction.apply(5.0));
    }

    @Test
    void testAndThenWithLinearFunctions() {
        MathFunction f1 = x -> 2 * x + 1;
        MathFunction f2 = x -> 3 * x - 2;

        MathFunction composite = f1.andThen(f2);

        // Проверяем, что композитная функция работает правильно
        assertEquals(composite.apply(5), 3 * (2 * 5 + 1) - 2);
    }

    @Test
    void testAndThenWithExponentialAndSquareFunctions() {
        MathFunction f1 = x -> Math.pow(x, 2);
        MathFunction f2 = x -> Math.exp(x);

        MathFunction composite = f1.andThen(f2);

        // Проверяем, что композитная функция работает правильно
        assertEquals(composite.apply(2), Math.exp(Math.pow(2, 2)));
    }
}