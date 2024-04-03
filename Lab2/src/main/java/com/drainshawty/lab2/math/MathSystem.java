package com.drainshawty.lab2.math;

import com.drainshawty.lab2.math.functions.logariphmic.*;
import com.drainshawty.lab2.math.functions.trigonometric.*;

import static java.lang.Math.pow;

public class MathSystem extends MathFunction {

    private final Sin sin;
    private final Cos cos;
    private final Sec sec;
    private final Tan tan;
    private final Cot cot;
    private final Ln ln;
    private final Log log2;
    private final Log log10;

    public MathSystem() {
        this.sin = new Sin();
        this.cos = new Cos();
        this.sec = new Sec();
        this.tan = new Tan();
        this.cot = new Cot();
        this.ln = new Ln();
        this.log2 = new Log(2);
        this.log10 = new Log(10);
    }

    public MathSystem(double precision, int maxIterations) {
        super(precision, maxIterations);
        this.sin = new Sin(precision, maxIterations);
        this.cos = new Cos(precision, maxIterations);
        this.sec = new Sec(precision, maxIterations);
        this.tan = new Tan(precision, maxIterations);
        this.cot = new Cot(precision, maxIterations);
        this.ln = new Ln(precision, maxIterations);
        this.log2 = new Log(2, precision, maxIterations);
        this.log10 = new Log(5, precision, maxIterations);
    }

    @Override
    public double of(double x) {
        double result;
        if (x <= 0) {
            double secValue = sec.of(x);
            double tanValue = tan.of(x);
            double cotValue = cot.of(x);
            result = (pow((pow(tanValue, 3) / cotValue), 2) + secValue)/ secValue;
        } else {
            double lnValue = ln.of(x);
            double log2Value = log2.of(x);
            double log10Value = log10.of(x);
            result = pow(((pow(log10Value, 2) + log2Value) / lnValue) / (log2Value / lnValue), 2);
        }
        return result;
    }
}