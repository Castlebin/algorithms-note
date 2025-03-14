package ds.ch09;

import static ds.ArrayUtil.generateNonNegativeArray;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 归并排序，非递归算法
 */
public class MergeSort2 {

    /**
     * 归并排序需要额外的O(N)空间，所以一般不用于内排序，适合超大数据量的外排序
     * 需要申请额外的O(N)空间用于归并数据
     */
    public void sort(int[] array) {
        int n = array.length;
        int[] tmp = new int[n];
        int step = 1;
        while (step < n) {
            mergePass(array, tmp, step);
            step *= 2;

            // 保证最后一定将有序数据倒腾回了原始数组
            mergePass(tmp, array, step);
            step *= 2;
        }
    }

    private void mergePass(int[] array, int[] tmp, int step) {
        int n = array.length;
        int i;
        for (i = 0; i <= n - 2 * step; i += 2 * step) {
            merge1(array, tmp, i, i + step, i + 2 * step - 1);
        }
        if (i + step < n) {
            merge1(array, tmp, i, i + step, n - 1);
        } else {
            for (int j = i; j < n; j++) {
                tmp[j] = array[j];
            }
        }
    }

    private void merge1(int[] array, int[] tmp, int leftBegin, int rightBegin, int rightEnd) {
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

        // 去掉这一步将数据倒腾回原始数组的步骤，在外层最后做一次倒腾即可，避免反复多次来回倒腾
        /*
        for (int t = leftBegin; t <= rightEnd; t++) {
            array[t] = tmp[t];
        }*/
    }

    @Test
    public void testSort() {
        MergeSort2 mergeSort = new MergeSort2();
        BubbleSort bubbleSort = new BubbleSort();
        for (int i = 0; i < 100; i++) {
            int[] array = generateNonNegativeArray(10, 10);
            int[] copy = Arrays.copyOfRange(array, 0, array.length);
            int[] copy2 = Arrays.copyOfRange(array, 0, array.length);
            System.out.println("origin: " + Arrays.toString(array));
            System.out.println("copy  : " + Arrays.toString(array));

            mergeSort.sort(array);
            bubbleSort.sort(copy2);
            System.out.println("      sorted: " + Arrays.toString(array));
            Arrays.sort(copy);
            System.out.println("copy  sorted: " + Arrays.toString(copy));
            System.out.println("copy2 sorted: " + Arrays.toString(copy2));

            Assert.assertArrayEquals(array, copy);
            Assert.assertArrayEquals(array, copy2);
        }
    }

}
