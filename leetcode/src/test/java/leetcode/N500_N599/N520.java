package leetcode.N500_N599;

import org.junit.Assert;
import org.junit.Test;

/**
 * 520. Detect Capital
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Given a string word, return true if the usage of capitals in it is right.
 *
 * Constraints:
 *
 * 1 <= word.length <= 100
 * word consists of lowercase and uppercase English letters.
 */
public class N520 {

    public boolean detectCapitalUse(String word) {
        if (word.length() < 1) {
            return false;
        }
        if (word.length() == 1) {
            return true;
        }
        char firstChar = word.charAt(0);
        if (isLowerCase(firstChar)) {
            // 首字母小写，其他字母必须全部为小写
            for (int index = 1; index < word.length(); index++) {
                if (!isLowerCase(word.charAt(index))) {
                    return false;
                }
            }
        } else if (isUpperCase(firstChar)) {
            // 首字母大写，其他字母要么全部小写，要么全部大写
            char secondChar = word.charAt(1);
            if (isLowerCase(secondChar)) {
                for (int index = 2; index < word.length(); index++) {
                    if (!isLowerCase(word.charAt(index))) {
                        return false;
                    }
                }
            } else if (isUpperCase(secondChar)) {
                for (int index = 2; index < word.length(); index++) {
                    if (!isUpperCase(word.charAt(index))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    @Test
    public void testDetectCapitalUse() {
        Assert.assertTrue(detectCapitalUse("USA"));
        Assert.assertTrue(detectCapitalUse("leetcode"));
        Assert.assertTrue(detectCapitalUse("Google"));
        Assert.assertFalse(detectCapitalUse("FlaG"));
    }

}
