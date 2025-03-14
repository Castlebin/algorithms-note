package ds.sort;

import org.junit.Assert;
import org.junit.Test;

/**
 * 基于三向切分的快速排序算法
 */
public class SimpleThreeWayQuickSort {

    public void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    void quickSort(int[] nums, int begin, int end) {
        if (end <= begin) {
            return;
        }

        int left = begin, index = begin + 1, right = end;
        int pivot = nums[begin];
        while (index <= right) {
            int cmp = nums[index] - pivot;
            if (cmp < 0) {
                swap(nums, left++, index++);
            } else if (cmp > 0) {
                swap(nums, index, right--);
            } else if (cmp == 0) {
                index++;
            }
        }

        quickSort(nums, begin, left - 1);
        quickSort(nums, right + 1, end);
    }

    public void swap(int[] nums, int p1, int p2) {
        if (p1 == p2 || nums[p1] == nums[p2]) {
            return;
        }
        int tmp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = tmp;
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

}
