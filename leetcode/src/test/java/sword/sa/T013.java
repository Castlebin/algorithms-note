package sword.sa;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 剑指 Offer 13. 机器人的运动范围
 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 */
public class T013 {

    public int movingCount(int m, int n, int k) {
        if (m == 0 || n == 0 || k < 0) {
            return 0;
        }
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        List<int[]> queue = new ArrayList<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            List<int[]> newQueue = new ArrayList<>();
            for (int[] pos : queue) {
                int row = pos[0], col = pos[1];
                if (visited[row][col] || calDigitSum(row) + calDigitSum(col) > k) {
                    continue;
                }
                visited[row][col] = true;
                count++;
                // 找到相邻的的位置
                if (row + 1 < m && !visited[row + 1][col]) {
                    newQueue.add(new int[]{row + 1, col});
                }
                if (row - 1 >= 0 && !visited[row - 1][col]) {
                    newQueue.add(new int[]{row - 1, col});
                }
                if (col + 1 < n && !visited[row][col + 1]) {
                    newQueue.add(new int[]{row, col + 1});
                }
                if (col - 1 >= 0 && !visited[row][col - 1]) {
                    newQueue.add(new int[]{row, col - 1});
                }
            }
            queue = newQueue;
        }
        return count;
    }

    private int calDigitSum(int num) {
        int res = 0;
        while (num > 0) {
            int mod = num % 10;
            num = num / 10;
            res += mod;
        }
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, movingCount(2, 3, 1));
        Assert.assertEquals(1, movingCount(3, 1, 0));
        Assert.assertEquals(6, movingCount(3, 2, 17));
    }

}
