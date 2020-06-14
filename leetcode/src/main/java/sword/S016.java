package sword;

import org.junit.Test;

/**
 面试题16. 数值的整数次方
 实现函数double Power(double base, int exponent)，求base的exponent次方。
 不得使用库函数，同时不需要考虑大数问题。

 重点在于判断越界

 二分，递归其实也很快，内存占用并不多，比保存中间结果还划算

 （本题保存中间结果的算法，内存占用太大了）
 */
public class S016 {

    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        // 注意，n 有可能为负数
        // 注意，n 为最小负数时，直接取相反数，会溢出，所以，用long
        boolean negative = false;
        long calN = n;
        if (calN < 0) {
            negative = true;
            calN = -calN;
        }

        double result = myPow(x, (int)(calN / 2));
        if (calN % 2 == 0) {
            result = result * result;
        } else {
            result = result * result * x;
        }

        return negative? 1 / result : result;
    }

    @Test
    public void test() {
        System.out.println(myPow(2, -2));
    }

}
