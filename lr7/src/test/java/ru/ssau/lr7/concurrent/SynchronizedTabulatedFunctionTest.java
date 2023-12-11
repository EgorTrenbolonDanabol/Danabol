package ru.ssau.lr7.concurrent;

import ru.ssau.lr7.functions.LinkedListTabulatedFunction;
import ru.ssau.lr7.functions.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SynchronizedTabulatedFunctionTest {


    private final double[] xValue = {0.0, 1.0, 2.0, 3.0};
    private final double[] yValue = {0.0, 1.0, 4.0, 9.0};
    LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(xValue, yValue);
    SynchronizedTabulatedFunction stf = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);

    @Test
    void iteratorTest() {
        int i = 0;
        for (Point point : stf) {
            assertEquals(point.x, stf.getX(i));
            assertEquals(point.y, stf.getY(i));
            ++i;
        }
    }

    @Test
    public void testApply() {
        assertEquals(1.0, stf.apply(1.0), 1e-9);
    }

    @Test
    public void testGetCount() {
        assertEquals(4, stf.getCount());
    }

    @Test
    public void testGetX() {
        assertEquals(2.0, stf.getX(2), 1e-9);
    }

    @Test
    public void testGetY() {
        assertEquals(9.0, stf.getY(3), 1e-9);
    }

    @Test
    public void testSetY() {
        stf.setY(1, 10.0);
        assertEquals(10.0, stf.getY(1), 1e-9);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(3, stf.indexOfX(3.0));
    }

    @Test
    public void testIndexOfY() {
        assertEquals(2, stf.indexOfY(4.0));
    }

    @Test
    public void testLeftBound() {
        assertEquals(0.0, stf.leftBound(), 1e-9);
    }

    @Test
    public void testRightBound() {
        assertEquals(3.0, stf.rightBound(), 1e-9);
    }

    @Test
    public void testDoSynchronously() {

        double result = stf.doSynchronously(sf -> sf.apply(1.5));
        assertEquals(1.5, result, 1e-9);


        stf.doSynchronously(sf -> {
            sf.setY(0, 10.0);
            return null;
        });
    }
}