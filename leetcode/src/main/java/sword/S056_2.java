package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 56 - II. 数组中数字出现的次数 II

 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 */
public class S056_2 {
    public int singleNumber(int[] nums) {
        int[] count = new int[32];
        int n = 1;
        for (int i = 0; i < count.length - 1; i++) {
            for (int num : nums) {
                int t = num & n;
                if (t != 0) {
                    count[i]++;
                }
            }
            n = n << 1;
        }
        int num = 0;
        for (int i = 0; i < count.length - 1; i++) {
            int mod = count[i] % 3;
            if (mod == 1) {
                num = num + (1 << i);
            }
        }
        return num;
    }

    @Test
    public void test() {
        Assert.assertEquals(4, singleNumber(new int[]{3,4,3,3}));
        Assert.assertEquals(1, singleNumber(new int[]{9,1,7,9,7,9,7}));
    }

}
