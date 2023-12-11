package ru.ssau.lr7.functions.factory;

import ru.ssau.lr7.functions.LinkedListTabulatedFunction;
import ru.ssau.lr7.functions.TabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionFactoryTest {

    @Test
    void create() {
        TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 4.0, 9.0};
        TabulatedFunction function = factory.create(xValues, yValues);
        assertTrue(function instanceof LinkedListTabulatedFunction);
    }
}