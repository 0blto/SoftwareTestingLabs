package com.drainshawty.lab1;

import com.drainshawty.lab1.task1.Asin;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestTask1 {

    private static final double EPSILON = 0.001;
    private static Random random;

    private static double randomX;

    @BeforeAll
    static void init() {random = new Random();}

    @BeforeEach
    void newX() {randomX = random.nextDouble() * 2 - 1;}

    @ParameterizedTest
    @CsvSource({
            "0.5, 0.52359877559",
            "-0.5, -0.52359877559",
            "0, 0",
            "1, 1.57079632679",
            "-1, -1.57079632679",
            "-0.95, -1.2532359",
            "0.95, 1.2532359"
    })
    void testAsinWithinRange(double x, double expected) {assertEquals(expected, Asin.of(x), EPSILON);}

    @ParameterizedTest
    @ValueSource(doubles = {
            1.1,
            -1.1,
            Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY
    })
    void testAsinOutsideRange(double x) {assertThrows(IllegalArgumentException.class, () -> Asin.of(x));}

    @RepeatedTest(100)
    void testAsinRandomValues() {assertEquals(Math.asin(randomX), Asin.of(randomX), EPSILON);}
}
