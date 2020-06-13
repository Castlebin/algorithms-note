package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * 04. 二维数组中的查找
 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

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

}
