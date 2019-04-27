package com.heller.ch09;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.heller.ArrayUtil.generateNonnegativeArray;

public class MergeSort {

    /**
     * 归并排序需要额外的O(N)空间，所以一般不用于内排序，适合超大数据量的外排序
     * 需要申请额外的O(N)空间用于归并数据
     */
    public void sort(int[] array) {
        int n = array.length;
        int[] tmp = new int[n];
        mergeSort(array, tmp, 0, n - 1);
    }

    private void mergeSort(int[] array, int[] tmp, int leftBegin, int rightEnd) {
        if (leftBegin < rightEnd) {
            int center = (leftBegin + rightEnd) / 2;
            mergeSort(array, tmp, leftBegin, center);
            mergeSort(array, tmp, center + 1, rightEnd);

            // 核心，两个有序序列的归并
            merge(array, tmp, leftBegin, center + 1, rightEnd);
        }
    }

    private void merge(int[] array, int[] tmp, int leftBegin, int rightBegin, int rightEnd) {
        int leftEnd = rightBegin - 1;
        int i = leftBegin;
        int j = rightBegin;
        int p = leftBegin;
        while (i <= leftEnd && j <= rightEnd) {
            if (array[i] <= array[j]) {
                tmp[p++] = array[i++];
            } else {
                tmp[p++] = array[j++];
            }
        }

        // 如果左边数组部分还有剩余，直接复制剩余的到tmp数组中后面
        while (i <= leftEnd) {
            tmp[p++] = array[i++];
        }
        // 如果右边数组部分还有剩余，直接复制剩余的到tmp数组中后面
        while (j <= rightEnd) {
            tmp[p++] = array[j++];
        }

        for (int t = leftBegin; t <= rightEnd; t++) {
            array[t] = tmp[t];
        }
    }

    @Test
    public void testSort() {
        MergeSort mergeSort = new MergeSort();
        BubbleSort bubbleSort = new BubbleSort();
        for (int i = 0; i < 100; i++) {
            int[] array = generateNonnegativeArray(100, 1000);
            int[] copy = Arrays.copyOfRange(array, 0, array.length);
            int[] copy2 = Arrays.copyOfRange(array, 0, array.length);
            System.out.println("origin: " + Arrays.toString(array));
            System.out.println("copy  : " + Arrays.toString(array));

            mergeSort.sort(array);
            bubbleSort.sort(copy2);
            System.out.println("sorted: " + Arrays.toString(array));
            Arrays.sort(copy);

            Assert.assertArrayEquals("相等", array, copy);
            Assert.assertArrayEquals("相等", array, copy2);
        }
    }

}
