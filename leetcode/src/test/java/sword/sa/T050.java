package sword.sa;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

/**
 * 剑指 Offer 50. 第一个只出现一次的字符

    在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     示例 1:
     输入：s = "abaccdeff"
     输出：'b'

     示例 2:
     输入：s = ""
     输出：' '
 */
public class T050 {

    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        LinkedHashSet<Character> single = new LinkedHashSet<>();
        Set<Character> notSingle = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!single.contains(c) && !notSingle.contains(c)) {
                single.add(c);
            } else if (single.contains(c)) {
                single.remove(c);
                notSingle.add(c);
            }
        }
        return single.stream().findFirst().orElse(' ');
    }

    @Test
    public void test() {

    }

}
