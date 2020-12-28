package ds.ch12;

import org.junit.Test;

import java.util.Arrays;

/**
 * KMP 算法实现字符串匹配
 */
public class KMP {

    /**
     * 如果 pattern 模式字符串是 str 目标字符串的子串，返回它在 str 中的出现的第一个位置
     * 否则返回 -1
     *
     * @param str     目标串
     * @param pattern 模式串
     * @return
     */
    public static int kmp(String str, String pattern) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("pattern 串长度必须大于 0 !");
        }
        if (str == null || str.length() == 0) {
            return -1;
        }
        int n = str.length();
        int m = pattern.length();
        // 目标串 比 模式串 长度还短，肯定不能匹配
        if (n < m) {
            return -1;
        }

        int[] match = buildMatch(pattern);
        // 定义 p 为 指向 pattern 模式串中字符的指针，s 为 指向 目标串 str 中字符的指针
        int s = 0, p = 0;
        while (s < n && p < m) {
            if (str.charAt(s) == pattern.charAt(p)) {
                s++;
                p++;
            } else {
                if (p > 0) {
                    p = match[p - 1] + 1;
                } else {
                    s++;
                }
            }
        }
        // 当 模式串 匹配完成，说明找到了一个子串了
        return p == m? s - m : -1;
    }

    /**
     * 关于 pattern 字符串的模式数据
     * **这里是算法最精巧的部分**
     *
     * @param pattern 模式串
     * @return
     */
    private static int[] buildMatch(String pattern) {
        int m = pattern.length();
        int[] match = new int[m];
        match[0] = -1;
        int i, j;
        for (j = 1; j < m; j++) {
            i = match[j - 1];
            while (i >= 0 && pattern.charAt(i + 1) != pattern.charAt(j)) {
                i = match[i];
            }
            if (pattern.charAt(i + 1) == pattern.charAt(j)) {
                match[j] = i + 1;
            } else {
                match[j] = -1;
            }
        }

        return match;
    }

    /**
     * O(N^3) 的算法
     *
     * @param pattern
     * @return
     */
    private static int[] buildMatchSlow(String pattern) {
        int m = pattern.length();
        int[] match = new int[m];
        for (int j = 0; j < m; j++) {
            match[j] = -1;
            for (int i = 0; i < j; i++) {
                // 两个子串比较
                int p1 = 0, p2 = j - i;
                while (p1 <= i && pattern.charAt(p1) == pattern.charAt(p2)) {
                    p1++;
                    p2++;
                }
                if (p1 > i) {
                    match[j] = i;
                }
            }
        }
        return match;
    }

    @Test
    public void testBuildMatch() {
        String pattern = "abcabcacab";
        System.out.println(Arrays.toString(buildMatchSlow(pattern)));
        System.out.println(Arrays.toString(buildMatch(pattern)));

        String str = "This is a simple example.";
        String pattern1 = "simple";
        System.out.println(kmp(str, pattern1));
        System.out.println(str.indexOf(pattern1));
        String pattern2 = "sample";
        System.out.println(kmp(str, pattern2));
    }

}
