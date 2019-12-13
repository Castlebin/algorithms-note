package com.heller.jcip;

import org.junit.Assert;
import org.junit.Test;

public class PrimeNumberGenerator {

    public static long findPrimeNumber(long nTh) {
        if (nTh < 1) {
            throw new IllegalArgumentException("nTh must bigger then ZERO!");
        }
        int n = 0;
        for (long num = 2; num < Long.MAX_VALUE; num++) {
            if (isPrimeNumber(num)) {
                n++;
                if (n == nTh) {
                    return num;
                }
            }
        }

        throw new RuntimeException("over long num range to find " + nTh + "th prime number");
    }

    public static boolean isPrimeNumber(long num) {
        for (long divisor = 2; divisor < num; divisor++) {
            if (num % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public static void runALongTimeJob(long m, long n) {
        long start = System.currentTimeMillis();
        for (long nth = m; nth < n; nth++) {
            long prime = findPrimeNumber(nth);
            long end = System.currentTimeMillis();
            System.out.println(nth + " -> " + prime + ", time: " + (end - start) + " ms, thread: " + Thread.currentThread().getName());
            start = end;
        }
    }

    @Test
    public void testIsPrime() {
        Assert.assertTrue(isPrimeNumber(2));
        Assert.assertTrue(isPrimeNumber(3));
        Assert.assertTrue(isPrimeNumber(5));
        Assert.assertFalse(isPrimeNumber(4));
        Assert.assertFalse(isPrimeNumber(6));
    }

    /**
     * 本机，普通机器，求第4000个素数，大概耗时 700 ms
     */
    @Test
    public void testLongTimeJob() {
        runALongTimeJob(4000, 100000);
    }

}
