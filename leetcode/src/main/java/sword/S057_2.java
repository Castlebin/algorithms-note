package sword;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 剑指 Offer 57 - II. 和为s的连续正数序列
 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。



 示例 1：

 输入：target = 9
 输出：[[2,3,4],[4,5]]
 示例 2：

 输入：target = 15
 输出：[[1,2,3,4,5],[4,5,6],[7,8]]


 限制：

 1 <= target <= 10^5
*/
public class S057_2 {
    public int[][] findContinuousSequence(int target) {
        // 滑动窗口的左边界
        int i = 1;
        // 滑动窗口的右边界
        int j = 1;
        // 滑动窗口中的数字和
        int sum = 0;

        List<int[]> res = new ArrayList<>();
        while (i <= target / 2) {
            if (sum < target) {
                sum += j++; // 窗口右边界向右移动
            } else if (sum > target) {
                sum -= i++; // 窗口左边界向右移动
            } else {
                int[] seq = new int[j - i];
                for (int k = i; k < j; k++) {
                    seq[k - i] = k;
                }
                res.add(seq);
                // 左边界向右移动
                sum -= i++;
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    @Test
    public void test() {
        findContinuousSequence(15);
    }

}
