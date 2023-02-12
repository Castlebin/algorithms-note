package sword.sa.d1_d5;

import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer II 002. 二进制加法
 * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
 * 输入为 非空 字符串且只包含数字 1 和 0
 * <p>
 * 提示：
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 */
public class T002 {

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int n = Math.max(a.length(), b.length());
        int carry = 0;
        for (int i = 0; i < n; i++) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0; // 从 char 转化为整数，需要减去 '0'
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;// 从 char 转化为整数，需要减去 '0'

            sb.append((char) (carry % 2 + '0')); // 从整数转化为 char ，需要加上 '0'
            carry /= 2;
        }
        if (carry > 0) {
            sb.append((char) (carry + '0')); // 从整数转化为 char ，需要加上 '0'
        }
        return sb.reverse().toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("101", addBinary("11", "10"));
        Assert.assertEquals("10101", addBinary("1010", "1011"));
        Assert.assertEquals("1000", addBinary("111", "1"));
    }

}
