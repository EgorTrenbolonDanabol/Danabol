package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZeroFunctionTest {
    @Test
    public void testApplyWithPositiveValue() {
        ZeroFunction zeroFunction = new ZeroFunction();
        double result = zeroFunction.apply(5.0);
        assertEquals(0.0, result, 0.0001); // Проверяем, что результат равен 0 с заданной погрешностью
    }

    @Test
    public void testApplyWithNegativeValue() {
        ZeroFunction zeroFunction = new ZeroFunction();
        double result = zeroFunction.apply(-2.0);
        assertEquals(0.0, result, 0.0001); // Проверяем, что результат равен 0 с заданной погрешностью
    }

    @Test
    public void testApplyWithZeroValue() {
        ZeroFunction zeroFunction = new ZeroFunction();
        double result = zeroFunction.apply(0.0);
        assertEquals(0.0, result, 0.0001); // Проверяем, что результат равен 0 с заданной погрешностью
    }
}