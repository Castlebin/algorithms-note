package algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * N 皇后问题，一遍解出！
 * 但是，为什么会重复打印一个解多次？
 */
public class CN0051 {

    class Solution {
        public List<List<String>> solveNQueens(int n) {
            slove(n);
            return null;
        }

        public void slove(int n) {
            int[] position = new int[n];
            for (int i = 0; i < n; i++) {
                choose(i, n, position);
            }

        }

        // 假设 前 i - 1 步已经满足条件，现在在第 i 行，找一列放皇后
        public int choose(int i, int n, int[] position) {
            if (i >= n) {
                // 找到一个解了，可以想办法返回
                System.out.println(Arrays.toString(position));
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
            return -1;
        }

        // 一行一行的放皇后，在第 i 行，有 n 种选择
        // 需要与 [0, i - 1] 这些行判断，是否有冲突
        // 判断在第 i 行 j 列，放皇后，是否可以
        public boolean available(int i, int j, int[] position) {
            for (int row = 0; row < i; row++) {
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
        new Solution().solveNQueens(5);
    }

}
