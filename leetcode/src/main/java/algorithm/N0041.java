package algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * 41. First Missing Positive
 */
public class N0041 {

    public static int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > 0) {

            }
        }
        return -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, firstMissingPositive(new int[]{1, 2, 0}));
        Assert.assertEquals(2, firstMissingPositive(new int[]{3, 4, -1, 1}));
        Assert.assertEquals(1, firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
    }

}
