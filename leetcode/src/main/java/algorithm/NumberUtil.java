package algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

public class NumberUtil {

    /**
     * 大数字加法（用字符串来表示数字）
     * 如 "12345" 表示 12345
     *
     * 由于算法原因，当前只支持两个非负整数的加法，而且只支持纯数字
     */
    public String addBigNum(String num1, String num2) {
        if (num1 == null || num1.length() == 0
            || num2 == null || num2.length() == 0) {
            throw new IllegalArgumentException("num must not empty!");
        }
        int lenOfNum1 = num1.length();
        int lenOfNum2 = num2.length();
        int maxLen = Math.max(lenOfNum1, lenOfNum2);
        int resultLen = maxLen + 1;
        char[] result = new char[resultLen];
        char[] charsOfNum1 = num1.toCharArray();
        char[] charsOfNum2 = num2.toCharArray();
        // 进位
        int carry = 0;
        int p1 = lenOfNum1 - 1;
        int p2 = lenOfNum2 - 1;
        int index = resultLen - 1;
        for (; p1 >= 0 && p2 >= 0; p1--, p2--, index--) {
            if(charsOfNum1[p1] < '0' || charsOfNum1[p1] > '9'
                || charsOfNum2[p2] < '0' || charsOfNum2[p2] > '9') {
                throw new IllegalArgumentException("包含非数字的字符");
            }
            int added = charsOfNum1[p1] - '0' + charsOfNum2[p2] - '0' + carry;
            if(added > 9) {
                added = added - 10;
                carry = 1;
            } else {
                carry = 0;
            }
            result[index] = (char)(added + '0');
        }
        // 现在只剩下进位 和 可能还有一个字符串没处理完了
        char[] leftChars = new char[0];
        if (p1 < 0) {
            leftChars = Arrays.copyOf(charsOfNum2, p2 + 1);
        } else {
            leftChars = Arrays.copyOf(charsOfNum1, p1 + 1);
        }
        if (leftChars.length > 0) {
            for (int p = leftChars.length - 1; p >= 0; p--, index--) {
                if (leftChars[p] < '0' || leftChars[p] > '9') {
                    throw new IllegalArgumentException("包含非数字字符");
                }
                int added = leftChars[p] - '0' + carry;
                if (added > 9) {
                    added = added - 10;
                    carry = 1;
                } else {
                    carry = 0;
                }
                result[index] = (char)(added + '0');
            }
        }
        // 如果还有进位
        result[index] = (char)(carry + '0');
        // 去掉开头的0
        for(int p = 0; p < resultLen; p++) {
            if (result[p] != '0') {
                return new String(Arrays.copyOfRange(result, p, resultLen));
            }
        }
        return "0";
    }

    /**
     * 不用加减乘除做加法
     */
    public long add(int a, int b) {
        while (b != 0) {
            // 不带进位的加法，异或
            int tmp = a ^ b;
            // 进位
            b = (a & b) << 1;
            a = tmp;
        }
        return a;
    }

    @Test
    public void testAddBigNum() {
        Assert.assertEquals("10000000086", addBigNum("0010000000085", "00001"));
        for (int i = 0; i < 200; i++) {
            Random r = new Random();
            String num1 = r.nextInt(1000000000) + "";
            String num2 = r.nextInt(1000000000) + "";
            String result = addBigNum(num1, num2);
            Assert.assertEquals(new BigDecimal(result), new BigDecimal(num1).add(new BigDecimal(num2)));
        }
    }

    @Test
    public void testAdd() {
        int a = 10, b = 20;
        Assert.assertEquals(30, add(a, b));
        a = -10; b = 20;
        Assert.assertEquals(10, add(a, b));
        a = 10; b = -20;
        Assert.assertEquals(-10, add(a, b));
    }

}
