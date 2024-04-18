package com.drainshawty.lab2.unit;

import com.drainshawty.lab2.math.functions.logariphmic.Ln;
import com.drainshawty.lab2.math.functions.logariphmic.Log;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogarithmicTest {

    static final double EPSILON = 1e-4;
    Ln ln;
    Log log2;

    Log log10;

    @BeforeAll
    void init() {
        ln = new Ln();
        log2 = new Log(2);
        log10 = new Log(10);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.001, 0.5, 1.0, 2.0})
    void testLnCalculation(double x) {
        assertEquals(Math.log(x), ln.of(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, 0.0})
    void testInvalidLnCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> ln.of(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.001, 0.5, 1.0, 2.0})
    void testLogCalculation(double x) {
        assertAll(
                () -> assertEquals(Math.log(x) / Math.log(2), log2.of(x), EPSILON),
                () -> assertEquals(Math.log(x) / Math.log(10), log10.of(x), EPSILON)
        );
    }

    @Test
    void testInvalidLogCalculation() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Log(0)),
                () -> assertThrows(ArithmeticException.class, () -> log2.of(-1))
        );
    }
}
