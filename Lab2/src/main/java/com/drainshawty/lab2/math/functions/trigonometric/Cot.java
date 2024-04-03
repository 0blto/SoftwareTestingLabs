package com.drainshawty.lab2.math.functions.trigonometric;

import com.drainshawty.lab2.math.MathFunction;

public class Cot extends MathFunction {

    private final Sin sin;
    private final Cos cos;

    public Cot() {
        this.sin = new Sin();
        this.cos = new Cos();
    }

    public Cot(double precision, int maxIterations) {
        super(precision, maxIterations);
        this.sin = new Sin(precision, maxIterations);
        this.cos = new Cos(precision, maxIterations);
    }

    @Override
    public double of(double x) {
        double sinValue = sin.of(x);
        if (sinValue == 0) throw new ArithmeticException(String.format("Function value for argument %f doesn't exist.", x));
        return cos.of(x) / sinValue;
    }
}
