package sword.sa;

import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer 04. 二维数组中的查找
 *
 * 在一个 n * m 的二维数组中，每一行都按照从左到右 非递减 的顺序排序，每一列都按照从上到下 非递减 的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
  示例:
  现有矩阵 matrix 如下：
  [
    [1,   4,  7, 11, 15],
    [2,   5,  8, 12, 19],
    [3,   6,  9, 16, 22],
    [10, 13, 14, 17, 24],
    [18, 21, 23, 26, 30]
  ]
  给定 target = 5，返回 true。
  给定 target = 20，返回 false。

 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 */
public class T004 {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, cols = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[rows - 1][cols - 1]) {
            return false;
        }
        // 从右上角开始遍历，按行按列划去不符合的行列
        for (int i = 0, j = cols - 1; i < rows && j >= 0; ) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] < target) {// 说明这一行都不行，划掉
                i++;
            }
            // 注意边界处理 (i 变大了，所以要再判断一下是否越界)
            if (i < rows && matrix[i][j] > target) {// 说明这一列都不行，划掉
                j--;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[][] matrix = new int[][]{
                new int[]{1,   4,  7, 11, 15},
                new int[]{2,   5,  8, 12, 19},
                new int[]{3,   6,  9, 16, 22},
                new int[]{10, 13, 14, 17, 24},
                new int[]{18, 21, 23, 26, 30},
        };
        Assert.assertEquals(true, findNumberIn2DArray(matrix, 5));
        Assert.assertEquals(false, findNumberIn2DArray(matrix, 20));
    }

}
