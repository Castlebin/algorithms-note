package com.heller.jcip;

import org.junit.Assert;
import org.junit.Test;

public class LongTimeJob {
    public static Long run(long begin, long end) {
        Long result = 1L;
        while (begin <= end) {
            result = findPrimeNumber(begin++);
            System.out.println(result);
        }
        return result;
    }

    public static Long findPrimeNumber(long nTh) {
        if (nTh < 1) {
            throw new IllegalArgumentException("nTh must bigger then ZERO!");
        }
        int n = 0;
        for (long num = 2; num <= Long.MAX_VALUE; num++) {
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

    @Test
    public void testIsPrime() {
        Assert.assertTrue(isPrimeNumber(2));
        Assert.assertTrue(isPrimeNumber(3));
        Assert.assertTrue(isPrimeNumber(5));
        Assert.assertFalse(isPrimeNumber(4));
        Assert.assertFalse(isPrimeNumber(6));
    }

    /**
     * 本机，普通机器，求出第10000个素数，大概耗时4秒
     */
    @Test
    public void testLongTimeJob() {
        long start = System.currentTimeMillis();
        for (long nth = 1000; nth < 100000; nth++) {
            Long prime = findPrimeNumber(nth);
            long end = System.currentTimeMillis();
            System.out.println(nth + " -> " + prime + ", time: " + (end - start) + " ms");
            start = end;
        }
    }
}
