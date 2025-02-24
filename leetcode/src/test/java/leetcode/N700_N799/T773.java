package leetcode.N700_N799;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * 773. 滑动谜题
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示，以及一块空缺用 0 来表示。
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换。
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * 给出初始板 board，找到能解开谜板的最少移动次数。
 * 如果不能解开谜板，返回 -1。
 * https://leetcode.cn/problems/sliding-puzzle/
 */
public class T773 {
    public int slidingPuzzle(int[][] board) {
        // 将 2*3 的矩阵转换为字符串，方便比较
        // adj[i] 代表的是 当 0 在位置 i 时，它相邻的位置（可以交换的位置）
        // 0 1 2
        // 3 4 5
        // 查看上面这个矩阵，可以得到相邻的位置
        int[][] adj = new int[][] {
                new int[]{1, 3},    // 0 的相邻位置
                new int[]{0, 2, 4}, // 1 的相邻位置
                new int[]{1, 5},    // 2 的相邻位置
                new int[]{0, 4},    // 3 的相邻位置
                new int[]{1, 3, 5}, // 4 的相邻位置
                new int[]{2, 4}     // 5 的相邻位置
        };
        // 目标状态
        String target = "123450";
        // 初始状态
        String init = buildState(board);
        Queue<String> queue = new LinkedList<>();
        queue.add(init);
        int step = 0;
        Set<String> used = new HashSet<>();
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int index = 0; index < sz; index++) {
                String cur = queue.poll();
                if (target.equals(cur)) { // 到达目标状态
                    return step;
                }
                used.add(cur);
                // 1. 找到 0 的位置；
                int i = 0;
                for (; i < cur.length(); i++) {
                    if (cur.charAt(i) == '0') {
                        break;
                    }
                }
                // 2. 将 0 和它相邻的位置交换，生成新的状态
                for (int ad : adj[i]) {
                    char[] cs = cur.toCharArray();
                    cs[i] = cs[ad];
                    cs[ad] = '0';
                    String next = new String(cs);
                    if (!used.contains(next)) {
                        queue.add(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String buildState(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(1, slidingPuzzle(new int[][]{new int[]{1, 2, 3}, new int[]{4, 0, 5}}));
        Assert.assertEquals(-1, slidingPuzzle(new int[][]{new int[]{1, 2, 3}, new int[]{5, 4, 0}}));
        Assert.assertEquals(5, slidingPuzzle(new int[][]{new int[]{4, 1, 2}, new int[]{5, 0, 3}}));
    }

}
