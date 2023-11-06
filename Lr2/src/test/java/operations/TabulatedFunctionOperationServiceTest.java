package operations;

import functions.*;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionOperationServiceTest {
    private double[] xValues = {0.0, 1.0, 2.0, 3.0};
    private double[] yValues = {0.0, 1.0, 2.0, 3.0};
    private double[] x1 = {0.0, 1.0, 2.0, 3.0};
    private double[] y1 = {0.0, 2.0, 4.0, 6.0};
    private double[] x2 = {0.0, 1.0, 2.0, 3.0};
    private double[] y2 = {0.0, 0.0, 0.0, 0.0};

    @Test
    void testAsPoints() {
        double[] x = {1.0, 2.0, 3.0};
        double[] y = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction f = new ArrayTabulatedFunction(x, y);
        Point[] points1 = new Point[f.getCount()];
        int i = 0;
        for(Point point : f)
            points1[i++] = point;
        Point[] points2 = TabulatedFunctionOperationService.asPoints(f);
        for(i = 0; i < 3; i++) {
            assertEquals(points1[i].x, points2[i].x);
            assertEquals(points1[i].y, points2[i].y);
        }
    }

    @Test
    void testAdditionSubtraction() {
        ArrayTabulatedFunction ans1 = new ArrayTabulatedFunction(x1, y1);
        ArrayTabulatedFunction ans2 = new ArrayTabulatedFunction(x2, y2);
        ArrayTabulatedFunction a = new ArrayTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction b = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunctionOperationService op = new TabulatedFunctionOperationService();
        assertEquals(ans1, op.Addition(a, b));
        assertEquals(ans2, op.Subtraction(b, a));
    }

    @Test
    void testMultiplicationDivision() {
        double[] xValues = {1.0, 2.0, 3.0, 4.0};
        double[] yValues = {1.0, 2.0, 3.0, 4.0};
        double[] x1 = {1.0, 2.0, 3.0, 4.0};
        double[] y1 = {1.0, 4.0, 9.0, 16.0};
        double[] x2 = {1.0, 2.0, 3.0, 4.0};
        double[] y2 = {1.0, 1.0, 1.0, 1.0};

        ArrayTabulatedFunction ans1 = new ArrayTabulatedFunction(x1, y1);
        ArrayTabulatedFunction ans2 = new ArrayTabulatedFunction(x2, y2);

        ArrayTabulatedFunction a = new ArrayTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction b = new LinkedListTabulatedFunction(xValues, yValues);

        TabulatedFunctionOperationService op = new TabulatedFunctionOperationService();

        // Test Multiplication
        assertEquals(ans1, op.Multiplication(a, b));

        // Test Division
        assertEquals(ans2, op.Division(a, b));
    }
}