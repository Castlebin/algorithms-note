package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 19. 正则表达式匹配

 请实现一个函数用来匹配包含'. '和'*'的正则表达式。
 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 在本题中，匹配是指字符串的所有字符匹配整个模式。
 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 */
public class S019 {

    /**
     * 动态规划，非常快
     */
    class Solution {
        public boolean isMatch(String str, String pattern) {
            if (str == null || pattern == null) {
                return false;
            }
            int n = str.length();
            int m = pattern.length();
            boolean[][] matched = new boolean[n + 1][m + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    // 分为空正则和非空正则两种情况
                    if (j == 0) {
                        matched[i][j] = (i == 0);
                    } else {
                        //非空正则分为两种情况 * 和 非*
                        if (pattern.charAt(j - 1) != '*') {
                            if (i > 0 && (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.')) {
                                matched[i][j] = matched[i - 1][j - 1];
                            }
                        } else {
                            //碰到 * 了，分为看和不看两种情况
                            //不看
                            if (j >= 2) {
                                matched[i][j] |= matched[i][j - 2];
                            }
                            //看
                            if (i >= 1 && j >= 2 && (str.charAt(i - 1) == pattern.charAt(j - 2) || pattern.charAt(j - 2) == '.')) {
                                matched[i][j] |= matched[i - 1][j];
                            }
                        }
                    }
                }
            }
            return matched[n][m];
        }
    }

    class Solution2 {
        public boolean isMatch(String A, String B) {
            // 如果字符串长度为0，需要检测下正则串
            if (A.length() == 0) {
                // 如果正则串长度为奇数，必定不匹配，比如 "."、"ab*",必须是 a*b*这种形式，*在奇数位上
                if (B.length() % 2 != 0) return false;
                int i = 1;
                while (i < B.length()) {
                    if (B.charAt(i) != '*') return false;
                    i += 2;
                }
                return true;
            }
            // 如果字符串长度不为0，但是正则串没了，return false
            if (B.length() == 0) return false;
            // c1 和 c2 分别是两个串的当前位，c3是正则串当前位的后一位，如果存在的话，就更新一下
            char c1 = A.charAt(0), c2 = B.charAt(0), c3 = 'a';
            if (B.length() > 1) {
                c3 = B.charAt(1);
            }
            // 和dp一样，后一位分为是 '*' 和不是 '*' 两种情况
            if (c3 != '*') {
                // 如果该位字符一样，或是正则串该位是 '.',也就是能匹配任意字符，就可以往后走
                if (c1 == c2 || c2 == '.') {
                    return isMatch(A.substring(1), B.substring(1));
                } else {
                    // 否则不匹配
                    return false;
                }
            } else {
                // 如果该位字符一样，或是正则串该位是 '.'，和dp一样，有看和不看两种情况
                if (c1 == c2 || c2 == '.') {
                    return isMatch(A.substring(1), B) || isMatch(A, B.substring(2));
                } else {
                    // 不一样，那么正则串这两位就废了，直接往后走
                    return isMatch(A, B.substring(2));
                }
            }
        }
    }

    // 书中方法
    // todo 执行很慢，1.8s，快超时了，不是好的方法，而且，边界条件太容易出错了
    class Solution1 {
        public boolean isMatch(String str, String pattern) {
            if (str == null || pattern == null) {
                return false;
            }
            return matchCore(str, pattern, 0, 0);
        }

        public boolean matchCore(String str, String pattern, int strStart, int patternStart) {
            if (strStart >= str.length() && patternStart >= pattern.length()) {
                return true;
            }
            if (patternStart >= pattern.length()) {
                return false;
            }
            // 如果模式的当前指针的下一个字符是 *
            if (patternStart < pattern.length() - 1
                    && pattern.charAt(patternStart + 1) == '*') {
                if ( strStart < str.length() && (str.charAt(strStart) == pattern.charAt(patternStart)
                        || pattern.charAt(patternStart) == '.')) {
                    // * 号匹配了 一次
                    return matchCore(str, pattern, strStart + 1, patternStart + 2)
                            // * 号 可能要匹配多次
                            || matchCore(str, pattern, strStart + 1, patternStart)
                            // * 号 一次也没匹配
                            || matchCore(str, pattern, strStart, patternStart + 2);
                } else {
                    // * 号 一次也没匹配
                    return matchCore(str, pattern, strStart, patternStart + 2);
                }
            }
            if (strStart < str.length()
                    && (str.charAt(strStart) == pattern.charAt(patternStart)
                    || pattern.charAt(patternStart) == '.')
            ) {
                return matchCore(str, pattern, strStart + 1, patternStart + 1);
            }

            return false;
        }
    }

    @Test
    public void testSolution2() {

    }

    @Test
    public void testSolution1() {
        Assert.assertFalse(new Solution1().isMatch("ab", ".*c"));
        Assert.assertTrue(new Solution1().isMatch("a", "ab*"));
    }



}
