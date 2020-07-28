package sword;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 剑指 Offer 61. 扑克牌中的顺子
 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。

 示例 1:

 输入: [1,2,3,4,5]
 输出: True


 示例 2:

 输入: [0,0,1,2,5]
 输出: True
*/
public class S061 {

    public boolean isStraight(int[] nums) {
        if (nums == null || nums.length != 5) {
            return false;
        }
        Arrays.sort(nums);
        int min = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != 0 && nums[i] == nums[i + 1]) {
                return false;
            }
            if (min == 0 && nums[i] != 0) {
                min = nums[i];
            }
        }
        return nums[nums.length - 1] - min <= 4;
    }

    @Test
    public void test() {
        Assert.assertEquals(false, isStraight(new int[]{13,13,9,12,10}));
        Assert.assertEquals(true, isStraight(new int[]{1,2,3,4,5}));
        Assert.assertEquals(true, isStraight(new int[]{0,0,1,2,5}));
    }

}
