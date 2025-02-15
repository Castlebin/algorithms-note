package leetcode.N1_N99;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;


/**
 * 51. N-Queens
 * 51. N 皇后  （经典的八皇后问题）
 */
public class T51 {

    public List<List<String>> solveNQueens(int n) {
        LinkedList<Integer> track = new LinkedList<>();
        List<List<Integer>> permutations = new ArrayList<>();
        backtrack(n, track, permutations);
        List<List<String>> result = resolve(permutations);
        return result;
    }
    
    private void backtrack(int n, LinkedList<Integer> track, List<List<Integer>> permutations) {
        if (!check(track)) {
            return;
        }
        if (track.size() == n) {
            // 说明找到一个合法的排列了
            permutations.add(new ArrayList<>(track));
            return;
        }
        Set<Integer> used = new HashSet<>(track);
        // 为新的一行选择皇后的位置
        for (int pos = 0; pos < n; pos++) {
            // 保证这一列没有被选择过
            if (used.contains(pos)) {
                continue;
            }
            track.add(pos);
            backtrack(n, track, permutations);
            // 删除刚刚添加进来的选择，回溯！
            track.removeLast();
        }
    }

    private boolean check(LinkedList<Integer> track) {
        if (track.isEmpty()) {
            return true;
        }
        // 因为每为新一行选择皇后的位置，都会check，所以，只需要检查最近行皇后的位置，和它前面行皇后的位置，是否冲突，就行了
        int pos = track.getLast();
        for (int index = 0; index < track.size() - 1; index++) {
            // 判断斜角
            int p = track.get(index);
            if (Math.abs(pos - p) == track.size() - 1 - index) {
                return false;
            }
            // 判断列 (如果放的时候已经保证了，其实这一步可以不要)
            if (pos == p) {
                return false;
            }
            // 行不用判断，因为这是个一维数组
        }
        return true;
    }

    private List<List<String>> resolve(List<List<Integer>> permutations) {
        if (permutations.isEmpty()) {
            return new ArrayList<>();
        }
        int n = permutations.get(0).size();
        String blankRow = buildBlankRowForQueen(n);
        List<List<String>> result = new ArrayList<>();
        for (List<Integer> permutation : permutations) {
            List<String> perm = new ArrayList<>();
            for (Integer col : permutation) {
                // 表示这一行是在 col 列放置了皇后
                char[] rowPerm = blankRow.toCharArray();
                rowPerm[col] = 'Q';
                perm.add(new String(rowPerm));
            }
            result.add(perm);
        }
        return result;
    }

    private String buildBlankRowForQueen(int n) {
        char[] arr = new char[n];
        Arrays.fill(arr, '.');
        return new String(arr);
    }

    @Test
    public void test() {
        solveNQueens(4);
        solveNQueens(8);
    }

}
