package ds.ch10;

import static ds.ArrayUtil.generateNonNegativeArray;
import static ds.ArrayUtil.swap;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import ds.ch09.InsertSort;

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
