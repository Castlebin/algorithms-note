package com.heller;

import java.util.Random;

public class ArrayUtil {

    /**
     * 生成一个长度为 n 的，每个元素大小在[-range, range) 之间的数组
     */
    public static int[] generateArray(int n, int range) {
        int[] array = new int[n];
        Random random = new Random();
        int t = 2 * range;
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(t) - range;
        }
        return array;
    }

    /**
     * 生成一个长度为 n 的，每个元素大小在[0, range) 之间的数组
     */
    public static int[] generateNonnegativeArray(int n, int range) {
        int[] array = new int[n];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(range);
        }
        return array;
    }

}
