package leetcode.N1_N99;

import org.junit.Assert;
import org.junit.Test;

/**
 * 27. Remove Element
 * 27. 移除元素
 */
public class T27 {

    /**
     * 快慢指针先都指向位置 0
     * 如果 fast 遇到值为 val 的元素，则直接跳过，否则就赋值给 slow 指针，并让 slow 前进一步。
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int fast = 0, slow = 0;
        // fast 遇到值为 val 的元素，则直接跳过，否则，将值赋给 slow 位置
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        // 因为 slow 已经向前移动过了，所以，slow 的值，就是要返回的长度，不用加1
        return slow;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, removeElement(new int[] {3, 2, 2, 3}, 3));
        Assert.assertEquals(5, removeElement(new int[] {0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }

}
