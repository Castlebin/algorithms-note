package sword.sa;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 13. 机器人的运动范围
 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 */
public class T013_2 {

    public int movingCount(int m, int n, int k) {
        if (m <= 0 || n <= 0 || k < 0) {
            return 0;
        }
        boolean[][] visited = new boolean[m][n];
        return movingCountCore(m, n, k, 0, 0, visited);
    }

    private int movingCountCore(int rows, int cols, int threshold, int row, int col, boolean[][] visited) {
        int count = 0;
        if (row >= 0 && row < rows
                && col >= 0 && col < cols
                && !visited[row][col]
                && (calDigitSum(row) + calDigitSum(col) <= threshold)) {
            visited[row][col] = true;
            count = 1 +
                    movingCountCore(rows, cols, threshold, row - 1, col, visited) +
                    movingCountCore(rows, cols, threshold, row + 1, col, visited) +
                    movingCountCore(rows, cols, threshold, row, col - 1, visited) +
                    movingCountCore(rows, cols, threshold, row, col + 1, visited);
        }
        return count;
    }

    public int calDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
    @Test
    public void test() {
        Assert.assertEquals(3, movingCount(2, 3, 1));
        Assert.assertEquals(1, movingCount(3, 1, 0));
        Assert.assertEquals(6, movingCount(3, 2, 17));
    }

}
