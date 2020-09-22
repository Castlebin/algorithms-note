package sword;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 剑指 Offer 29. 顺时针打印矩阵
 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

 示例 1：
 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 输出：[1,2,3,6,9,8,7,4,5]

 示例 2：
 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 输出：[1,2,3,4,8,12,11,10,9,5,6,7]

 限制：
 0 <= matrix.length <= 100
 0 <= matrix[i].length <= 100
 */
public class S029 {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[]{};
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] result = new int[rows * cols];
        // x, y 为每一轮的起点坐标
        for (int x = 0, y = 0, index = 0; x <= (rows + 1)/2 && y <= (cols + 1) / 2; x++, y++) {
            for (int col = y; col < cols - y && index < result.length; col++) {
                result[index++] = matrix[x][col];
            }
            for (int row = x + 1; row < rows - x && index < result.length; row++) {
                result[index++] = matrix[row][cols - y - 1];
            }
            for (int col = cols - y - 2; col >= y && index < result.length; col--) {
                result[index++] = matrix[rows - x - 1][col];
            }
            for (int row = rows - x - 2; row > x && index < result.length; row--) {
                result[index++] = matrix[row][y];
            }
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{1,2,3,6,9,8,7,4,5}, spiralOrder(
                new int[][]{{1,2,3},
                        {4,5,6},
                        {7,8,9}}
        ));
        Assert.assertArrayEquals(new int[]{1,2,3,4,8,12,11,10,9,5,6,7}, spiralOrder(
                new int[][]{{1,2,3,4},
                        {5,6,7,8},
                        {9,10,11,12}}
        ));
    }

}
