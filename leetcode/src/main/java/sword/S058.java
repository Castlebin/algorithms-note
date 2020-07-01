package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 58 - I. 翻转单词顺序

 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 为简单起见，标点符号和普通字母一样处理。
 例如输入字符串"I am a student. "，则输出"student. a am I"。

 说明：

 无空格字符构成一个单词。
 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
*/
public class S058 {

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        if (s.trim().length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char[] str = s.trim().toCharArray();

        // 先整体翻转整个字符串
        reverseSubStr(str, 0, str.length - 1);

        // 再依次翻转每个单词
        for (int p1 = 0, p2 = 0; p2 < str.length; ) {
            while (p2 < str.length && str[p2] != ' ') {
                p2++;
            }
            for (int t = p2 - 1 ; t >= p1; t--) {
                sb.append(str[t]);
            }
            sb.append(' ');
            while (p2 < str.length && str[p2] == ' ') {
                p2++;
            }
            p1 = p2;
        }

        return sb.toString().trim();
    }

    public void reverseSubStr(char[] str, int start, int end) {
        for (int p1 = start, p2 = end; p1 < p2; p1++, p2--) {
            swap(str, p1, p2);
        }
    }

    public void swap(char[] str, int p1, int p2) {
        char tmp = str[p1];
        str[p1] = str[p2];
        str[p2] = tmp;
    }

    @Test
    public void test() {
        Assert.assertEquals("blue is sky the", reverseWords("the sky is blue"));
        Assert.assertEquals("world! hello", reverseWords("  hello world!  "));
        Assert.assertEquals("example good a", reverseWords("a good   example"));
    }

}
