package leetcode.N100_N199;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * 128. 最长连续序列
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * **请你设计并实现时间复杂度为 O(n) 的算法解决此问题。**
 */
public class T128 {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLen = 0;
        for (int num : set) {
            if (set.contains(num - 1)) {
                // 说明不是连续序列的起点，跳过  (这样保证了 O(n) 的时间复杂度，每个元素只会被访问最多两次)
                continue;
            }
            // 这个数字是一个连续序列的起点，开始计算连续序列的长度
            int curNum = num;
            int curLen = 1;
            while (set.contains(curNum + 1)) {
                curNum++;
                curLen++;
            }
            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen;
    }

    @Test
    public void test() {
        Assert.assertEquals(4, longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
        Assert.assertEquals(9, longestConsecutive(new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        Assert.assertEquals(1, longestConsecutive(new int[] {0}));
        Assert.assertEquals(0, longestConsecutive(new int[] {}));
        Assert.assertEquals(3, longestConsecutive(new int[] {1, 2, 0, 1}));
        Assert.assertEquals(4, longestConsecutive(new int[] {1, 2, 0, 1, 3}));
    }

}
