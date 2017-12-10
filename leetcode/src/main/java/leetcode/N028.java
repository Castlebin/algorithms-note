package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 28. Implement strStr()
 Implement strStr().

 Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 Example 1:

 Input: haystack = "hello", needle = "ll"
 Output: 2
 Example 2:

 Input: haystack = "aaaaa", needle = "bba"
 Output: -1
 */
public class N028 {

    public int strStr(String haystack, String needle) {
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }
        if (needle.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (needle.equals(haystack.substring(i, i + needle.length()))) {
                return i;
            }
        }

        return -1;
    }

    @Test
    public void test() {
        int[] nums = new int[] {-1, 2, 1, -4};
        int target = 1;
        Assert.assertEquals(2, strStr("hello", "ll"));
        Assert.assertEquals(-1, strStr("aaaaa", "bba"));
    }

}
