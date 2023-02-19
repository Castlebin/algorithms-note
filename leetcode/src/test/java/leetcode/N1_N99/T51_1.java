package leetcode.N1_N99;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 51. N-Queens
 * 51. N 皇后  （经典的八皇后问题）
 */
public class T51_1 {

    /**
     * 换一种思路解 N 皇后问题，迭代
     * 假设前面 i - 1 行已经符合要求，现在我们为第 i 行寻找一个可以放置皇后的位置
     * 依次迭代剩下的行
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // 记录当前已经选择好的结果（注意，只有前面 i - 1 个数据是有效的，代表前面 i - 1 行的选择，后面的还没选呢）
        int[] pos = new int[n];
        // 从第 0 行开始
        choose(0, n, pos, result);
        return result;
    }

    /**
     * 前面的 row - 1 行都已经就绪，现在为第 row 行，选择一个可以放置皇后的位置
     * （所以，pos 中，当前只有前 row - 1 个元素使有效的！）
     */
    private void choose(int row, int n, int[] pos, List<List<String>> result) {
        if (row == n) {
            // 说明已经都选过了
            result.add(buildPos(pos));
        }
        // 为第 row 行，选择一个可以放置皇后的位置
        for (int col = 0; col < n; col++) {
            if (checkCol(row, col, pos)) {
                // 位置可以。用它！
                pos[row] = col;
                // 继续为下一行选择可以放置皇后的位置
                choose(row + 1, n, pos, result);
            }
        }
    }

    private boolean checkCol(int row, int col, int[] pos) {
        // 只需要判断新选择的这一行的位置即可
        for (int r = 0; r < row; r++) {
            // 判断斜角
            if (Math.abs(col - pos[r]) == row - r) {
                return false;
            }
            // 判断列
            if (col == pos[r]) {
                return false;
            }
        }
        return true;
    }

    private List<String> buildPos(int[] pos) {
        char[] blank = new char[pos.length];
        Arrays.fill(blank, '.');
        String blankRow = new String(blank);
        List<String> result = new ArrayList<>();
        for (int p : pos) {
            char[] chars = blankRow.toCharArray();
            chars[p] = 'Q';
            result.add(new String(chars));
        }
        return result;
    }

    @Test
    public void test() {
        solveNQueens(4);
        solveNQueens(8);
    }

}
