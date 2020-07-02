package sword;

import org.junit.Test;

/**
 剑指 Offer 56 - I. 数组中数字出现的次数

 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
*/
public class S056 {
    public int[] singleNumbers(int[] nums) {
        // 第一步，求出所有元素异或的结果，即为这两个数异或之后的结果
        int xorResult = 0;
        for (int num : nums) {
            xorResult ^= num;
        }
        // 第二步，找出结果中二进制位为 1 的位，把所有数根据这一位是 0 还是 1 分为两组
        int pow = 1, bit = 0;
        while (bit <= 31) {
            if ((pow & xorResult) != 0) {
                break;
            }
            bit++;
            pow = pow << 1;
        }
        int a = 0, b = 0;
        for (int num : nums) {
            if ((pow & num) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    @Test
    public void test() {
    }

}
