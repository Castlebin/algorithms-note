package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 58 - II. 左旋转字符串

 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 请定义一个函数实现字符串左旋转操作的功能。

 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

 限制：
 1 <= k < s.length <= 10000
*/
public class S058_2 {

    public String reverseLeftWords(String s, int k) {
        char[] str = s.toCharArray();
        // 把字符串看做两部分，每部分是一个单词，因此，该题和前面旋转单词 那题 是一样的，比它简单
        // 先把整个字符串旋转
        reverseSubStr(str, 0, str.length - 1);
        // 再依次旋转每个单词
        reverseSubStr(str, 0, str.length - 1 - k);
        reverseSubStr(str, str.length - k, str.length - 1);
        return new String(str);
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
        Assert.assertEquals("cdefgab", reverseLeftWords("abcdefg", 2));
    }

}
