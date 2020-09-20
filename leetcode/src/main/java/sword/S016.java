package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 面试题16. 数值的整数次方
 实现函数double Power(double base, int exponent)，求base的exponent次方。

 不得使用库函数，同时 **不需要考虑大数问题**。

 重点在于判断越界, 注意，n 为最小负数时，直接取相反数，会溢出，所以，用long

 二分，递归其实也很快，内存占用并不多，比保存中间结果还划算
 （本题保存中间结果的算法，内存占用太大了）
 */
public class S016 {

    public double myPow(double base, int exponent) {
        if (exponent == 1) {
            return base;
        }
        if (base == 0) {
            if (exponent < 0) {
                throw new IllegalArgumentException("数学上无意义");
            }
            return 0;
        }
        if (exponent == 0) {
            return 1;
        }
        // 注意，n 为最小负数时，直接取相反数，会溢出，所以，用long
        // 直接用三元表达式，也会出错，必须先做类型向上转换
    //    long exp = exponent > 0? exponent : -exponent;
        long exp = exponent;
        if (exp < 0) {
            exp = -exp;
        }
        boolean nonNegative = exponent > 0;
        long halfOfExp = exp / 2;
        boolean isEven = exp % 2 == 0;
        double powOfHalfExp = myPow(base, (int)halfOfExp);
        double pow;
        if (isEven) {
            pow = powOfHalfExp * powOfHalfExp;
        } else {
            pow = powOfHalfExp * powOfHalfExp * base;
        }
        return nonNegative? pow : 1 / pow;
    }

    @Test
    public void test() {
        Assert.assertFalse(0.25 != myPow(2, -2));
        Assert.assertFalse(0 != myPow(2, -2147483648));
    }

}
