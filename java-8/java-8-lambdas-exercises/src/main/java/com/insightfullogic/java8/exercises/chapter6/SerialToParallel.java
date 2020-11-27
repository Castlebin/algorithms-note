package com.insightfullogic.java8.exercises.chapter6;

import com.insightfullogic.java8.exercises.Exercises;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SerialToParallel {

    public static int sumOfSquares(IntStream range) {
        return Exercises.replaceThisWithSolution();
    }

    public static int sequentialSumOfSquares(IntStream range) {
        return Exercises.replaceThisWithSolution();
    }

    public static double[] simpleMovingAverage(double[] values, int n) {
        double[] sums = Arrays.copyOf(values, n);
        Arrays.parallelPrefix(sums, Double::sum);
        int start = n - 1;
        return IntStream.range(start, sums.length)
                .mapToDouble(i -> {
                    double prefix = i == start? 0 : sums[i - n];
                    return (sums[i] - prefix) / n;
                })
                .toArray();
    }

}
