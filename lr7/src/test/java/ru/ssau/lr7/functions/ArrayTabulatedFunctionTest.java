package ru.ssau.lr7.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import ru.ssau.lr7.exceptions.DifferentLengthOfArraysException;
import ru.ssau.lr7.exceptions.ArrayIsNotSortedException;
import ru.ssau.lr7.exceptions.InterpolationException;

class ArrayTabulatedFunctionTest {
    @Test
    public void testConstructorWithArrays() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(3, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(2.0, function.getX(1));
        assertEquals(3.0, function.getX(2));
        assertEquals(2.0, function.getY(0));
        assertEquals(4.0, function.getY(1));
        assertEquals(6.0, function.getY(2));
    }

    @Test
    public void testConstructorWithMathFunction() {
        MathFunction source = x -> x * 2;
        double xFrom = 1.0;
        double xTo = 3.0;
        int count = 3;
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, xFrom, xTo, count);

        assertEquals(3, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(2.0, function.getX(1));
        assertEquals(3.0, function.getX(2));
        assertEquals(2.0, function.getY(0));
        assertEquals(4.0, function.getY(1));
        assertEquals(6.0, function.getY(2));
    }

    @Test
    public void testSetY() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        function.setY(1, 5.0);
        assertEquals(5.0, function.getY(1));
    }

    @Test
    public void testLeftBound() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(1.0, function.leftBound());
    }

    @Test
    public void testRightBound() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(3.0, function.rightBound());
    }

    @Test
    public void testIndexOfX() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(1, function.indexOfX(2.0));
        assertEquals(-1, function.indexOfX(4.0));
    }

    @Test
    public void testFloorIndexOfX() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(0, function.floorIndexOfX(1.0));
        assertEquals(1, function.floorIndexOfX(2.5));
        assertEquals(2, function.floorIndexOfX(4.0));
    }
    @Test
    public void testEquals() {
        double[] xValues1 = {1.0, 2.0, 3.0};
        double[] yValues1 = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(xValues1, yValues1);

        double[] xValues2 = {1.0, 2.0, 3.0};
        double[] yValues2 = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(xValues2, yValues2);

        assertTrue(function1.equals(function2));
    }
    @Test
    public void testHashCode() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        int expectedHashCode = function.hashCode();
        assertEquals(expectedHashCode, function.hashCode());
    }
    @Test
    public void testClone() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);

        ArrayTabulatedFunction clonedFunction = (ArrayTabulatedFunction) originalFunction.clone();
        assertTrue(originalFunction != clonedFunction);
        assertTrue(originalFunction.getClass() == clonedFunction.getClass());
        assertEquals(originalFunction, clonedFunction);
    }
    @Test
    public void testConstructorWithTooFewPoints() {
        double[] xValues = {1.0};
        double[] yValues = {2.0};

        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(xValues, yValues));
    }
    @Test
    public void ConstructorException() {
        double[] x1 = {1.0, 2.0, 3.0, 4.0};
        double[] x2 = {1.0, 2.0, 1.5};
        double[] y = {2.0, 4.0, 6.0};
        assertThrows(DifferentLengthOfArraysException.class, () ->{new ArrayTabulatedFunction(x1, y);});
        assertThrows(ArrayIsNotSortedException.class, () ->{new ArrayTabulatedFunction(x2, y);});
    }
    @Test
    public void testInterpolateWithInvalidFloorIndex() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 2.0, 3.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // Attempt to interpolate with an invalid floorIndex
        assertThrows(InterpolationException.class, () -> {
            function.interpolate(2.5, -1); // -1 is an invalid floorIndex
        });
        assertThrows(InterpolationException.class, () -> {
            function.interpolate(2.5, 3); // 3 is an invalid floorIndex
        });
    }
    @Test
    public void testInterpolateWithInvalidXValue() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 2.0, 3.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // Attempt to interpolate with an x value outside the interpolation interval
        assertThrows(InterpolationException.class, () -> {
            function.interpolate(0.5, 0);
        });
        assertThrows(InterpolationException.class, () -> {
            function.interpolate(4.0, 0);
        });
    }
    @Test
    public void IteratorWhileTest() {
        double[] x = {2.0, 4.0, 5.0};
        double[] y = {4.0, 8.0, 10.0};
        ArrayTabulatedFunction f = new ArrayTabulatedFunction(x, y);
        Iterator<Point> iterator = f.iterator();
        int i = 0;
        while(iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(x[i], point.x);
            assertEquals(y[i++], point.y);
        }
    }

    @Test
    public void IteratorForEachTest() {
        double[] x = {2.0, 4.0, 5.0};
        double[] y = {4.0, 8.0, 10.0};
        ArrayTabulatedFunction f = new ArrayTabulatedFunction(x, y);
        int i = 0;
        for (Point point : f) {
            assertEquals(x[i], point.x);
            assertEquals(y[i++], point.y);
        }
    }
}