package ds.ch09;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static ds.ArrayUtil.generateNonNegativeArray;
import static ds.ArrayUtil.swap;

public class QuickSort2 {

    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left < right) {
            // 取左边第一个作为pivot （实际上表现很差的选择）
            int pivot = array[left];
            int i = left;
            int j = right;
            while (i != j) {
                while (i < j && array[j] > pivot) {
                    j--;
                }
                while (i < j && array[i] <= pivot) {
                    i++;
                }
                if (i < j) {
                    swap(array, i, j);
                }
            }
            swap(array, i, left);

            quickSort(array, left, i - 1);
            quickSort(array, i + 1, right);
        }
    }

    @Test
    public void testSort() {
        QuickSort2 quickSort = new QuickSort2();
        InsertSort insertSort = new InsertSort();
        for (int i = 0; i < 10; i++) {
            int[] array = generateNonNegativeArray(10, 1000);
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
