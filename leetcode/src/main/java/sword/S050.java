package sword;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 面试题50. 第一个只出现一次的字符

 在字符串 s 中找出第一个只出现一次的字符。
 如果没有，返回一个单空格。 s 只包含小写字母。
 */
public class S050 {

    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int index = 0; index < s.length(); index++) {
            char ch = s.charAt(index);
            if (ch > 'z' || ch < 'a') {
                throw new IllegalArgumentException("字符串包含非小写字母");
            }
            // 1.8 新增的 merge 函数
            map.merge(ch, 1, Integer::sum);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return ' ';
    }

    @Test
    public void test() {
        Assert.assertEquals('b', firstUniqChar("abaccdeff"));
        Assert.assertEquals(' ', firstUniqChar(""));
    }

}
