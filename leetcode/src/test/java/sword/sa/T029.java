package sword.sa;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 29. 顺时针打印矩阵
 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

 示例 1：
 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 输出：[1,2,3,6,9,8,7,4,5]

 示例 2：
 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class T029 {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, cols = matrix[0].length;
        int[] res = new int[rows * cols];
        for (int m = 0, n = 0, i = 0; m <= rows / 2 && n <= cols / 2; m++, n++) {
            for (int col = n; i < res.length && col < cols - n; col++) {
                res[i++] = matrix[m][col];
            }
            for (int row = m + 1; i < res.length && row < rows - m; row++) {
                res[i++] = matrix[row][cols - 1 - m];
            }
            for (int col = cols - 1 - m - 1; i < res.length && col >= n; col--) {
                res[i++] = matrix[rows - 1 - m][col];
            }
            for (int row = rows - 1 - n - 1; i < res.length && row > m; row--) {
                res[i++] = matrix[row][n];
            }
        }
        return res;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 6, 9, 8, 7, 4, 5}, spiralOrder(new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 9},
        }));
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7}, spiralOrder(new int[][]{
                new int[]{1, 2,  3,  4},
                new int[]{5, 6,  7,  8},
                new int[]{9, 10, 11, 12},
        }));
    }

}
