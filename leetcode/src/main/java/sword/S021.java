package sword;

import org.junit.Test;

/**
  面试题21. 调整数组顺序使奇数位于偶数前面

  输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 */
public class S021 {

    public int[] exchange(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        int begin = 0;
        int end = nums.length - 1;
        while (begin < end) {
            while (begin < nums.length && !matchRule(nums[begin])) {
                begin++;
            }
            while (end >= 0 && matchRule(nums[end])) {
                end--;
            }
            if (begin < end) {
                int tmp = nums[begin];
                nums[begin] = nums[end];
                nums[end] = tmp;
            }
        }

        return nums;
    }

    public boolean matchRule(int num) {
        return isEven(num);
    }

    public boolean isEven(int num) {
        return (num & 0x0001) == 0;
    }

    @Test
    public void test() {

    }

}
