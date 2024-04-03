package com.drainshawty.lab2.math.functions.trigonometric;

import com.drainshawty.lab2.math.MathFunction;

public class Tan extends MathFunction {

    private final Sin sin;
    private final Cos cos;

    public Tan() {
        this.sin = new Sin();
        this.cos = new Cos();
    }

    public Tan(double precision, int maxIterations) {
        super(precision, maxIterations);
        this.sin = new Sin(precision, maxIterations);
        this.cos = new Cos(precision, maxIterations);
    }

    @Override
    public double of(double x) {
        double cosValue = cos.of(x);
        if (cosValue == 0)
            throw new ArithmeticException(String.format("Function value for argument %f doesn't exist.", x));
        return sin.of(x) / cosValue;
    }
}
