package com.heller.ch01;

import org.junit.Test;

import java.util.Arrays;

import static cn.hutool.core.util.ArrayUtil.swap;
import static com.heller.ch01.ArrayUtil.generateArray;

public class BubbleSort {

    /**
     * 冒泡排序：
     * n轮循环，每轮循环中，比较两个相邻元素，如果a[i] > a[i+1]，则交换两个元素
     * 每一轮排序之后，从第n-i到n-1个元素都是有序的（大的那部分元素）
     */
    public static void bubbleSort(int[] array) {
        for (int j = 0; j < array.length; j++) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] > array[j]) {
                    swap(array, i,j);
                }
            }
        }
    }

    @Test
    public void testBubbleSort() {
        int[] array = generateArray(10, 8);
        int [] copy = Arrays.copyOf(array, array.length);
        System.out.println("origin: " + Arrays.toString(array));
        System.out.println("copy: " + Arrays.toString(copy));

        Arrays.sort(copy);
        System.out.println(Arrays.toString(copy));

        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

}
