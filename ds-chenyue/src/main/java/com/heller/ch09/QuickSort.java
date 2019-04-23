package com.heller.ch09;

import org.junit.Test;

import java.util.Arrays;

import static com.heller.ArrayUtil.generateArray;

public class QuickSort {

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int startIndex, int endIndex) {
        // 开始元素位置大于等于结束位置元素位置，不用干啥
        if (startIndex >= endIndex) {
            return;
        }

        int pivotIndex = partition(array, startIndex, endIndex);

        quickSort(array, startIndex, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, endIndex);
    }

    private static int partition(int[] array, int startIndex, int endIndex) {
        int pivot = array[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            while (left < right && array[right] > pivot) {
                right--;
            }
            while (left < right && array[left] <= pivot) {
                left++;
            }
            if (left < right) {
                swap(array, left, right);
            }
        }
        //pivot和指针重合点交换
        swap(array, startIndex, left);
        return left;
    }

    private static void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    @Test
    public void testQuickSort() {
        int[] array = new int[]{4, 7, 6, 5, 3, 2, 8, 1};
        sort(array);
        System.out.println(Arrays.toString(array));

        for (int i = 0; i < 10; i++) {
            array = generateArray(10, 8);
            int[] copy = Arrays.copyOf(array, array.length);
            System.out.println("origin: " + Arrays.toString(array));
            System.out.println("copy: " + Arrays.toString(copy));

            Arrays.sort(copy);
            System.out.println(Arrays.toString(copy));

            sort(array);
            System.out.println(Arrays.toString(array));
        }
    }

}
