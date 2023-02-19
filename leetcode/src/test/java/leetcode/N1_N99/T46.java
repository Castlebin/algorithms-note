package leetcode.N1_N99;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


/**
 * 46. Permutations
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
public class T46 {

    /**
     * 解法1：全排列的递归解法
     */
    class Solution_1 {
        public List<List<Integer>> permute(int[] nums) {
            if (nums == null || nums.length == 0) {
                return new ArrayList<>();
            }
            List<List<Integer>> res = new ArrayList<>();
            // 让它有个迭代的基础
            res.add(new ArrayList<>());
            for (int num : nums) {
                res = permute(res, num);
            }
            return res;
        }

        private List<List<Integer>> permute(List<List<Integer>> res, int num) {
            List<List<Integer>> thisRes = new ArrayList<>();
            for (List<Integer> re : res) {
                for (int index = 0; index <= re.size(); index++) {
                    List<Integer> copy = new ArrayList<>(re);
                    copy.add(index, num);
                    thisRes.add(copy);
                }
            }
            return thisRes;
        }
    }

    @Test
    public void test() {
        new Solution_1().permute(new int[] {1, 2, 3});
    }

}
