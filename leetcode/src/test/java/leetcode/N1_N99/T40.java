package leetcode.N1_N99;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 40. Combination Sum II
 * 40. 组合总和 II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 */
public class T40 {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 先排序，能够简化很多这种排列问题，并且减少时间复杂度
        Arrays.sort(candidates);
        backtrack(new ArrayList<>(), candidates, target, 0, 0);
        return res;
    }

    private void backtrack(List<Integer> track, int[] candidates, int target, int sum, int begin) {
        if (sum == target) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            // 因为前一个元素值和它相同，用这个值作为开始已经试过了，不必再试
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            int rs = candidates[i] + sum;
            if (rs <= target) {
                track.add(candidates[i]);
                backtrack(track, candidates, target, rs, i + 1);
                track.remove(track.size() - 1);
            } else {
                break;
            }
        }
    }

    @Test
    public void test() {
        combinationSum2(new int[] {10, 1, 2, 7, 6, 1, 5}, 8);
    }

}
