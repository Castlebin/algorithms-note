package leetcode.N200_N299;

import org.junit.Assert;
import org.junit.Test;


/**
 * 283. Move Zeroes
 * 283. 移动零
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作
 */
public class T283 {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        // 快慢指针，开始时都指向位置 0 ，接下来快指针寻找非 0 元素
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        // 处理完毕。需要将剩余的元素置为 0
        // 因为 slow 已经++ 过了，所以现在从 slow 开始（包括 slow）位置的所有元素值都应该置为 0
        while (slow < nums.length) {
            nums[slow++] = 0;
        }
    }

    @Test
    public void test() {
        int[] a1 = new int[] {0, 1, 0, 3, 12};
        moveZeroes(a1);
        Assert.assertArrayEquals(new int[] {1, 3, 12, 0, 0}, a1);

        int[] a2 = new int[] {0};
        moveZeroes(a2);
        Assert.assertArrayEquals(new int[] {0}, a2);
    }

}
