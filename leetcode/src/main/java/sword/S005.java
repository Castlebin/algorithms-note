package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 * 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * <p>
 * 原题 要求 在原来字符串上进行原地替换，对于Java等语言不可能，
 * 所以，算法内部用char[]代替
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 10000
 */
public class S005 {
    private static final int MAX_LENGTH = 10000;

    public String replaceSpace(String s) {
        // 边界条件判断
        if (s == null || s.length() == 0) {
            return s;
        }

        // 用来做原地替换的字符串
        char[] chars = new char[MAX_LENGTH];
        int length = s.length();
        s.getChars(0, length, chars, 0);

        // 1. 遍历字符串，找出有多少个空格
        int spaceCount = 0;
        for (int i = 0; i < length; i++) {
            if (chars[i] == ' ') {
                spaceCount++;
            }
        }
        // 如果没有空格，直接返回
        if (spaceCount == 0) {
            return s;
        }

        // 计算出替换之后的字符串长度应该是多少(每个空格替换成"%20"，长度就多了2)
        // 从末尾开始替换
        int newLength = length + 2 * spaceCount;
        for (int po = length - 1, pn = newLength - 1; po >= 0; po--) {
            if (chars[po] != ' ') {
                chars[pn--] = chars[po];
            } else {
                pn = pn - 3;
                chars[pn + 1] = '%';
                chars[pn + 2] = '2';
                chars[pn + 3] = '0';
            }
        }

        return new String(chars, 0, newLength);
    }

    @Test
    public void test() {
        String s = "We are happy.";
        Assert.assertEquals(s.replaceAll(" ", "%20"), replaceSpace(s));
    }

}
