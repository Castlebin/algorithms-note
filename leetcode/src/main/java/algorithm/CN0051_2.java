package algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * N 皇后问题
 * 
 * 打印下 尝试的次数、解数
 */
public class CN0051_2 {

    class Solution {
        private int tryCount = 0;
        private int resultCount = 0;

        public void solveNQueens(int n) {
            for (int i = 1; i < n; i++) {
                tryCount = 0;
                resultCount = 0;

                solve(i);

                System.out.println("N=" + i + ", tryCount=" + tryCount + ", resultCount=" + resultCount);
            }
        }

        public void solve(int n) {
            int[] position = new int[n];
            /** 这里错了 ！！ 好好的 DFS ， 循环是个啥意思？？哎
            for (int i = 0; i < n; i++) {
                choose(i, n, position);
            }*/
            // 从 0 开始 DFS 即可
            choose(0, n, position);
        }

        // 假设 前 i - 1 步已经满足条件，现在在第 i 行，找一列放皇后
        public int choose(int i, int n, int[] position) {
            if (i >= n) {
                resultCount++;
            }
            for (int j = 0; j < n; j++) {
                if (available(i, j, position)) {
                    position[i] = j;

                    int nextChoose = choose(i + 1, n, position);
                    if (nextChoose == -1) {
                        // 第 i + 1 行已经找不到可以放皇后的列了，倒退回第 i 步， j++

                    }
                }
            }
            // 说明此种情况下, 第 i 行，无法找到一个位置放置皇后
            return -1;
        }

        // 一行一行的放皇后，在第 i 行，有 n 种选择
        // 需要与 [0, i - 1] 这些行判断，是否有冲突
        // 判断在第 i 行 j 列，放皇后，是否可以
        public boolean available(int i, int j, int[] position) {
            for (int row = 0; row < i; row++) {
                tryCount++;
                if (j == position[row]
                        || (i - row == j - position[row]
                        || (row - i == j - position[row]))

                ) {
                    return false;
                }
            }
            return true;
        }

    }

    @Test
    public void test() {
        new Solution().solveNQueens(13);
    }

}
