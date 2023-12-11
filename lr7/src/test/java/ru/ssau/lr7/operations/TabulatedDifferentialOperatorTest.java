package ru.ssau.lr7.operations;

import ru.ssau.lr7.functions.ArrayTabulatedFunction;
import ru.ssau.lr7.functions.LinkedListTabulatedFunction;
import ru.ssau.lr7.functions.TabulatedFunction;
import ru.ssau.lr7.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.LinkedListTabulatedFunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TabulatedDifferentialOperatorTest {
    double[] xValues = {0.0, 1.0, 2.0, 3.0, 4.0};
    double[] yValues = {0.0, 1.0, 4.0, 9.0, 16.0};

    @Test
    void testSetF() {
        LinkedListTabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator();
        tabulatedDifferentialOperator.setF(linkedListTabulatedFunctionFactory);


        assertEquals(linkedListTabulatedFunctionFactory.getClass(), tabulatedDifferentialOperator.getF().getClass());

        ArrayTabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();


        tabulatedDifferentialOperator.setF(arrayTabulatedFunctionFactory);
        assertEquals(arrayTabulatedFunctionFactory.getClass(), tabulatedDifferentialOperator.getF().getClass());
    }

    @Test
    void testDerive() {
        double[] xValues = new double[] {1.0, 2.0, 3.0, 4.0};
        double[] yValues = new double[] {1.0, 4.0, 9.0, 16.0};

        LinkedListTabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(linkedListTabulatedFunctionFactory);
        TabulatedFunction arrayTabulatedFunction = linkedListTabulatedFunctionFactory.create(xValues, yValues);
        TabulatedFunction derive = tabulatedDifferentialOperator.derive(arrayTabulatedFunction);

        assertEquals(3, derive.getY(0));
        assertEquals(5, derive.getY(1));
        assertEquals(7, derive.getY(2));
        assertEquals(7, derive.getY(3));
    }
    @Test
    void deriveSynchronously() {
        double[] xValue = {1.0, 2.0, 3.0, 4.0};
        double[] yValue = {1.0, 4.0, 9.0, 16.0};
        LinkedListTabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(linkedListTabulatedFunctionFactory);
        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValue, yValue);
        TabulatedFunction derive = tabulatedDifferentialOperator.deriveSynchronously(arrayTabulatedFunction);
        for(int i = 0; i < arrayTabulatedFunction.getCount(); i++){
            assertEquals(derive.getX(i), arrayTabulatedFunction.getX(i));
        }
        assertEquals(3, derive.getY(0));
        assertEquals(5, derive.getY(1));
        assertEquals(7, derive.getY(2));
        assertEquals(7, derive.getY(3));

        ArrayTabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedDifferentialOperator tabulatedDifferentialOperator1 = new TabulatedDifferentialOperator(arrayTabulatedFunctionFactory);
        TabulatedFunction list = new LinkedListTabulatedFunction(xValue, yValue);
        derive = tabulatedDifferentialOperator1.deriveSynchronously(list);
        for(int i = 0; i < list.getCount(); i++){
            assertEquals(derive.getX(i), list.getX(i));
        }
        assertEquals(3, derive.getY(0));
        assertEquals(5, derive.getY(1));
        assertEquals(7, derive.getY(2));
        assertEquals(7, derive.getY(3));
    }

}