package sword.sa;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 39. 数组中出现次数超过一半的数字
 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

 你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 示例 1:
 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 输出: 2
 */
public class T039 {

    public int majorityElement(int[] nums) {
        int voteNum = nums[0], voteCount = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == voteNum) {
                voteCount++;
            } else {
                voteCount--;
                if (voteCount < 0) {
                    voteNum = nums[i];
                    voteCount = 1;
                }
            }
        }
        return voteNum;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }

}
