package sword;

import org.junit.Test;

/**
 剑指 Offer 65. 不用加减乘除做加法

 写一个函数，求两个整数之和，
 要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
*/
public class S065 {

    public int add(int a, int b) {
        while(b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;
    }

    /**
     * 递归解法
     */
    public int add_1(int a, int b) {
        if (b == 0) {
            return a;
        }
        // 转换成非进位和 + 进位
        return add_1(a ^ b, (a & b) << 1);
    }

    @Test
    public void test() {

    }

}
