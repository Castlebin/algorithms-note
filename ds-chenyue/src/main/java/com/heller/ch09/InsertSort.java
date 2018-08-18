package com.heller.ch09;

import org.junit.Test;

import java.util.Arrays;

import static com.heller.ch01.ArrayUtil.generateArray;

public class InsertSort {

    /**
     * 插入排序：（稳定的排序算法）
     */
    public static void insertSort(int[] array) {
        for (int j = 1; j < array.length; j++) {
            int p = array[j];
            int i = j;
            for (; i > 0 && array[i - 1] > p; i--) {  // a[i-1] > p
                array[i] = array[i - 1];
            }
            array[i] = p;
        }
    }

    @Test
    public void testInsertSort() {
        int[] array = generateArray(10, 8);
        int[] copy = Arrays.copyOf(array, array.length);
        System.out.println("origin: " + Arrays.toString(array));
        System.out.println("copy: " + Arrays.toString(copy));

        Arrays.sort(copy);
        System.out.println(Arrays.toString(copy));

        insertSort(array);
        System.out.println(Arrays.toString(array));
    }

}
