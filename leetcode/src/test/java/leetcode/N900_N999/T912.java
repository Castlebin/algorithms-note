package leetcode.N900_N999;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import common.NumUtil;

/**
 * 912. 排序数组
 * https://leetcode.cn/problems/sort-an-array/
 */
public class T912 {

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    void quickSort(int[] nums, int begin, int end) {
        if (begin >= end) { // 无需排序
            return;
        }

        //  检查是否已经有序，有序的话就不用排了 （提高一点点效率）
        if (checkOrdered(nums, begin, end)) {
            return;
        }

        int pivotIndex = partition(nums, begin, end);
        quickSort(nums, begin, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, end);
    }

    /**
     * 快速排序最重要的就是这个 partition 方法！！
     * partition 做了 4 件事情：
     * 1. 选取主元  （直接选取 begin 位置作为主元，或者选取主元后将主元的位置放在 begin 位置）
     * 2. 将小于等于主元的元素放在左边，大于等于主元的元素放在右边
     * 3. 将主元放在正确的位置上
     * 4. 返回主元的位置
     */
    int partition(int[] nums, int begin, int end) {
        int left = begin; // !! 是 begin
        int right = end;
        int pivot = nums[begin]; // 简单的做法，直接取 begin 位置作为主元
        while (left < right) {
            while(left < end && nums[left] <= pivot) { // left < end !!  不超出要进行排序对的范围         （不使用 left < right）
                left++;
            }
            while (right > begin && nums[right] >= pivot) { // right > begin !! 不超出要进行排序对的范围        （不使用 left < right）
                right--;
            }
            if (left < right) { // 交换的前提，一定是 **左边**的元素 大、 **右边**的元素 小  （所以必须要 left < right）
                swap(nums, left, right);
            }
        }
        swap(nums, begin, right);
        return right;
    }

    void swap(int[] nums, int from, int to) {
        int temp = nums[from];
        nums[from] = nums[to];
        nums[to] = temp;
    }

    boolean checkOrdered(int[] nums, int begin, int end) {
        for (int index = begin + 1; index <= end; index++) {
            if (nums[index] < nums[index - 1]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        // 1 case
        checkSort(new int[] {5, 1, 1, 2, 0, 0});

        // many cases
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
        sortArray(nums);
        System.out.println("sorted nums: " + Arrays.toString(nums));
        Arrays.sort(copy);
        Assert.assertArrayEquals(copy, nums);
    }

}
