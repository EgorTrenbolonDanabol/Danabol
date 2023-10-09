package functions;

import static org.junit.jupiter.api.Assertions.*;

class IdentityFunctionTest {

    @org.junit.jupiter.api.Test
    void apply() {
        IdentityFunction identityFunction = new IdentityFunction();
        assertEquals(5.0, identityFunction.apply(5.0), 0.0001); // Проверка тождественного преобразования
        assertEquals(-3.14, identityFunction.apply(-3.14), 0.0001); // Еще одна проверка
    }

}