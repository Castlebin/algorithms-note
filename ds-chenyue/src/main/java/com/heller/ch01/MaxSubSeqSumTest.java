package com.heller.ch01;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class MaxSubSeqSumTest {

    @Test
    public void maxSubSeqSum1() {
        System.out.println(MaxSubSeqSum.maxSubSeqSum1(a));
    }

    @Test
    public void maxSubSeqSum2() {
        System.out.println(MaxSubSeqSum.maxSubSeqSum2(a));
    }

    @Test
    public void maxSubSeqSum3() {
        System.out.println(MaxSubSeqSum.maxSubSeqSum3(a));
    }

    @Test
    public void maxSubSeqSum4() {
        System.out.println(MaxSubSeqSum.maxSubSeqSum4(a));
    }

    private static int[] a;

    @BeforeClass
    public static void setUp() throws Exception {
        a = MaxSubSeqSum.buildSeq(1000);
        System.out.println(Arrays.toString(a));
    }

}
