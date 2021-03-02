package algorithm;

public class SpiralOrder {

    /**
     * 顺时针打印矩阵
     * 注意一定要加上 && index < result.length   ！！
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] result = new int[rows * cols];
        // 一圈一圈的来，x，y 分别为每一轮的起始坐标
        for (int x = 0, y = 0, index = 0; x <= (rows + 1) / 2 && y <= (cols + 1) / 2; x++, y++) {
            // 上边从左到右的这一行 (&& index < result.length   !!)
            for (int col = y; col < cols - y && index < result.length; col++) {
                result[index++] = matrix[x][col];
            }
            // 右边从上往下的这一列
            for (int row = x + 1; row < rows - x && index < result.length; row++) {
                result[index++] = matrix[row][cols - 1 - y];
            }
            // 下面从右往左这一行
            for (int col = cols - y - 2; col >= y && index < result.length; col--) {
                result[index++] = matrix[rows - x - 1][col];
            }
            // 左边从下往上这一列
            for (int row = rows - x - 2; row > x && index < result.length; row--) {
                result[index++] = matrix[row][y];
            }
        }
        return result;
    }

}
