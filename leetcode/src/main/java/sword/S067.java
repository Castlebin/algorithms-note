package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 67. 把字符串转换成整数
 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。

 边界处理有点麻烦了  流程分析清楚了还好
 */
public class S067 {

    public int strToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        // 找到第一个非空字符位置
        int start = findFirstNonBlank(str);
        if (start != -1) {
            char startChar = str.charAt(start);
            if (startChar == '+') {
                if (start < str.length() - 1) {
                    char nextChar = str.charAt(start + 1);
                    if (nextChar >= '0' && nextChar <= '9') {
                        long num = toNum(str, start + 1, false);
                        return num >= Integer.MAX_VALUE? Integer.MAX_VALUE : (int)num;
                    }
                }
            } else if (startChar == '-') {
                if (start < str.length() - 1) {
                    char nextChar = str.charAt(start + 1);
                    if (nextChar >= '0' && nextChar <= '9') {
                        long num = toNum(str, start + 1, true);
                        return num <= Integer.MIN_VALUE? Integer.MIN_VALUE : (int)num;
                    }
                }
            } else if (startChar < '0' || startChar > '9') {
                return 0;
            } else {
                // 数字开头
                long num = toNum(str, start, false);
                return num >= Integer.MAX_VALUE? Integer.MAX_VALUE : (int)num;
            }
        }
        return 0;
    }

    /**
     * 边界处理有点烦
     */
    private long toNum(String str, int start, boolean neg) {
        char startChar = str.charAt(start);
        long num = startChar - '0';
        if (neg) {
            num = -num;
        }
        for (int i = start + 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c > '9' || c < '0') {
                break;
            }
            if (!neg) {
                num = (num * 10) + (c - '0');
                if (num >= Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
            } else {
                num = (num * 10) - (c - '0');
                if (num <= Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            }
        }

        return num;
    }

    private int findFirstNonBlank(String str) {
        for (int index = 0; index < str.length(); index++) {
            if (str.charAt(index) != ' ') {
                return index;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(42, strToInt("42"));
        Assert.assertEquals(-42, strToInt("      -42"));
        Assert.assertEquals(4193, strToInt("  4193 with words"));
        Assert.assertEquals(0, strToInt("words and 987"));
        Assert.assertEquals(-2147483648, strToInt("-91283472332"));
    }

}
