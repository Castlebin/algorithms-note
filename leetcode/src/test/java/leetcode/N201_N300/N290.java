package leetcode.N201_N300;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

/**
 * 290. Word Pattern
 *
 * Constraints:
 *
 * 1 <= pattern.length <= 300
 * pattern contains only lower-case English letters.
 * 1 <= s.length <= 3000
 * s contains only lowercase English letters and spaces ' '.
 * s does not contain any leading or trailing spaces.
 * All the words in s are separated by a single space.
 */
public class N290 {

    public boolean wordPattern(String pattern, String s) {
        char[] chars = pattern.toCharArray();
        String[] splitWords = s.split(" ");
        if (chars.length != splitWords.length) {
            return false;
        }
        Map<Character, String> matchMap = new HashMap<>();
        Map<String, Character> reMatchMap = new HashMap<>();
        matchMap.put(chars[0], splitWords[0]);
        reMatchMap.put(splitWords[0], chars[0]);
        for (int index = 1; index < chars.length; index++) {
            String match = matchMap.get(chars[index]);
            String word = splitWords[index];
            if (match == null) {
                matchMap.put(chars[index], word);
                if (reMatchMap.get(word) != null) {
                    return false;
                }
                reMatchMap.put(word, chars[index]);
            } else if (!Objects.equals(match, word)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testWordPattern() {
        Assert.assertTrue(wordPattern("abba", "dog cat cat dog"));
        Assert.assertFalse(wordPattern("abba", "dog cat cat fish"));
        Assert.assertFalse(wordPattern("aaaa", "dog cat cat dog"));
        Assert.assertFalse(wordPattern("abba", "dog dog dog dog"));
    }

}
