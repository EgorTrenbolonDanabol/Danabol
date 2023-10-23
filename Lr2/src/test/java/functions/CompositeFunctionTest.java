package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompositeFunctionTest {
    @Test
    public void testCompositeFunctionWithIdentityAndSqr() {
        // Создаем две функции: f(x) = x и g(x) = x^2
        IdentityFunction f = new IdentityFunction();
        SqrFunction g = new SqrFunction();

        // Создаем композитную функцию: h(x) = g(f(x)) = (x)^2 = x^2
        CompositeFunction h = new CompositeFunction(f, g);

        // Проверяем, что композитная функция возвращает правильные значения
        assertEquals(0.0, h.apply(0), 1e-5); // h(0) = 0^2 = 0
        assertEquals(1.0, h.apply(1), 1e-5); // h(1) = 1^2 = 1
        assertEquals(4.0, h.apply(2), 1e-5); // h(2) = 2^2 = 4
        assertEquals(9.0, h.apply(3), 1e-5); // h(3) = 3^2 = 9
    }
    @Test
    void andThenForList(){
        // Создаем две функции: f(x) = x и g(x) = x^2
        IdentityFunction f = new IdentityFunction();
        SqrFunction g = new SqrFunction();

        // Создаем композитную функцию: h(x) = g(f(x)) = (x)^2 = x^2
        CompositeFunction h = new CompositeFunction(f, g);

        // Проверяем, что композитная функция возвращает правильные значения
        assertEquals(0.0, h.apply(0), 1e-5); // h(0) = 0^2 = 0
        assertEquals(1.0, h.apply(1), 1e-5); // h(1) = 1^2 = 1
        assertEquals(4.0, h.apply(2), 1e-5); // h(2) = 2^2 = 4
        assertEquals(9.0, h.apply(3), 1e-5); // h(3) = 3^2 = 9
    }
}