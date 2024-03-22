package com.drainshawty.lab1;

import com.drainshawty.lab1.task1.Asin;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestTask1 {

    private static final double EPSILON = 0.001;
    private static Random random;

    private static double randomX;
    private static double lastY = Double.NEGATIVE_INFINITY;

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
            "0.95, 1.2532359",
            "0.05, 0.050020856805770016",
            "-0.05, -0.050020856805770016"
    })
    void testAsinWithinRange(double x, double expected) {assertEquals(expected, Asin.of(x), EPSILON);}

    @ParameterizedTest
    @CsvSource({
        "0.5, -0.5",
        "0.4, -0.4",
        "1, -1",
        "0.9, -0.9"
    })
    void testAsinOnAsymmetry(double positive, double negative) {assertEquals(Asin.of(positive), -Asin.of(negative));}

    @ParameterizedTest
    @ValueSource(doubles = {
            1.1,
            -1.1,
            Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY
    })
    void testAsinOutsideRange(double x) {assertThrows(IllegalArgumentException.class, () -> Asin.of(x));}

    @ParameterizedTest
    @MethodSource("rangeProvider")
    void upUpTest(double x) {
        assertTrue(Asin.of(x) > lastY);
        lastY = Asin.of(x);
    }

    @RepeatedTest(100)
    void testAsinRandomValues() {assertEquals(Math.asin(randomX), Asin.of(randomX), EPSILON);}

    private static Stream<Double> rangeProvider() {
        return IntStream.rangeClosed(-10, 10)
                .mapToDouble(i -> i * 0.1).boxed();
    }
}
