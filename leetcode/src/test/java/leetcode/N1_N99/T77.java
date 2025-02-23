package leetcode.N1_N99;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * https://leetcode-cn.com/problems/combinations/
 */
public class T77 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrace(n, 0, k, new LinkedList<Integer>(), res);
        return res;
    }
    void backtrace(int n, int start, int k, LinkedList<Integer> trace, List<List<Integer>> res) {
        if (trace.size() == k) {
            res.add(new ArrayList<>(trace));
            return;
        }
        for (int i = start; i < n; i++) {
            trace.add(i + 1);
            // 注意：这里是 i + 1，因为组合不允许重复  （每次选择的数，都要比上次选择的数大，用这种方式保证不会有重复的组合）
            backtrace(n, i + 1, k, trace, res);
            trace.removeLast();
        }
    }

    @Test
    public void test() {
        System.out.println(combine(4, 2));
        System.out.println(combine(1, 1));
    }

}
