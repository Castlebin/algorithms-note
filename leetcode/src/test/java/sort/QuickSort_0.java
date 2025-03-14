package sort;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import common.NumUtil;

public class QuickSort_0 {

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
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
    public void test_0() {
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
