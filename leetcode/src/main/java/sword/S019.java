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
