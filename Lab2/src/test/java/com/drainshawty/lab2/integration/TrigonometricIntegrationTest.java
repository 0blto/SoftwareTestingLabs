package com.drainshawty.lab2.integration;

import com.drainshawty.lab2.math.functions.trigonometric.Cos;
import com.drainshawty.lab2.math.functions.trigonometric.Cot;
import com.drainshawty.lab2.math.functions.trigonometric.Sec;
import com.drainshawty.lab2.math.functions.trigonometric.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.drainshawty.lab2.ReflectionUtil.setField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrigonometricIntegrationTest {

    static final double EPSILON = 1e-4;
    @Mock
    Sin sinMock;
    @Spy
    Sin sinSpy;
    @Mock
    Cos cosMock;
    @Spy
    Cos cosSpy;

    @Test
    void testCosCalculationSinFunctionCalled() {
        Cos cos = new Cos();
        setField(cos, "sin", sinSpy);
        cos.of(1);
        verify(sinSpy, times(1)).of(anyDouble());
    }

    @Test
    void testCosCalculationWithMockedSinValues() {
        Cos cos = new Cos();
        setField(cos, "sin", sinMock);
        when(sinMock.of(Math.PI / 2)).thenReturn(1.0);
        assertEquals(1.0, cos.of(0), EPSILON);
    }

    @Test
    void testSecCalculationCosFunctionCalled() {
        Sec sec = new Sec();
        setField(sec, "cos", cosSpy);
        sec.of(1);
        verify(cosSpy, times(1)).of(anyDouble());
    }

    @Test
    void testSecCalculationWithMockedCosValues() {
        Sec sec = new Sec();
        setField(sec, "cos", cosMock);
        double x = 0;
        when(cosMock.of(x)).thenReturn(1.0);
        assertEquals(1.0, sec.of(x), EPSILON);
    }

    @Test
    void testCotCalculationCosAndSinFunctionsCalled() {
        Cot cot = new Cot();
        setField(cot, "sin", sinSpy);
        setField(cot, "cos", cosSpy);
        cot.of(1);
        verify(sinSpy, times(1)).of(anyDouble());
        verify(cosSpy, times(1)).of(anyDouble());
    }

    @Test
    void testCotCalculationWithMockedCosAndSinValues() {
        Cot cot = new Cot();
        setField(cot, "cos", cosMock);
        setField(cot, "sin", sinMock);
        double x = 5;
        when(cosMock.of(x)).thenReturn(0.28366218);
        when(sinMock.of(x)).thenReturn(-0.95892427);
        assertEquals(-0.2958, cot.of(x), EPSILON);
    }
}
