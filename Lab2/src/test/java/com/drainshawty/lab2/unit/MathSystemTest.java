package com.drainshawty.lab2.unit;

import com.drainshawty.lab2.math.MathSystem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathSystemTest {

    static final double EPSILON = 1e-4;
    MathSystem mathSystem;

    @BeforeAll
    void init() {
        mathSystem = new MathSystem();
    }

    @ParameterizedTest
    @CsvSource({
            "0.00939155, 0.001",
            "0.488559, 0.1",
            "0.997374, 0.99",
            "1.18945, 2.0",
            "1, -0.001",
            "5.75113, -1",
            "0.980085, -3",
    })
    void testMathSystemCalculation(double expected, double x) {assertEquals(expected, mathSystem.of(x), EPSILON);}

    @ParameterizedTest
    @ValueSource(doubles = {0.0, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, -Math.PI/2})
    void testInvalidCosCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> mathSystem.of(x));
    }
}
