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

    /**
     * 最好记的快速排序写法，一般来说，记下这一种就行了
     * partition 做了 4 件事情：
     * 1. 选取主元
     * 2. 将小于等于主元的元素放在左边，大于等于主元的元素放在右边
     * 3. 将主元放在正确的位置上
     * 4. 返回主元的位置
     */
    private int partition(int[] nums, int begin, int end) {
        // 简单的选取 begin 位置元素作为主元
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
            // 注意，交换时，一定要是 left < right ，因为我们要达到的效果就是，分区，左边的元素小于右边的
            // 所以，一定是得保证 left < right 才交换元素
            if (left < right) {
                swap(nums, left, right);
            }
        }
        // 结束之后，right 位置因为已经走了循环内的第二个循环，所以 nums[right] < pivot 了
        // 而 right 位置左边的元素都已经小于 pivot，它右边的元素都已经大于等于 pivot
        // 此时将 pivot 元素放到 right 位置
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
