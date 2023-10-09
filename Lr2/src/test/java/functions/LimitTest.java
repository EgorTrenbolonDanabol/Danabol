package functions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class LimitTest {
    // Define a simple MathFunction for testing
    private MathFunction linearFunction = x -> 2 * x + 3;

    @Test
    public void testLimitFromBelow() {
        double result = Limit.limitFromBelow(linearFunction, 0, 0);
        assertEquals(3.0, result, 0.0001); // Expected result is 3.0
    }

    @Test
    public void testLimitFromAbove() {
        double result = Limit.limitFromAbove(linearFunction, 0, 0);
        assertEquals(3.0, result, 0.0001); // Expected result is 3.0
    }

    @Test
    public void testLimit() {
        double result = Limit.limit(linearFunction, 0, 0);
        assertEquals(3.0, result, 0.0001); // Expected result is 3.0
    }

    @Test
    public void testLimitWithInfinity() {
        MathFunction constantFunction = x -> Double.POSITIVE_INFINITY;
        double result = Limit.limit(constantFunction, 0, 0);
        assertEquals(Double.POSITIVE_INFINITY, result, 0.0001); // Expected result is Double.POSITIVE_INFINITY
    }

    @Test
    public void testLimitWithNaN() {
        MathFunction nanFunction = x -> Double.NaN;
        double result = Limit.limit(nanFunction, 0, 0);
        assertTrue(Double.isNaN(result)); // Expected result is NaN
    }

    @Test
    public void testLimitWithNegativeInfinity() {
        MathFunction negativeInfinityFunction = x -> Double.NEGATIVE_INFINITY;
        double result = Limit.limit(negativeInfinityFunction, 0, 0);
        assertEquals(Double.NEGATIVE_INFINITY, result, 0.0001); // Expected result is Double.NEGATIVE_INFINITY
    }
}