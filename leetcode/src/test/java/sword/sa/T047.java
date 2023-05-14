package sword.sa;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 47. 礼物的最大价值
 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 
 示例 1:输入:
 [
   [1,3,1],
   [1,5,1],
   [4,2,1]
 ]
 输出: 12
 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物

 提示：
 0 < grid.length <= 200
 0 < grid[0].length <= 200

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof
 */
public class T047 {

    /**
     * 典型的动态规划题目
     * 能选择第 [row, col] 个礼物，只能是它的上面或者左边选过来的
     * 所以，声明的是一个和给定矩阵同样大小的 dp 矩阵 :  [ m * n ]
     */
    public int maxValue(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        // 初始化第一行第一列
        dp[0][0] = grid[0][0];
        for (int col = 1; col < cols; col++) {
            dp[0][col] = dp[0][col - 1] + grid[0][col];
        }
        for (int row = 1; row < rows; row++) {
            dp[row][0] = dp[row - 1][0] + grid[row][0];
        }
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                // 能选择第 [row, col] 个礼物，只能是它的上面或者左边选过来的
                dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]) + grid[row][col];
            }
        }
        return dp[rows - 1][cols - 1];
    }

    @Test
    public void test() {
        int[][] grid = new int[][] {
                new int[] {1, 3, 1},
                new int[] {1, 5, 1},
                new int[] {4, 2, 1}
        };
        Assert.assertEquals(12, maxValue(grid));
    }

}
