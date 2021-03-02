package interview;

/**
 快手 1 面

 两道算法题
 1. 顺时针打印矩阵  (关键点  && index < result.length )
 2. 二进制求和
 */
public class N210103 {

    /**
     * 剑指 Offer 29. 顺时针打印矩阵  （ https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/ ）
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     *
     *
     *
     * 示例 1：
     *
     * 输入：matrix = [[1,2,3],
     *                [4,5,6],
     *                [7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
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

    /**
     * 二进制数字字符串做加法
     *
     * 67. 二进制求和   ( https://leetcode-cn.com/problems/add-binary/ )
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     *
     * 输入为 非空 字符串且只包含数字 1 和 0。
     *
     *
     *
     * 示例 1:
     *
     * 输入: a = "11", b = "1"
     * 输出: "100"
     * 示例 2:
     *
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     */
    public String addBigNumberStr(String num1, String num2) {
        // 2 进制
        int SCALE = 2;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        for (int p1 = num1.length() - 1, p2 = num2.length() - 1; p1 >= 0 || p2 >= 0 || carry > 0; p1--, p2--) {
            int n1 = p1 >= 0? num1.charAt(p1) - '0' : 0;
            int n2 = p2 >= 0? num2.charAt(p2) - '0' : 0;
            int added = n1 + n2 + carry;
            int a = added >= SCALE? added - SCALE : added;
            res.insert(0, (char)(a + '0'));
            carry = added >= SCALE? 1 : 0;
        }
        return res.toString();
    }

}
