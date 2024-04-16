package com.drainshawty.lab2.output;

import com.drainshawty.lab2.math.MathFunction;

import java.io.IOException;

public interface FunctionWriter {
    void write(
            MathFunction function,
            double from,
            double to,
            double step
    ) throws IOException;
}
