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
