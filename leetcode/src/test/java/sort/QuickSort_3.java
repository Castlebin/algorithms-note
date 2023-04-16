package sort;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import common.NumUtil;

public class QuickSort_3 {

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        // 快速扫描一遍是否已经有序(一个优化而已)
        boolean sorted = checkOrder(nums);
        if (sorted) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int pivotIndex = partition(nums, begin, end);
        quickSort(nums, begin, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, end);
    }

    private int partition(int[] nums, int begin, int end) {
        int pivot = nums[begin];
        int left = begin + 1, right = end;
        while (true) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (right >= left && nums[right] > pivot) {
                right--;
            }
            if (left >= right) {
                break;
            }
            swap(nums, left++, right--);
        }
        swap(nums, begin, right);
        return right;
    }

    public void swap(int[] nums, int p1, int p2) {
        if (p1 == p2 || nums[p1] == nums[p2]) {
            return;
        }
        int tmp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = tmp;
    }

    public boolean checkOrder(int[] nums) {
        for (int index = 0; index < nums.length - 1; index++) {
            if (nums[index] > nums[index + 1]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        checkSort(new int[] {5, 1, 1, 2, 0, 0});
        for (int count = 0; count < 10; count++) {
            int n = new Random().nextInt(20);
            int rangeL = 0;
            int rangeR = 100;
            int[] nums = NumUtil.generateRandomArray(n, rangeL, rangeR);

            checkSort(nums);
        }
    }

    private void checkSort(int[] nums) {
        System.out.println("nums : " + Arrays.toString(nums));
        int[] copy = Arrays.copyOf(nums, nums.length);
        sort(nums);
        System.out.println("sorted nums: " + Arrays.toString(nums));
        Arrays.sort(copy);
        Assert.assertArrayEquals(copy, nums);
    }

}
