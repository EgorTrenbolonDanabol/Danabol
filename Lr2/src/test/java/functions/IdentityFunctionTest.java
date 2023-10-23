package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityFunctionTest {
    @Test
    public void testApply() {
        IdentityFunction identityFunction = new IdentityFunction();
        double result = identityFunction.apply(5.0);
        assertEquals(5.0, result, 0.0001); // Проверяем, что apply(5.0) возвращает 5.0
    }

    @Test
    public void testToString() {
        IdentityFunction identityFunction = new IdentityFunction();
        String result = identityFunction.toString();
        assertEquals("IdentityFunction", result); // Проверяем, что toString() возвращает "IdentityFunction"
    }

    @Test
    public void testEquals() {
        IdentityFunction identityFunction1 = new IdentityFunction();
        IdentityFunction identityFunction2 = new IdentityFunction();
        IdentityFunction differentFunction = new IdentityFunction() {
            // Anonymous subclass to make it different
        };

        assertTrue(identityFunction1.equals(identityFunction2));
        assertTrue(identityFunction2.equals(identityFunction1));
        assertFalse(identityFunction1.equals(differentFunction));
    }
    @Test
    public void testHashCode() {
        IdentityFunction function1 = new IdentityFunction();
        IdentityFunction function2 = new IdentityFunction();

        assertEquals(function1.hashCode(), function2.hashCode()); // Проверяем, что хэш-коды одинаковых объектов равны
    }

    @Test
    public void testClone() {
        IdentityFunction original = new IdentityFunction();
        IdentityFunction clone = (IdentityFunction) original.clone();

        assertNotSame(original, clone); // Проверяем, что клон и оригинал - разные объекты
        assertEquals(original.getClass(), clone.getClass()); // Проверяем, что классы совпадают
    }
}