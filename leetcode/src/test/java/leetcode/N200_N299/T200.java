package leetcode.N200_N299;

/**
 * 200. 岛屿数量
 * https://leetcode.cn/problems/number-of-islands/
 */
public class T200 {

    public int numIslands(char[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        int islandCount = 0; // 岛屿数量
        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                if (grid[row][col] == '0') { // 0 代表水
                    continue;
                }
                islandCount++; // 发现一个岛屿
                fill(grid, row, col); // 将这个岛屿的所有部分都填充成 0 , 这样就不会重复计算了
            }
        }
        return islandCount;
    }

    void fill(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length
                || col < 0 || col >= grid[0].length
                || grid[row][col] == '0') { // 越界了，或者是水
            return;
        }

        grid[row][col] = '0';  // 将这个岛屿的部分填充成 0

        // 递归填充上下左右
        fill(grid, row - 1, col);
        fill(grid, row + 1, col);
        fill(grid, row, col - 1);
        fill(grid, row, col + 1);
    }

}
