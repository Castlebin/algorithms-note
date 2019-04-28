package com.heller.ch09;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.heller.ArrayUtil.generateNonnegativeArray;
import static com.heller.ArrayUtil.swap;

public class QuickSort1 {

    private int cutoff = 3;

    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int pivot = middleThree(array, left, right);
            int i = left;
            int j = right - 1;
            while (true) {
                while (array[++i] < pivot) {
                }
                while (j > left && array[--j] > pivot) {
                }
                if (i < j) {
                    swap(array, i, j);
                } else {
                    break;
                }
            }
            if (i < right) {
                swap(array, i, right - 1);
            }
            quickSort(array, left, i - 1);
            quickSort(array, i + 1, right);
        }
    }

    private int middleThree(int[] array, int left, int right) {
        int center = (left + right) / 2;
        if (array[left] > array[center]) {
            swap(array, left, center);
        }
        if (array[left] > array[right]) {
            swap(array, left, right);
        }
        if (array[center] > array[right]) {
            swap(array, center, right);
        }
        swap(array, center, right - 1);
        return array[right - 1];
    }

    @Test
    public void testSort() {
        QuickSort1 quickSort = new QuickSort1();
        InsertSort insertSort = new InsertSort();
        for (int i = 0; i < 10; i++) {
            int[] array = generateNonnegativeArray(10, 1000);
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
