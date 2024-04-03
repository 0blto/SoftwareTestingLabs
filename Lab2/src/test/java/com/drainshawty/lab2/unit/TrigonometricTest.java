package com.drainshawty.lab2.unit;

import com.drainshawty.lab2.math.functions.trigonometric.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrigonometricTest {

    static final double EPSILON = 1e-4;
    Sin sin;
    Cos cos;
    Tan tan;
    Cot cot;
    Sec sec;

    @BeforeAll
    void init() {
        sin = new Sin();
        cos = new Cos();
        tan = new Tan();
        cot = new Cot();
        sec = new Sec();
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -Math.PI / 2, -0.75, 0.0, Math.PI / 2, 1.2, Math.PI})
    void testSinCalculation(double x) {
        assertEquals(Math.sin(x), sin.of(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testInvalidSinCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> sin.of(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -Math.PI / 2, -0.75, 0.0, Math.PI / 2, 1.2, Math.PI})
    void testCosCalculation(double x) {
        assertEquals(Math.cos(x), cos.of(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testInvalidCosCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> cos.of(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -0.45, 0.0, 0.54, Math.PI})
    void testSecCalculation(double x) {
        assertEquals(1 / Math.cos(x), sec.of(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI / 2, Math.PI / 2, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testInvalidSecCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> sec.of(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.75, 0.0, 1.2})
    void testTanCalculation(double x) {
        assertEquals(Math.tan(x), tan.of(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testInvalidTanCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> tan.of(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.75, Math.PI / 2, 1.2})
    void testCotCalculation(double x) {
        assertEquals(1 / Math.tan(x), cot.of(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testInvalidCotCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> cot.of(x));
    }
}