package algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

/**
 * 大数字运算
 */
public class BigNumUtil {

    /**
     * 大数字 做 加法 （十进制，字符串中的每一个字符都是 ['0'-'9'] 的字符）
     * 因为数字可能超长，只能用字符串模拟
     * <p>
     * 字符串 0 位，存放的是数字的最高位
     * 比如： "123" 代表 整数 123
     *
     * 加法器，美中不足，暂时只支持 **非负整数** 的加法
     */
    public static String add(String num1, String num2) {
        if (num1 == null || num1.length() == 0
            || num2 == null || num2.length() == 0) {
            throw new IllegalArgumentException("输入非法");
        }
        int num1Len = num1.length();
        int num2Len = num2.length();
        int maxLen = Math.max(num1Len, num2Len);
        int minLen = Math.min(num1Len, num2Len);
        int resultLen = maxLen + 1;
        char carry = '0';
        char[] result = new char[resultLen];
        int pr = resultLen - 1;
        int p1 = num1Len - 1, p2 = num2Len - 1;
        int processedLen = 0;
        for (; processedLen < minLen; p1--, p2--, pr--, processedLen++) {
            char c1 = num1.charAt(p1);
            char c2 = num2.charAt(p2);
            if (c1 < '0' || c1 > '9' || c2 < '0' || c2 > '9') {
                throw new IllegalArgumentException("输入字符串中有非数字字符");
            }
            char added = (char)(c1 - '0' + c2 - '0' + carry - '0' + '0');
            if (added > '9') {
                added = (char)(added - '0' - 10 + '0');
                carry = '1';
            } else {
                carry = '0';
            }
            result[pr] = added;
        }
        // 可能还有一个字符串没处理完，另外，还可能有进位没处理完
        char[] leftChars = new char[0];
        if (p1 >= 0) {
            leftChars = Arrays.copyOf(num1.toCharArray(), maxLen - minLen);
        } else if (p2 >= 0) {
            leftChars = Arrays.copyOf(num2.toCharArray(), maxLen - minLen);
        }
        if (leftChars.length > 0) {
            for (int p = leftChars.length - 1; p >= 0; p--, pr--) {
                char c = leftChars[p];
                if (c < '0' || c > '9') {
                    throw new IllegalArgumentException("输入字符串中有非数字字符");
                }
                char added = (char)(c - '0' + carry - '0' + '0');
                if (added > '9') {
                    added = (char)(added - '0' - 10 + '0');
                    carry = '1';
                } else {
                    carry = '0';
                }
                result[pr] = added;
            }
        }
        result[0] = carry;
        int resultStart = 0;
        for (; resultStart < resultLen && result[resultStart] == '0'; resultStart++) {
        }
        if (resultStart == resultLen) {
            return "0";
        }
        String rs = new String(Arrays.copyOfRange(result, resultStart, result.length));
        return rs;
    }

    @Test
    public void test() {
        for (int i = 0; i < 200; i++) {
            Random r = new Random();
            String num1 = r.nextInt(1000000000) + "";
            String num2 = r.nextInt(1000000000) + "";
            String result = add(num1, num2);
            System.out.println(result);
            Assert.assertEquals(new BigDecimal(result), new BigDecimal(num1).add(new BigDecimal(num2)));
        }
    }



}
