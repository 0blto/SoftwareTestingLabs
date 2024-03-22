package com.drainshawty.lab1.task1;

public class Asin {
    public static double of(double x) {
        if (Math.abs(x) > 1) throw new IllegalArgumentException("Input value must be in range [-1, 1]");
        double result = x, term = x;
        for (int k = 1; k < 320000; k++) result += (term *= x * x * (2 * k - 1) * (2 * k - 1) / (2 * k) / (2 * k + 1));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Math.asin(0.05));
        System.out.println(Math.asin(-0.05));
    }
}
