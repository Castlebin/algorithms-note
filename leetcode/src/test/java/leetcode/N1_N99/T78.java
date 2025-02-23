package leetcode.N1_N99;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
public class T78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrace(nums, 0, new LinkedList<>(), res);
        return res;
    }
    void backtrace(int[] nums, int start, LinkedList<Integer> trace, List<List<Integer>> res) {
        res.add(new ArrayList<>(trace));
        for (int i = start; i < nums.length; i++) {
            trace.addLast(nums[i]);
            backtrace(nums, i + 1, trace, res);
            trace.removeLast();
        }
    }

    @Test
    public void test() {
        System.out.println(subsets(new int[]{1, 2, 3}));
        System.out.println(subsets(new int[]{}));
    }
}
