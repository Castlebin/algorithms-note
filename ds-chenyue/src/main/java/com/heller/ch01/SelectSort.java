package com.heller.ch01;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 选择排序
 *
 * 每次循环，从 i到 n 元素 之间，找出最小元素，通过swap放到i的位置，所以每次循环，前i个元素是排好序的
 */
public class SelectSort {

    public int[] selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minCur = i;
            // 每次循环，从 i到 n 元素 之间，找出最小元素，通过swap放到i的位置，所以每次循环，前i个元素是排好序的
            for (int cur = i + 1; cur < array.length; cur++) {
                if (array[cur] < array[minCur]) {
                    minCur = cur;
                }
            }
            swap(array, i, minCur);
            System.out.println("step " + (i + 1) + ": " + Arrays.toString(array));
        }
        return array;
    }

    @Test
    public void testSelectSort() {
        System.out.println(Arrays.toString(selectSort(array)));
        System.out.println(Arrays.toString(copy));
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static int[] array = new int[10];
    private static int[] copy;

    @Test
    public void beforClass() {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }
        System.out.println("origin: " + Arrays.toString(array) + ", " + array);
        copy = Arrays.copyOf(array, array.length);
        System.out.println("copy: " + Arrays.toString(copy) + ", " + copy);
        Arrays.sort(copy);
        System.out.println("sorted copy: " + Arrays.toString(copy) + ", " + copy);
    }

}
