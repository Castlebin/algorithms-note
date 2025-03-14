package ds.sort;

import static ds.ArrayUtil.generateArray;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class QS {

    public void sort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }
        if (start + 1 == end) {
            if (nums[start] > nums[end]) {
                swap(nums, start, end);
            }
            return;
        }
        // 到了这里，至少有三个元素了
        int pivotIndex = partition(nums, start, end);
        if (pivotIndex > start) {
            quickSort(nums, start, pivotIndex - 1);
        }
        if (pivotIndex < end) {
            quickSort(nums, pivotIndex + 1, end);
        }
    }

    /**
     * partition 里，至少有三个元素
     */
    private int partition(int[] nums, int start, int end) {
        // 选取主元
        int index = choosePivotIndex(nums, start, end);
        // 将主元藏到右边
        swap(nums, index, end);

        int p1 = start, p2 = end - 1;
        int pivot = nums[end];
        while (p1 < p2) {
            // 注意 p1 不能越界
            while (p1 < end && nums[p1] < pivot) {
                p1++;
            }
            // 注意 p2 不能越界，并且 这里的大小判断是 大于等于
            while (p2 > start && nums[p2] >= pivot) {
                p2--;
            }
            if (p1 < p2) {
                swap(nums, p1, p2);
            }
        }
        swap(nums, p1, end);
        return p1;
    }

    /**
     * 使用简单的三数取中的方法，找出中元位置
     * 此方法无副作用，所以可以用其他无副作用的任意方法替换
     */
    private int choosePivotIndex(int[] nums, int start, int end) {
        int middle = start + (end - start) / 2;
        if (isMidIndex(nums, middle, start, end)) {
            return middle;
        }
        if (isMidIndex(nums, start, middle, end)) {
            return start;
        }
        return end;
    }

    private boolean isMidIndex(int[] nums, int guess, int p1, int p2) {
        return (nums[p1] <= nums[guess] && nums[guess] <= nums[p2])
                || (nums[p2] <= nums[guess] && nums[guess] <= nums[p1]);
    }

    private void swap(int[] nums, int from, int to) {
        if (from == to || nums[from] == nums[to]) {
            return;
        }
        int tmp = nums[from];
        nums[from] = nums[to];
        nums[to] = tmp;
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
        QS quickSort = new QS();
        for (int i = 0; i < 100; i++) {
            int n = new Random().nextInt(1000);
            int m = new Random().nextInt(10000);
            int[] array = generateArray(n, m);
            int[] copy = Arrays.copyOfRange(array, 0, array.length);
            int[] copy2 = Arrays.copyOfRange(array, 0, array.length);
            System.out.println("origin: " + Arrays.toString(array));

            quickSort.sort(copy);
            Arrays.sort(copy2);
            System.out.println("sorted: " + Arrays.toString(copy));

            Assert.assertArrayEquals("相等", copy, copy2);
        }
    }

}
