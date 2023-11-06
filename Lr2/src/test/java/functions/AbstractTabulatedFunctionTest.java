package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.ArrayIsNotSortedException;

import exceptions.DifferentLengthOfArraysException;

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
    @Test
    void testCheckLengthIsTheSame() {
        // Подготавливаем тестовые данные
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};

        // Вызываем метод и ожидаем, что он не выбросит исключение
        assertDoesNotThrow(() -> AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues));

        // Изменяем длину одного из массивов, ожидаем исключение DifferentLengthOfArraysException
        double[] xValues2 = {1.0, 2.0, 3.0};
        double[] yValues2 = {4.0, 5.0};
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(xValues2, yValues2));
    }

    @Test
    public void testCheckSorted() {
        // Подготавливаем данные для теста
        double[] xValues = {1.0, 3.0, 2.0};

        // Вызываем метод и ожидаем исключения
        try {
            AbstractTabulatedFunction.checkSorted(xValues);
            // Если исключение не было брошено, то тест не прошел
            fail("Expected ArrayIsNotSortedException");
        } catch (ArrayIsNotSortedException e) {
            // Исключение было успешно брошено, тест прошел
            assertTrue(true);
        }
    }
}