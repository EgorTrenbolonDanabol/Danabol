package ru.ssau.lr7.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegralCalculatorTest {
    private static final double DELTA = 1E-2;

    @Test
    public void testApply() {
        double lowerLimit = 0.0; // Нижний предел интегрирования
        double upperLimit = 1.0; // Верхний предел интегрирования

        // Создаем экземпляр IntegralCalculator с указанием функции и пределов интегрирования
        IntegralCalculator calculator = new IntegralCalculator(new MathFunction() {
            @Override
            public double apply(double x) {
                return Math.pow(x, 2);
            }
        }, lowerLimit, upperLimit);

        double[] xValues = {0.0, 1.0, 2.0, 3.0};
        double[] yValues = {0.0, 1.0, 4.0, 9.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        // Вычисляем интеграл для функции x^2
        MathFunction result = calculator.andThen(function);

        // Ожидаемый результат
        double expected = 0.9990000000000006; // Точное значение интеграла x^2 от 0 до 1

        // Проверяем результат с учетом допустимой погрешности
        assertEquals(expected, result.apply(3), DELTA);
    }
}