package com.drainshawty.lab2.math.functions.trigonometric;

import com.drainshawty.lab2.math.MathFunction;

public class Cos extends MathFunction {

    private final Sin sin;

    public Cos() {this.sin = new Sin();}

    public Cos(double precision, int maxIterations) {
        super(precision, maxIterations);
        this.sin = new Sin(precision, maxIterations);
    }

    @Override
    public double of(double x) {return sin.of(Math.PI / 2 - x);}
}
