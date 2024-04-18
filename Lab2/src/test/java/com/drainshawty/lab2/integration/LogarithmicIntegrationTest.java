package com.drainshawty.lab2.integration;

import com.drainshawty.lab2.math.functions.logariphmic.Ln;
import com.drainshawty.lab2.math.functions.logariphmic.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.drainshawty.lab2.ReflectionUtil.setField;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogarithmicIntegrationTest {

    static final double EPSILON = 1e-4;
    @Mock
    Ln lnMock;
    @Spy
    Ln lnSpy;

    @Test
    void testLog2CalculationLnFunctionCalled() {
        Log log2 = new Log(2);
        setField(log2, "ln", lnSpy);
        log2.of(8);
        verify(lnSpy, atLeastOnce()).of(anyDouble());
    }

    @Test
    void testLog2CalculationWithMockedLnValues() {
        Log log2 = new Log(2);
        setField(log2, "ln", lnMock);
        when(lnMock.of(2)).thenReturn(0.69314);
        when(lnMock.of(8)).thenReturn(2.07944);
        assertEquals(3, log2.of(8), EPSILON);
    }
}