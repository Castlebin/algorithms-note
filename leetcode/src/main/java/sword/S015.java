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

    @Test
    public void test() {
        Assert.assertEquals(31, hammingWeight(0b11111111111111111111111111111101));
    }

}
