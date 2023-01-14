package sword.sa.d1_d5;

import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer II 001. 整数除法
 * 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%'。
 *
 * 注意：
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8以及truncate(-2.7335) = -2
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31, 2^31−1]。本题中，如果除法结果溢出，则返回 2^31−1
 *
 * 限制：
 * -2^31 <= a, b <= 2^31 - 1
 * b != 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/xoh6Oh
 */
public class T001 {

    public int divide(int a, int b) {
        // 唯一的溢出情况
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        // TODO

        return 0;
    }

    @Test
    public void testT001() {
        Assert.assertEquals(7, divide(15, 2));
        Assert.assertEquals(-2, divide(7, -3));
        Assert.assertEquals(0, divide(0, 1));
        Assert.assertEquals(1, divide(1, 1));
    }

}
