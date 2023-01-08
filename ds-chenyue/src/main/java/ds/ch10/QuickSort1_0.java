package ds.ch10;

import ds.ch09.InsertSort;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static ds.ArrayUtil.generateNonNegativeArray;
import static ds.ArrayUtil.swap;

public class QuickSort1_0 {

    private int cutoff = 3;

    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        if (left + 1 == right && array[left] > array[right]) {
            swap(array, left, right);
            return;
        }
        int pivot = middleThree(array, left, right);
        if (right - left > 2) {
            int i = left;
            int j = right - 1;
            while (true) {
                while (array[++i] < pivot) {
                }
                while (array[--j] > pivot) {
                }
                if (i < j) {
                    swap(array, i, j);
                } else {
                    break;
                }
            }
            if (i < right - 1) {
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
        QuickSort1_0 quickSort = new QuickSort1_0();
        InsertSort insertSort = new InsertSort();
        for (int i = 0; i < 101; i++) {
            int[] array = generateNonNegativeArray(103, 100000);
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
