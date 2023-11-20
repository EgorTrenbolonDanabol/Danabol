package functions;

import exceptions.InterpolationException;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import exceptions.DifferentLengthOfArraysException;
import exceptions.ArrayIsNotSortedException;
class LinkedListTabulatedFunctionTest {
    @Test
    void testNodeToString() {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(1, 2);
        assertEquals("(1.0; 2.0)", node.toString());
    }

    @Test
    void testNodeEquals() {
        LinkedListTabulatedFunction.Node node1 = new LinkedListTabulatedFunction.Node(1, 2);
        LinkedListTabulatedFunction.Node node2 = new LinkedListTabulatedFunction.Node(1, 2);
        assertEquals(node1, node2);
    }

    @Test
    void testNodeHashCode() {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(1, 2);
        assertEquals(Objects.hash(1.0, 2.0), node.hashCode());
    }

    @Test
    void testNodeClone() throws CloneNotSupportedException {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(1, 2);
        LinkedListTabulatedFunction.Node clone = (LinkedListTabulatedFunction.Node) node.clone();
        assertEquals(node.x, clone.x);
        assertEquals(node.y, clone.y);
        assertNull(clone.next);
        assertNull(clone.prev);
    }

    @Test
    void testEquals() {
        LinkedListTabulatedFunction linkedList1 = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
        LinkedListTabulatedFunction linkedList2 = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
        assertEquals(linkedList1, linkedList2);
    }

    @Test
    void testHashCode() {
        double[] xValues1 = {1, 2, 3};
        double[] yValues1 = {1, 4, 9};
        double[] xValues2 = {1, 2, 3};
        double[] yValues2 = {1, 4, 9};

        LinkedListTabulatedFunction linkedList1 = new LinkedListTabulatedFunction(xValues1, yValues1);
        LinkedListTabulatedFunction linkedList2 = new LinkedListTabulatedFunction(xValues2, yValues2);

        assertEquals(linkedList1.hashCode(), linkedList2.hashCode());
    }

    @Test
    void testClone() {
        LinkedListTabulatedFunction linkedList = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
        LinkedListTabulatedFunction clone = (LinkedListTabulatedFunction) linkedList.clone();
        assertEquals(linkedList.toString(), clone.toString());
    }


    @Test
    public void testConstructorWithArrays() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {10.0, 20.0, 30.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(3, function.getCount());
        assertEquals(1.0, function.getX(0), 0.001);
        assertEquals(10.0, function.getY(0), 0.001);
        assertEquals(2.0, function.getX(1), 0.001);
        assertEquals(20.0, function.getY(1), 0.001);
        assertEquals(3.0, function.getX(2), 0.001);
        assertEquals(30.0, function.getY(2), 0.001);
    }

    @Test
    public void testConstructorWithMathFunction() {
        MathFunction source = new MathFunction() {
            @Override
            public double apply(double x) {
                return 1.0 / Math.tan(x); // ctg(x) = 1/tan(x)
            }
        };
        double xFrom = 1.0;
        double xTo = 4.0;
        int count = 4;

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(source, xFrom, xTo, count);

        assertEquals(4, function.getCount());
        assertEquals(1.0, function.getX(0), 0.001);
        assertEquals(0.642, function.getY(0), 0.001);
    }
    @Test
    public void testGetCount() {
        LinkedListTabulatedFunction tabulatedFunction = createSampleTabulatedFunction();
        assertEquals(5, tabulatedFunction.getCount());
    }

    @Test
    public void testGetX() {
        LinkedListTabulatedFunction tabulatedFunction = createSampleTabulatedFunction();
        assertEquals(0.0, tabulatedFunction.getX(0));
        assertEquals(1.0, tabulatedFunction.getX(1));
        assertEquals(2.0, tabulatedFunction.getX(2));
        assertEquals(3.0, tabulatedFunction.getX(3));
        assertEquals(4.0, tabulatedFunction.getX(4));
    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction tabulatedFunction = createSampleTabulatedFunction();
        assertEquals(0.0, tabulatedFunction.getY(0));
        assertEquals(1.0, tabulatedFunction.getY(1));
        assertEquals(4.0, tabulatedFunction.getY(2));
        assertEquals(9.0, tabulatedFunction.getY(3));
        assertEquals(16.0, tabulatedFunction.getY(4));
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction tabulatedFunction = createSampleTabulatedFunction();
        tabulatedFunction.setY(2, 42.0);
        assertEquals(42.0, tabulatedFunction.getY(2));
    }

