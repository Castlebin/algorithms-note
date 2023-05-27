package sword.sa;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 65. 不用加减乘除做加法
 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。

 示例:
 输入: a = 1, b = 1
 输出: 2

 提示：
 a, b 均可能是负数或 0
 结果不会溢出 32 位整数
 */
public class T065 {

    public int add(int a, int b) {
        if (b == 0) {
            return a;
        }
        // 转换成非进位和 + 进位
        return add(a ^ b, (a & b) << 1);
    }

    @Test
    public void test() {
        Assert.assertEquals(2, add(1, 1));
        Assert.assertEquals(20, add(7, 13));
    }

}
