package operations;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
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

        // Убедитесь, что установленная фабрика соответствует фабрике, которая была установлена.
        assertEquals(linkedListTabulatedFunctionFactory.getClass(), tabulatedDifferentialOperator.getF().getClass());

        ArrayTabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();

        // Установите другую фабрику и проверьте, что она теперь соответствует установленной фабрике.
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
}