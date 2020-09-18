package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 15. 二进制中1的个数

 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。
 例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

 */
public class S015 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int numberOfBit1 = 0;

        // 注意：此处为判断 n != 0，而不是 n > 0，注意！
        while (n != 0) {
            numberOfBit1++;
            n = n & (n - 1);
        }

        return numberOfBit1;
    }

    // 判断一个数是否 2 的整数次方  （二进制表示中，只有一个 1 ）
    public boolean isPowOf2(int num) {
        return 1 == hammingWeight(num);
    }

    // 判断两个数字，二进制位 最少需要经过几次变换，才能变为相同的数字
    // 即 判断两个数字的二进制位 有 几位 不同
    // 即 判断 两个数字 异或 的结果，有几位为 1
    public int diffBit(int num1, int num2) {
        return hammingWeight(num1 ^ num2);
    }

    @Test
    public void test() {
        Assert.assertEquals(29, hammingWeight(0B11110011111111111111111111111101));

        Assert.assertTrue(isPowOf2(0B0000001000000000));
        Assert.assertFalse(isPowOf2(0B0000001000100000));

        Assert.assertEquals(2, diffBit(0B0000001000100100, 0B0010001000100000));
    }

}


/**
 相关题目：
 0. 一个整型数组 nums 里除 1 个数字之外，其他数字都出现了两次。请写程序找出这 1 个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

 1. 一个整型数组 nums 里除 2 个数字之外，其他数字都出现了两次。请写程序找出这 两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
    剑指 Offer 56 - I. 数组中数字出现的次数
    https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/

 3. 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了 三 次。请找出那个只出现一次的数字。
     剑指 Offer 56 - II. 数组中数字出现的次数 II
     https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/

 */