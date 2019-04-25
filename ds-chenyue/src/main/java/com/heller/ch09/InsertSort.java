package com.heller.ch09;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.heller.ArrayUtil.generateNonnegativeArray;

public class InsertSort {

    public void sort(int[] array) {
        int swapCount = 0;
        for (int p = 1; p < array.length; p++) {
            // 新牌先拿到手里
            int tmp = array[p];
            // 与现有的排序好的牌进行比较，从右到左，给新牌找个合适的位置插进去
            int i = p;
            for (; i > 0 && array[i - 1] > tmp; i--) {
                // 将牌往后移，移出一个空位
                array[i] = array[i - 1];
                swapCount++;
            }
            array[i] = tmp;
        }

        System.out.println("InsertSort 经过交换次数 swapCount = " + swapCount);
    }

    @Test
    public void testSort() {
        InsertSort insertSort = new InsertSort();
        BubbleSort bubbleSort = new BubbleSort();
        for (int i = 0; i < 10; i++) {
            int[] array = generateNonnegativeArray(8, 10);
            int[] copy = Arrays.copyOfRange(array, 0, array.length);
            int[] copy2 = Arrays.copyOfRange(array, 0, array.length);
            System.out.println("origin: " + Arrays.toString(array));
            System.out.println("copy  : " + Arrays.toString(array));

            insertSort.sort(array);
            bubbleSort.sort(copy2);
            System.out.println("sorted: " + Arrays.toString(array));
            Arrays.sort(copy);

            Assert.assertArrayEquals("相等", array, copy);
            Assert.assertArrayEquals("相等", array, copy2);
        }
    }

}
