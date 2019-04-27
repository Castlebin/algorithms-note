package com.heller.ch09;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.heller.ArrayUtil.generateNonnegativeArray;

public class QuickSort {

    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {

    }

    private int middleThree(int[] array, int left, int right) {
        int middle = (left + right) / 2;
        if (array[left] <= array[middle] && array[middle] <= array[right]) {
            return middle;
        } else if (array[middle] <= array[left] && array[left] <= array[right]) {
            return left;
        } else {
            return right;
        }
    }

    @Test
    public void testSort() {
        QuickSort quickSort = new QuickSort();
        InsertSort insertSort = new InsertSort();
        for (int i = 0; i < 10; i++) {
            int[] array = generateNonnegativeArray(100, 10000);
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
