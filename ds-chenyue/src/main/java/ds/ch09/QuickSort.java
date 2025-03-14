package ds.ch09;

import static ds.ArrayUtil.generateNonNegativeArray;
import static ds.ArrayUtil.swap;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class QuickSort {

    private int cutoff = 3;

    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left + cutoff <= right) {
            int pivot = middleThree(array, left, right);
            int i = left;
            int j = right - 1;
            for (; ; ) {
                while (array[++i] < pivot) {}
                while (array[--j] > pivot) {}
                if (i < j) {
                    swap(array, i, j);
                } else {
                    break;
                }
            }
            swap(array, i, right - 1);
            quickSort(array, left, i - 1);
            quickSort(array, i + 1, right);
        } else {
            insertSort(array, left, right);
        }
    }

    private void insertSort(int[] array, int left, int right) {
        for (int p = left + 1; p <= right; p++) {
            int tmp = array[p];
            int i = p;
            for (; i > left && array[i - 1] > tmp; i--) {
                array[i] = array[i - 1];
            }
            array[i] = tmp;
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
        QuickSort quickSort = new QuickSort();
        InsertSort insertSort = new InsertSort();
        for (int i = 0; i < 10; i++) {
            int[] array = generateNonNegativeArray(1000, 1000000);
            int[] copy = Arrays.copyOfRange(array, 0, array.length);
            int[] copy2 = Arrays.copyOfRange(array, 0, array.length);
            System.out.println("origin: " + Arrays.toString(array));
            System.out.println("copy  : " + Arrays.toString(array));

            quickSort.sort(array);
            insertSort.sort(copy2);
            System.out.println("sorted: " + Arrays.toString(array));
            Arrays.sort(copy);

            Assert.assertArrayEquals(array, copy);
            Assert.assertArrayEquals(array, copy2);
        }
    }

}
