package ru.ssau.lr7.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitFunctionTest {
    @Test
    public void testApply() {
        UnitFunction unitFunction = new UnitFunction();

        // Проверяем, что метод apply возвращает 1 при любом входящем значении
        assertEquals(1.0, unitFunction.apply(0.0), 1e-6); // Приближенное сравнение с погрешностью 1e-6
        assertEquals(1.0, unitFunction.apply(1.0), 1e-6);
        assertEquals(1.0, unitFunction.apply(-1.0), 1e-6);
        assertEquals(1.0, unitFunction.apply(100.0), 1e-6);
        assertEquals(1.0, unitFunction.apply(-100.0), 1e-6);
    }
}