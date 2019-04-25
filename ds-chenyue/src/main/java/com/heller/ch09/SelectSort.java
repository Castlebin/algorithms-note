package com.heller.ch09;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.heller.ArrayUtil.generateNonnegativeArray;
import static com.heller.ArrayUtil.swap;

public class SelectSort {

    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            // 找到从第i个到第N-1个元素中最小的元素，放到i的位置（跟原来i位置的元素交换，（避免了再申请一数组份空间））
            int minPosition = scanForMinPosition(array, i, array.length - 1);
            swap(array, i, minPosition);
        }
    }

    private int scanForMinPosition(int[] array, int start, int end) {
        int minPosition = start;
        int min = array[minPosition];
        for (int i = start; i <= end; i++) {
            if (array[i] < min) {
                min = array[i];
                minPosition = i;
            }
        }
        return minPosition;
    }

    @Test
    public void testSort() {
        SelectSort selectSort = new SelectSort();
        for (int i = 0; i < 10; i++) {
            int[] array = generateNonnegativeArray(10, 1000);
            int[] copy = Arrays.copyOfRange(array, 0, array.length);
            System.out.println("origin: " + Arrays.toString(array));
            System.out.println("copy  : " + Arrays.toString(array));

            selectSort.sort(array);
            System.out.println("sorted: " + Arrays.toString(array));
            Arrays.sort(copy);

            Assert.assertArrayEquals("相等", array, copy);
        }
    }

}
