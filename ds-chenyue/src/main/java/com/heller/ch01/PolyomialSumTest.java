package com.heller.ch01;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class PolyomialSumTest {

    @Test
    public void testPolyomialSum1() {
        double y = PolyomialSum.polyomialSum1(a, 1.00001f);
        System.out.println(y);
    }

    @Test
    public void testPolyomialSum11() {
        double y = PolyomialSum.polyomialSum1(aa, 1.00001f);
        System.out.println(y);
    }

    @Test
    public void testPolyomialSum111() {
        double y = PolyomialSum.polyomialSum1(aaa, 1.00001f);
        System.out.println(y);
    }

    @Test
    public void testPolyomialSum2() {
        double y = PolyomialSum.polyomialSum2(a, 1.00001f);
        System.out.println(y);
    }

    @Test
    public void testPolyomialSum22() {
        double y = PolyomialSum.polyomialSum2(aa, 1.00001f);
        System.out.println(y);
    }

    @Test
    public void testPolyomialSum222() {
        double y = PolyomialSum.polyomialSum2(aaa, 1.00001f);
        System.out.println(y);
    }

    private static int[] a;
    private static int[] aa;
    private static int[] aaa;

    @BeforeClass
    public static void setUp() throws Exception {
        a = PolyomialSum.buildFators(100);
        aa = PolyomialSum.buildFators(10000);
        aaa = PolyomialSum.buildFators(1000000);
    }

}
