package com.drainshawty.lab2.math.functions.logariphmic;

import com.drainshawty.lab2.math.MathFunction;

public class Log extends MathFunction {

    private final Ln ln = new Ln();
    private final int base;

    public Log(int base) {
        if (base <= 0) throw new IllegalArgumentException("Base must be greater than 0.");
        this.base = base;
    }

    public Log(int base, double precision, int maxIterations) {
        super(precision, maxIterations);
        if (base <= 0) throw new IllegalArgumentException("Base must be greater than 0.");
        this.base = base;
    }

    @Override
    public double of(double x) {return ln.of(x) / ln.of(base);}
}
