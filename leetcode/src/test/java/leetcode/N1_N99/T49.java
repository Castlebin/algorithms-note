package leetcode.N1_N99;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


/**
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 */
public class T49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> group = new HashMap<>();
        for (String s : strs) {
            String encode = encode(s);
            List<String> list = group.get(encode);
            if (list == null) {
                list = new ArrayList<>();
                group.put(encode, list); // 按字母异位词分组了
            }
            list.add(s);
        }
        List<List<String>> result = new ArrayList<>();
        group.forEach((k, v) -> result.add(v));
        return result;
    }

    /**
     * 关键在于如何快速的判断两个单词是字母异位词
     * 由于题目中已经说了，单词仅包含英文小写字母，所以我们想到了利用 数组计数 来快速判断字母异位词
     * 利用一个长度为 26 的整数数组，来对每个单词进行统计字母出现对的个数。只要两个单词的字母计数数组是一样的，
     * 那么就是字母异位词
     */
    private String encode(String s) {
        char[] count = new char[26]; // 使用 char[]，方便直接转化为 String
        char[] charArr = s.toCharArray();
        for (char c : charArr) {
            count[c - 'a']++;
        }
        return new String(count);
    }


    @Test
    public void testGroupAnagrams() {
        T49 t49 = new T49();

        // Test case 1
        String[] input1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> expected1 = Arrays.asList(
                Arrays.asList("eat", "tea", "ate"),
                Arrays.asList("tan", "nat"),
                Arrays.asList("bat")
        );
        List<List<String>> result1 = t49.groupAnagrams(input1);
        Assert.assertTrue(compareResult(expected1, result1));

        // Test case 2
        String[] input2 = {""};
        List<List<String>> expected2 = Arrays.asList(
                Arrays.asList("")
        );
        List<List<String>> result2 = t49.groupAnagrams(input2);
        Assert.assertTrue(compareResult(expected2, result2));

        // Test case 3
        String[] input3 = {"a"};
        List<List<String>> expected3 = Arrays.asList(
                Arrays.asList("a")
        );
        List<List<String>> result3 = t49.groupAnagrams(input3);
        Assert.assertTrue(compareResult(expected3, result3));
    }

    private boolean compareResult(List<List<String>> expected, List<List<String>> result) {
        if (expected.size() != result.size()) {
            return false;
        }
        for (List<String> list : expected) {
            boolean found = false;
            for (List<String> resList : result) {
                if (list.size() == resList.size() && list.containsAll(resList)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

}