    private LinkedListTabulatedFunction createSampleTabulatedFunction() {
        double[] xValues = {0.0, 1.0, 2.0, 3.0, 4.0};
        double[] yValues = {0.0, 1.0, 4.0, 9.0, 16.0};
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
    @Test
    public void testLeftBound() {
        LinkedListTabulatedFunction tabulatedFunction = createSampleTabulatedFunction();
        assertEquals(0.0, tabulatedFunction.leftBound());
    }

    @Test
    public void testRightBound() {
        LinkedListTabulatedFunction tabulatedFunction = createSampleTabulatedFunction();
        assertEquals(4.0, tabulatedFunction.rightBound());
    }
    @Test
    void testExtrapolateLeft() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(1.0, function.extrapolateLeft(0.5), 0.001);
    }
    @Test
    public void testExtrapolateRight() {
        // Создаем табулированную функцию с данными
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        // Выполняем экстраполяцию справа для x > 3.0
        double extrapolatedValue = function.extrapolateRight(3.5);

        assertEquals(7.0, extrapolatedValue, 0.001);
    }

    @Test
    public void testInterpolateWithFloorIndex() {
        // Создаем табулированную функцию с данными
        double[] xValues = {0.0, 1.0, 2.0, 3.0};
        double[] yValues = {0.0, 1.0, 4.0, 9.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        // Пытаемся выполнить интерполяцию для x = -1.0 (вне интервала)
        try {
            double interpolatedValue = function.interpolate(-1.0, 0);
            fail("InterpolationException should have been thrown.");
        } catch (InterpolationException e) {
            // InterpolationException ожидается
            assertEquals("Interpolation point is outside the interpolation interval.", e.getMessage());
        } catch (IllegalArgumentException e) {
            // IllegalArgumentException также ожидается, можно добавить соответствующую проверку
        }
    }
    @Test
    public void testIndexOfX() {
        LinkedListTabulatedFunction tabulatedFunction = createSampleTabulatedFunction();
        assertEquals(2, tabulatedFunction.indexOfX(2.0));
        try {
            tabulatedFunction.indexOfX(-1);
        } catch (IllegalArgumentException e) {
            assertEquals("the index goes beyond the acceptable values", e.getMessage());
        }
    }

    @Test
    public void testIndexOfY() {
        LinkedListTabulatedFunction tabulatedFunction = createSampleTabulatedFunction();
        assertEquals(2, tabulatedFunction.indexOfY(4.0));
        try {
            tabulatedFunction.indexOfX(-1);
        } catch (IllegalArgumentException e) {
            assertEquals("the index goes beyond the acceptable values", e.getMessage());
        }
    }
    @Test
    public void testFloorIndexOfX() {
        double[] xValues = {-3.0, 4.0, 6.0};
        double[] yValues = {9.0, 16.0, 36.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        try {
            function.floorIndexOfX(-4.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Value of x is less than the left boundary.", e.getMessage());
        }
        assertEquals(0, function.floorIndexOfX(-3.0));
        assertEquals(1, function.floorIndexOfX(4.5));
        assertEquals(1, function.floorIndexOfX(4.5));
        assertEquals(3, function.floorIndexOfX(10.0));
    }
    @Test
    public void testApply() {
        LinkedListTabulatedFunction function = createSampleTabulatedFunction();

        // Тестирование экстраполяции слева (x < leftBound())
        assertEquals(-1, function.apply(-1.0), 0.001);

        // Тестирование экстраполяции справа (x > rightBound())
        assertEquals(23, function.apply(5.0), 0.001);

        // Тестирование получения значения по существующему x
        assertEquals(4, function.apply(2.0), 0.001);
        // Тестирование интерполяции для x между существующими точками
        try {
            function.apply(1.5);
        } catch (IllegalArgumentException e) {
            assertEquals("the index goes beyond the acceptable values", e.getMessage());
        }
    }
    @Test
    public void ConstructorException() {
        double[] x1 = {1.0, 2.0, 3.0, 4.0};
        double[] x2 = {1.0, 2.0, 1.5};
        double[] y = {2.0, 4.0, 6.0};
        assertThrows(DifferentLengthOfArraysException.class, () ->{new LinkedListTabulatedFunction(x1, y);});
        assertThrows(ArrayIsNotSortedException.class, () ->{new LinkedListTabulatedFunction(x2, y);});
    }
    @Test
    public void testInterpolateWithInvalidXValue() {
        double[] xValues = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yValues = {1.0, 2.0, 3.0, 4.0, 5.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // Attempt to interpolate with an x value outside the interpolation interval
        assertThrows(InterpolationException.class, () -> {
            function.interpolate(0.5, 0);
        });
        assertThrows(InterpolationException.class, () -> {
            function.interpolate(6.0, 4);
        });
    }
    @Test
    void newToString(){
        double[] xValue = {1, 2, 2.7, 2.9, 5};
        double[] yValue = {1, 2, 3, 4, 5};


        double[] xValue3 = {5, 7};
        double[] yValue3 = {2, 7};
        LinkedListTabulatedFunction linkedListTabulatedFunction=new LinkedListTabulatedFunction(xValue,yValue);

        LinkedListTabulatedFunction linkedListTabulatedFunction2=new LinkedListTabulatedFunction(xValue3,yValue3);

        assertEquals(linkedListTabulatedFunction.toString(), "LinkedListTabulatedFunction size = 5\n[1.0; 1.0]\n[2.0; 2.0]\n[2.7; 3.0]\n[2.9; 4.0]\n[5.0; 5.0]\n");
    }
}