package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 19. 正则表达式匹配

 请实现一个函数用来匹配包含'. '和'*'的正则表达式。
 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 在本题中，匹配是指字符串的所有字符匹配整个模式。
 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 
 （动态规划，最快）
 */
public class S019 {

    /**
     * 同样的 动态规划 方法
     */
    class Solution4 {
        public boolean isMatch(String s, String p) {
            if (s == null || p == null) {
                return false;
            }
            int m = s.length();
            int n = p.length();
            boolean[][] dp = new boolean[m + 1][n + 1];
            dp[0][0] = true;
            for (int i = 0; i < n; i++) {
                //如果p的第i+1个字符也就是p.charAt(i)是"*"的话，
                //那么他就可以把p的第i个字符给消掉（也就是匹配0次）。
                //我们只需要判断p的第i-1个字符和s的前0个字符是否匹
                //配即可。比如p是"a*b*"，如果要判断p的第4个字符
                //"*"和s的前0个字符是否匹配，因为字符"*"可以消去
                //前面的任意字符，只需要判断p的"a*"和s的前0个字
                //符是否匹配即可
                if (p.charAt(i) == '*' && dp[0][i - 1]) {
                    dp[0][i + 1] = true;
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
                        dp[i + 1][j + 1] = dp[i][j];
                    } else if (p.charAt(j) == '*') {
                        //递归公式
                        if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
                            dp[i + 1][j + 1] = dp[i][j + 1];
                        }
                        dp[i + 1][j + 1] |= dp[i + 1][j - 1];
                    }
                }
            }
            return dp[m][n];
        }
    }

    /**
     * 83 ms，因为 每次都是新建数组，所以多了一点消耗，大体算法思路和 Solution1 一样的
     */
    class Solution3 {
        public boolean isMatch(String str, String pattern) {
            // 如果 str 或者 pattern 有一个为 null ，说明不匹配
            if (str == null || pattern == null) {
                return false;
            }
            // 如果正则长度为 0，字符串长度不为 0，说明不匹配
            if (pattern.length() == 0) {
                return str.length() == 0;
            }
            // 如果字符串长度为 0，则还需要看正则是否为 x*x* 格式
            if (str.length() == 0) {
                // 如果正则长度为奇数，一定不匹配
                if (pattern.length() % 2 == 1) {
                    return false;
                }
                // 看正则是否为 x*x*x* 格式
                for (int i = 1; i < pattern.length(); i += 2) {
                    if (pattern.charAt(i) != '*') {
                        return false;
                    }
                }
                return true;
            }
            // 到了这一步，str 和 pattern 的长度都不为 0
            char s = str.charAt(0);
            char p = pattern.charAt(0);
            // 如果 pattern 长度大于 1，且pattern的下一个字符为 *
            if (pattern.length() > 1 && pattern.charAt(1) == '*') {
                // 如果 pattern 和 str 的第一个字符都一样
                // 分 看 和 不看，两种情况 ， 如 (a , a*a) 这个例子，当前就不能匹配
                if (s == p || p == '.') {
                    // isMatch(str.substring(1), pattern.substring(2)) 不用写，递归时会执行到，写了增加了很多执行次数，特别慢
                    return isMatch(str.substring(1), pattern)
                            || isMatch(str, pattern.substring(2));
                }else {
                    // 不一样，那么正则的这两位就废了，直接往后走
                    return isMatch(str, pattern.substring(2));
                }
            } else {
                // 如果该位字符一样，或是正则串该位是 '.',也就是能匹配任意字符，就可以往后走
                if (s == p || p == '.') {
                    return isMatch(str.substring(1), pattern.substring(1));
                } else {
                    // 否则不匹配
                    return false;
                }
            }
        }
    }

    /**
     * 动态规划，非常快，5 ms！！
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

    // 书中方法
    // todo 执行很慢，1.8s，快超时了，不是好的方法，而且，边界条件太容易出错了
    //  （因为执行了多余的迭代，改进了，快很多了，变为 37ms，厉害！）
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
                    // * 号匹配了 一次  (去掉 matchCore(str, pattern, strStart + 1, patternStart + 2) ，执行快了很多)
                    return /*matchCore(str, pattern, strStart + 1, patternStart + 2)
                            // * 号 可能要匹配多次
                            || */
                            matchCore(str, pattern, strStart + 1, patternStart)
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
        Assert.assertTrue(new Solution3().isMatch("bbbba", ".*a*a"));
        Assert.assertTrue(new Solution1().isMatch("ab",".*"));
        Assert.assertFalse(new Solution1().isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*c"));
        Assert.assertTrue(new Solution1().isMatch("mississippi","mis*is*ip*."));
        Assert.assertTrue(new Solution1().isMatch("aa", "a*"));
        Assert.assertFalse(new Solution1().isMatch("ab",".*c"));
        Assert.assertTrue(new Solution1().isMatch("aab","c*a*b"));
    }

}
