package ds.ch10;

import static ds.ArrayUtil.generateNonNegativeArray;
import static ds.ArrayUtil.swap;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import ds.ch09.InsertSort;
import ds.ch09.QuickSort;

public class QuickSort_re {

    private int cutoff = 3;

    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left + cutoff < right) {
            int pivotIndex = partition(array, left, right);
            quickSort(array, left, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, right);
        } else {
            insertSort(array, left, right);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivotIndex = middleThreePivotIndex(array, left, right);
        int pivot = array[pivotIndex];
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
        swap(array, i, pivotIndex);
        // 主元和i位置互换，所以现在主元位置为i
        return i;
    }

    private int middleThreePivotIndex(int[] array, int left, int right) {
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
        // 将中间元素放到right-1位置 并返回该位置作为主元位置
        swap(array, center, right - 1);
        return right - 1;
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

    @Test
    public void test() {
        int[] array1 = new int[] {5, 2, 3, 1};
        sort(array1);
        Assert.assertArrayEquals(new int[] {1, 2, 3, 5}, array1);

        int[] array2 = new int[] {5, 1, 1, 2, 0, 0};
        sort(array2);
        Assert.assertArrayEquals(new int[] {0, 0, 1, 1, 2, 5}, array2);

        int[] array3 = new int[] {-4, 0, 7, 4, 9, -5, -1, 0, -7, -1};
        sort(array3);
        Assert.assertArrayEquals(new int[] {-7, -5, -4, -1, -1, 0, 0, 4, 7, 9}, array3);

        int[] array4 = new int[] {4, 981, 10, -17, 0, -20, 29, 50, 8, 43, -5};
        sort(array4);
        Assert.assertArrayEquals(new int[] {-20, -17, -5, 0, 4, 8, 10, 29, 43, 50, 981}, array4);
    }

    @Test
    public void testSort() {
        ds.ch09.QuickSort quickSort = new QuickSort();
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
