package com.heller.ch01;

public class PolyomialSum {

    public static double polyomialSum1(int[] a, double x) {
        double sum = 0;
        for (int n = 0; n < a.length; n++) {
            sum += a[n] * Math.pow(x, n);
        }
        return sum;
    }

    public static double polyomialSum2(int[] a, double x) {
        double sum = 0;
        for (int n = a.length - 1; n >= 0; n--) {
            sum = sum * x + a[n];
        }
        return sum;
    }

    public static int[] buildFators(int n) {
        int[] fators = new int[n];
        for (int i = 1; i <= n; i++) {
            fators[i - 1] = i;
        }
        return fators;
    }

}
