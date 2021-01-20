package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * 04. 二维数组中的查找
 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。

 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

 （思考一下变体，假设需要找到所有的目标数的位置并返回。该如何做？）

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S004 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 边界条件判断
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        // 获取函数矩阵的右上角元素的坐标
        int x = 0, y = matrix[0].length - 1;
        while (x <= matrix.length - 1 && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] > target) {
                y--;
            } else {
                x++;
            }
        }
        
        return false;
    }

    @Test
    public void test() {
        int[][] array = {{-1, 3}};
        Assert.assertEquals(true, findNumberIn2DArray(array, 3));
    }

    /**
     * 仔细分析之后，其实也可以用递归方式，得出另一种解法，理论上更快，但稍复杂
     */
    class Solution2 {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if (matrix == null
                    || matrix.length <= 0
                    || matrix[0] == null || matrix[0].length <= 0) {
                return false;
            }

            return findNumberIn2DArray(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
        }

        public boolean findNumberIn2DArray(int[][] matrix, int target, int startX, int endX, int startY, int endY) {
            if (startX > endX || startY > endY) {
                return false;
            }
            int middleX = (endX - startX) / 2 + startX;
            int middleY = (endY - startY) / 2 + startY;
            if (matrix[middleX][middleY] == target) {
                return true;
            }
            if (matrix[middleX][middleY] > target) {
                return findNumberIn2DArray(matrix, target, startX, endX, startY, middleY - 1)
                        || findNumberIn2DArray(matrix, target, startX, middleX - 1, middleY, endY);
            } else {
                return findNumberIn2DArray(matrix, target, middleX + 1, endX, startY, endY)
                        || findNumberIn2DArray(matrix, target, startX, middleX, middleY + 1, endY);
            }
        }
    }

}
