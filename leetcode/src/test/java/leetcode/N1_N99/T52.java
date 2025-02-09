package leetcode.N1_N99;

import org.junit.Assert;
import org.junit.Test;

/**
 * 52. N 皇后 II  （经典的八皇后问题）
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 */
public class T52 {

    int total = 0;

    public int totalNQueens(int n) {
        int[] pos = new int[n];
        backtrack(0, n, pos);
        return total;
    }

    // 为 第 row 行的皇后，寻找一个合适的位置。（前 row - 1 行都是已经放好了的）
    private void backtrack(int row, int n, int[] pos) {
        if (row == n) {
            total++;
            return;
        }
        for (int col = 0; col < n; col++) {
            boolean canChoose = true;
            for (int r = 0; r < row; r++) { // 每次都只用了前 row - 1 个 pos 中的元素来判断。所以后面的 backtrack 回溯是无需重置 pos[row]
                if (pos[r] == col) {
                    canChoose = false;
                    break;      // 相同的列不能选
                }
                if (row - r == Math.abs(col - pos[r])) { // 斜对角的不能选
                    canChoose = false;
                    break;
                }
            }
            if (canChoose) {
                pos[row] = col;
                backtrack(row + 1, n, pos);
            }
        }
    }


    @Test
    public void test() {
        // 当前编写的函数，有个外部变量 total 。所以测试用例中每次都 new 一个新的实例
        Assert.assertEquals(1, new T52().totalNQueens(1));
        Assert.assertEquals(0, new T52().totalNQueens(2));
        Assert.assertEquals(0, new T52().totalNQueens(3));
        Assert.assertEquals(2, new T52().totalNQueens(4));
        Assert.assertEquals(10, new T52().totalNQueens(5));
        Assert.assertEquals(4, new T52().totalNQueens(6));
        Assert.assertEquals(40, new T52().totalNQueens(7));
        Assert.assertEquals(92, new T52().totalNQueens(8));
        Assert.assertEquals(352, new T52().totalNQueens(9));
    }

}

