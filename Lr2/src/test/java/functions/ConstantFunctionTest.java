package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantFunctionTest {
    @Test
    public void testConstructorAndGetConstant() {
        double constantValue = 5.0;
        ConstantFunction constantFunction = new ConstantFunction(constantValue);

        assertEquals(constantValue, constantFunction.getConstant());
    }

    @Test
    public void testApply() {
        double constantValue = 3.14;
        ConstantFunction constantFunction = new ConstantFunction(constantValue);

        double x = 10.0;
        double result = constantFunction.apply(x);

        assertEquals(constantValue, result);
    }

    @Test
    public void testApplyWithNegativeConstant() {
        double constantValue = -2.5;
        ConstantFunction constantFunction = new ConstantFunction(constantValue);

        double x = 7.0;
        double result = constantFunction.apply(x);

        assertEquals(constantValue, result);
    }
}