package ds.ch09;

import static ds.ArrayUtil.generateNonNegativeArray;
import static ds.ArrayUtil.swap;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class BubbleSort {

    public void sort(int[] array) {
        int swap = 0;
        int swapCount = 0;
        for (int p = array.length - 1; p > 0; p--) {
            boolean sorted = true;
            for (int i = 0; i < p; i++) {
                if (array[i] > array[i + 1]) {
                    sorted = false;
                    swap(array, i, i + 1);

                    swap += 3;
                    swapCount += 1;
                }
            }
            // 如果某一轮比较，发现没有元素需要交换，说明数组已经是排序好了的，无需再循环
            if (sorted) {
                break;
            }
        }

        System.out.println("BubbleSort 经过交换次数 swapCount = " + swapCount + ", swap = " + swap);
    }

    @Test
    public void testSort() {
        BubbleSort bubbleSort = new BubbleSort();
        for (int i = 0; i < 10; i++) {
            int[] array = generateNonNegativeArray(8, 10);
            int[] copy = Arrays.copyOfRange(array, 0, array.length);
            System.out.println("origin: " + Arrays.toString(array));
            System.out.println("copy  : " + Arrays.toString(array));

            bubbleSort.sort(array);
            System.out.println("sorted: " + Arrays.toString(array));
            Arrays.sort(copy);

            Assert.assertArrayEquals(array, copy);
        }
    }

}
