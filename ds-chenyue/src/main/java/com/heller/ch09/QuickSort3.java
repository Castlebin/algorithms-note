package com.heller.ch09;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.heller.ArrayUtil.generateNonnegativeArray;
import static com.heller.ArrayUtil.swap;

public class QuickSort3 {

    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(array, left, right);
            quickSort(array, left, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, right);
        }
    }

    private int partition(int[] array, int left, int right) {
        // 设定基准值（pivot）, 取左边第一个作为pivot （实际上表现很差的选择）
        int pivotIndex = left;
        int pivot = array[pivotIndex];
        int i = pivotIndex + 1;
        for (int j = i; j <= right; j++) {
            if (array[j] < pivot) {
                if (j != i) {
                    swap(array, j, i);
                }
                i++;
            }
        }
        swap(array, pivotIndex, i - 1);
        pivotIndex = i - 1;
        return pivotIndex;
    }

    @Test
    public void testSort() {
        QuickSort3 quickSort = new QuickSort3();
        InsertSort insertSort = new InsertSort();
        for (int i = 0; i < 10; i++) {
            int[] array = generateNonnegativeArray(11, 30);
            int[] copy = Arrays.copyOfRange(array, 0, array.length);
            int[] copy2 = Arrays.copyOfRange(array, 0, array.length);
            System.out.println("origin: " + Arrays.toString(array));
            System.out.println("copy  : " + Arrays.toString(array));

            quickSort.sort(array);
            insertSort.sort(copy2);
            System.out.println("sorted: " + Arrays.toString(array));
            Arrays.sort(copy);

            Assert.assertArrayEquals("相等", array, copy);
            Assert.assertArrayEquals("相等", array, copy2);
        }
    }

}
