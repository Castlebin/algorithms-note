package sword.sa;

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
 */
public class T057 {

    public int[][] findContinuousSequence(int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (target < 3) {
            return trans(res);
        }

        int p1 = 1, sum = 1;
        for (int p2 = 2; p2 <= target / 2 + 1; p2++) {
            sum += p2;
            while (sum > target) {
                sum -= (p1++);
            }
            if (sum == target) {
                List<Integer> list = new ArrayList<>();
                for (int i = p1; i <= p2; i++) {
                    list.add(i);
                }
                res.add(list);
            }
        }

        return trans(res);
    }

    private int[][] trans(List<List<Integer>> list) {
        int[][] res = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> l = list.get(i);
            int[] row = new int[l.size()];
            for (int j = 0; j < l.size(); j++) {
                row[j] = l.get(j);
            }
            res[i] = row;
        }
        return res;
    }

    @Test
    public void test() {
        findContinuousSequence(9);
    }

}
