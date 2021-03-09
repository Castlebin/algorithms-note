package algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1277. 统计全为 1 的正方形子矩阵

  给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，
  请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。

 示例 1：

 输入：matrix =
     [
     [0,1,1,1],
     [1,1,1,1],
     [0,1,1,1]
     ]
 输出：15
 解释：
 边长为 1 的正方形有 10 个。
 边长为 2 的正方形有 4 个。
 边长为 3 的正方形有 1 个。
 正方形的总数 = 10 + 4 + 1 = 15.

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class N1227 {

    public int countSquares(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // 定义 dp 数组，其中 坐标为(i,j) 的元素值表示
        // 以 matrix 中该坐标的元素作为正方形的最右下角最大的正方形边长
        int[][] dp = new int[rows][cols];

        int count = 0;
        // 递推公式
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 1 && row > 0 && col > 0) {
                    dp[row][col] = Math.min(dp[row - 1][col - 1], Math.min(dp[row - 1][col], dp[row][col - 1])) + 1;
                } else {
                    // 因为 第一行或者第一列的元素，dp[row][col] 最多为 1 ，即 与 matrix 中对应坐标的元素值相等
                    // 另外 matrix[row][col] 为 0 时，显然 dp[row][col] = 0
                    dp[row][col] = matrix[row][col];
                }
                count += dp[row][col];
            }
        }

        return count;
    }

    @Test
    public void testCountSquires() {
        int[][] matrix;

        matrix = new int[][] {{1,0,1,0,1},
                              {1,0,0,1,1},
                              {0,1,0,1,1},
                              {1,0,0,1,1}};
        Assert.assertEquals(14, countSquares(matrix));

        matrix = new int[][]{{0,1,1,1},
                             {1,1,1,1},
                             {0,1,1,1}};
        Assert.assertEquals(15, countSquares(matrix));

        matrix = new int[][]{{1,0,1},
                             {1,1,0},
                             {1,1,0}};
        Assert.assertEquals(7, countSquares(matrix));

        matrix = new int[][]{{1,1},
                             {0,0},
                             {1,1}};
        Assert.assertEquals(4, countSquares(matrix));
    }

}
