package com.drainshawty.lab2.math.functions.trigonometric;

import com.drainshawty.lab2.math.MathFunction;

public class Sin extends MathFunction {

    public Sin() {}

    public Sin(double precision, int maxIterations) {super(precision, maxIterations);}

    @Override
    public double of(double x) {
        if (!Double.isFinite(x)) throw new ArithmeticException(String.format("Function value for argument %f doesn't exist.", x));
        double result = x, term = x;
        int k = 0;
        while (Math.abs(term) > precision && (k += 1) > 0) result += (term *= -x * x / ((2 * k) * (2 * k + 1)));
        return Math.abs(result) < precision ? 0 : result;
    }

}
