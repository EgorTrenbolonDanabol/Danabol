package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTabulatedFunctionTest {
    @Test
    public void testInterpolate() {
        // Создаем mock-объект с двумя точками
        MockTabulatedFunction mockFunction = new MockTabulatedFunction(0.0, 1.0, 1.0, 2.0);

        // Тестирование interpolate()
        double x = 0.5;
        double interpolatedValue = mockFunction.interpolate(x, mockFunction.floorIndexOfX(x));
        assertEquals(1.5, interpolatedValue, 1e-5); // Проверяем, что значение близко к ожидаемому
    }

    @Test
    public void testApply() {
        // Создаем mock-объект с двумя точками
        MockTabulatedFunction mockFunction = new MockTabulatedFunction(0.0, 1.0, 1.0, 2.0);

        // Тестирование apply()
        double inputValue = 0.75;
        double appliedValue = mockFunction.apply(inputValue);
        assertEquals(1.75, appliedValue, 1e-5); // Проверяем, что значение близко к ожидаемому
    }
}