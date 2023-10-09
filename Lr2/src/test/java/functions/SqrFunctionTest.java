package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {
    @Test
    public void testApply() {
        // Создаем экземпляр класса SqrFunction
        SqrFunction sqrFunction = new SqrFunction();

        // Проверяем, что метод apply правильно вычисляет квадрат числа
        assertEquals(0.0, sqrFunction.apply(0.0), 0.0001); // Ожидаемый результат: 0.0
        assertEquals(1.0, sqrFunction.apply(1.0), 0.0001); // Ожидаемый результат: 1.0
        assertEquals(4.0, sqrFunction.apply(2.0), 0.0001); // Ожидаемый результат: 4.0
        assertEquals(9.0, sqrFunction.apply(3.0), 0.0001); // Ожидаемый результат: 9.0
    }
}