package com.drainshawty.lab2.integration;

import com.drainshawty.lab2.math.MathSystem;
import com.drainshawty.lab2.math.functions.logariphmic.Ln;
import com.drainshawty.lab2.math.functions.logariphmic.Log;
import com.drainshawty.lab2.math.functions.trigonometric.*;
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
public class MathSystemIntegrationTest {

    static final double EPSILON = 1e-4;
    @Mock
    Sec secMock;
    @Mock
    Tan tanMock;
    @Mock
    Cot cotMock;
    @Mock
    Ln lnMock;
    @Mock
    Log log2Mock;
    @Mock
    Log log10Mock;
    @Spy
    Sec secSpy;

    @Spy
    Tan tanSpy;

    @Spy
    Cot cotSpy;
    @Spy
    Ln lnSpy;
    Log log2Spy = spy(new Log(2));
    Log log10Spy = spy(new Log(10));

    @Test
    void testMathSystemCalculationTrigonometricFunctionsCalled() {
        MathSystem mathSystem = new MathSystem();
        setField(mathSystem, "sec", secSpy);
        setField(mathSystem, "cot", cotSpy);
        setField(mathSystem, "tan", tanSpy);
        mathSystem.of(-1.5);
        verify(secSpy, atLeastOnce()).of(anyDouble());
        verify(cotSpy, atLeastOnce()).of(anyDouble());
        verify(tanSpy, atLeastOnce()).of(anyDouble());
    }

    @Test
    void testMathSystemCalculationWithMockedTrigonometricFunctionValues() {
        MathSystem mathSystem = new MathSystem();
        setField(mathSystem, "sec", secMock);
        setField(mathSystem, "cot", cotMock);
        setField(mathSystem, "tan", tanMock);
        double x = -Math.PI / 4;
        when(secMock.of(x)).thenReturn(Math.sqrt(2));
        when(cotMock.of(x)).thenReturn(1.0);
        when(tanMock.of(x)).thenReturn(-1.0);
        assertEquals(0.85355, mathSystem.of(x), EPSILON);
    }

    @Test
    void testMathSystemCalculationLogarithmicFunctionsCalled() {
        MathSystem mathSystem = new MathSystem();
        setField(mathSystem, "ln", lnSpy);
        setField(mathSystem, "log2", log2Spy);
        setField(mathSystem, "log10", log10Spy);
        mathSystem.of(1.5);
        verify(lnSpy, atLeastOnce()).of(anyDouble());
        verify(log2Spy, atLeastOnce()).of(anyDouble());
        verify(log10Spy, atLeastOnce()).of(anyDouble());
    }

    @Test
    void testMathSystemCalculationWithMockedLogarithmicFunctionValues() {
        MathSystem mathSystem = new MathSystem();
        setField(mathSystem, "ln", lnMock);
        setField(mathSystem, "log2", log2Mock);
        setField(mathSystem, "log10", log10Mock);
        double x = 2.25;
        when(lnMock.of(x)).thenReturn(0.81093);
        when(log2Mock.of(x)).thenReturn(1.16992);
        when(log10Mock.of(x)).thenReturn(0.35218);
        assertEquals(1.223272, mathSystem.of(x), EPSILON);
    }
}
