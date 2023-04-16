package sort;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import common.NumUtil;

/**
 * 快速排序（随机取主元）
 * 简单的在 QuickSort_1 上，随机选择一个元素作为主元，跟左边元素交换一下（这样转化为以左边元素作为主元的了！巧妙!）
 */
public class QuickSort_RandomPivot {

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
     * 简单的在 QuickSort_1 上，随机选择一个元素作为主元，跟左边元素交换一下（这样转化为以左边元素作为主元的了！巧妙!）
     * 其他完全一致
     */
    private int partition(int[] nums, int begin, int end) {
        // 随机选取一个作为主元，和最左边元素交换，这样，就也是取最左边元素作为主元的算法了，没其他改变！妙！
        // 其他的选取主元的方式，都可以这样改写
        int pivotIndex = choosePivotIndex(begin, end);
        swap(nums, begin, pivotIndex);

        // 简单的选取 begin 位置元素作为主元 (全都没有变，看见没！)
        int pivot = nums[begin];
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
        swap(nums, begin, right);
        return right;
    }

    /**
     * 随机选取主元
     */
    public int choosePivotIndex(int begin, int end) {
        int num = (int) (Math.random() * (end - begin + 1) + begin);
        return num;
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
