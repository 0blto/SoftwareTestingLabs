package com.drainshawty.lab2.integration;

import com.drainshawty.lab2.math.functions.logariphmic.Ln;
import com.drainshawty.lab2.math.functions.logariphmic.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.drainshawty.lab2.ReflectionUtil.setField;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogarithmicIntegrationTest {

    static final double EPSILON = 1e-4;

    static final Map<Double, Double> LNS = new HashMap<Double, Double>() {{
        put(2.0, 0.69314);
        put(8.0, 2.079441);
        put(10.0, 2.302585);
        put(100.0, 4.60517);
    }};
    @Mock
    Ln lnMock;
    @Spy
    Ln lnSpy;

    @Test
    void testLog2CalculationLnFunctionCalled() {
        List<Integer> ns = List.of(8, 16, 32);
        Log log2 = new Log(2);
        setField(log2, "ln", lnSpy);
        for (Integer n : ns) {
            reset(lnSpy);
            log2.of(n);
            verify(lnSpy, times(n)).of(anyDouble());
        }
    }

    @Test
    void testLog2CalculationWithMockedLnValues() {
        Log log2 = new Log(2);
        Log log10 = new Log(10);
        setField(log2, "ln", lnMock);
        setField(log10, "ln", lnMock);
        for (Double d : LNS.keySet()) when(lnMock.of(d)).thenReturn(LNS.get(d));
        assertAll(() -> {
            assertEquals(3, log2.of(8), EPSILON);
            assertEquals(2, log10.of(100), EPSILON);
        });
    }
}