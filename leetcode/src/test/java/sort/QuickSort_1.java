package sort;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import common.NumUtil;

// QuickSort_1  最好记
public class QuickSort_1 {

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
        if (begin < end) {
            int pivot = partition(nums, begin, end);
            quickSort(nums, begin, pivot - 1);
            quickSort(nums, pivot + 1, end);
        }
    }

    /**
     * 最好记的快速排序写法，一般来说，记下这一种就行了
     */
    private int partition(int[] nums, int begin, int end) {
        // 简单的选取 begin 位置元素作为主元
        int pivotIndex = begin;
        int pivot = nums[pivotIndex];
        // 注意！！这里直接用的 begin
        int left = begin;
        int right = end;
        while (left < right) {
            while (left < end && nums[left] <= pivot) {
                left++;
            }
            while (right > begin && nums[right] >= pivot) {
                right--;
            }
            if (left < right) {
                swap(nums, left, right);
            }
        }
        swap(nums, pivotIndex, right);
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
