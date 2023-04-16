package sort;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import common.NumUtil;

public class QuickSort_2 {

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

    public int partition(int[] nums, int begin, int end) {
        // 两个数的时候，就直接判断好了，用下面的流程容易出错，这样简单易懂！
        if (begin + 1 == end) {
            if (nums[begin] > nums[end]) {
                swap(nums, begin, end);
            }
            // 这里取 begin 或者 end 都行，取 end 理论上来说更快一些，因为切分可能会更好
            return end;
        }
        // 简单的直接取左边元素作为主元
        int pivotIndex = begin;
        int pivot = nums[pivotIndex];
        // 这里用的是 begin + 1 ，所以才需要上面的 对于两个数的判断！
        int left = begin + 1;
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
