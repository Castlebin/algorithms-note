package sword;

import org.junit.Test;

import java.util.*;

/**
 剑指 Offer 38. 字符串的排列

 输入一个字符串，打印出该字符串中字符的所有排列。

 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

 示例:

 输入：s = "abc"
 输出：["abc","acb","bac","bca","cab","cba"]

 限制：
 1 <= s 的长度 <= 8
 */
public class S038 {

    public String[] permutation(String s) {
        if (s == null || s.length() == 0) {
            return new String[]{};
        }
        Set<String> permutationSet = new HashSet<>();
        permutationSet.add(s.substring(0, 1));
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            Set<String> newSet = new HashSet<>();
            for (String permu : permutationSet) {
                for (int index = 0; index <= i; index++){
                    String per = permu.substring(0, index) + c + permu.substring(index);
                    newSet.add(per);
                }
            }
            permutationSet = newSet;
        }
        return permutationSet.toArray(new String[]{});
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(permutation("abc")));
    }

}
