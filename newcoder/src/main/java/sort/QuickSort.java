package sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class QuickSort {

    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int begin, int end) {
        int pivotIndex = partition(arr, begin, end);
        if (pivotIndex > begin) {
            quickSort(arr, begin, pivotIndex - 1);
        }
        if (pivotIndex < end) {
            quickSort(arr, pivotIndex + 1, end);
        }
    }

    private int partition(int[] arr, int begin, int end) {
        if (begin == end) {
            return begin;
        }
        // 取中元
        // 简单的取开始的元素作为中元
        int pivotIndex = begin;
        int pivot = arr[pivotIndex];
        // 定义左右两个指针
        int left = begin, right = end;
        while (left < right) {
            /** 此算法，必须先从右边开始搜索!!! */
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            // 可以交换元素了
            if (left < right) {
                swap(arr, left, right);
            }
        }
        swap(arr, right, pivotIndex);
        return right;
    }

    private void swap(int[] arr, int p1, int p2) {
        if (p1 != p2 && arr[p1] != arr[p2]) {
            int temp = arr[p1];
            arr[p1] = arr[p2];
            arr[p2] = temp;
        }
    }

    @Test
    public void testQuickSort() {
        int[] array = new int[]{7, 2, -3, -1, 8, 1};
        int[] copy = new int[]{7, 2, -3, -1, 8, 1};

        array = ArrayUtil.generateArray(100, 1000);
        copy = Arrays.copyOf(array, array.length);

        Arrays.sort(copy);
        sort(array);
        Assert.assertArrayEquals(array, copy);
    }

}
