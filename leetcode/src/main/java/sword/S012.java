package sword;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 12. 矩阵中的路径

 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。

 回溯算法，递归实现
 */
public class S012 {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0
                || board[0] == null || board[0].length == 0
                || word == null || word.length() == 0) {
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        // 使用一个可变的引用类型，来记录长度，因为需要传递，值类型无法满足
        AtomicInteger marchedLength = new AtomicInteger();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (hasPathCode(board, word, marchedLength, row, col, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasPathCode(char[][] board, String word, AtomicInteger marchedLength,
                                int row, int col, boolean[][] visited) {
        if (word.length() == marchedLength.get()) {
            return true;
        }

        boolean hasPath = false;
        if (row >= 0 && row < board.length
                && col >= 0 && col < board[0].length
                && board[row][col] == word.charAt(marchedLength.get())
                && !visited[row][col]) {
            marchedLength.incrementAndGet();
            visited[row][col] = true;

            // 继续往上下左右分别探索，如果，上下左右的探索都失败，回退此步
            hasPath = hasPathCode(board, word, marchedLength, row - 1, col, visited)
                    || hasPathCode(board, word, marchedLength, row + 1, col, visited)
                    || hasPathCode(board, word, marchedLength, row, col - 1, visited)
                    || hasPathCode(board, word, marchedLength, row, col + 1, visited);

            // 上下左右继续探索，失败了，说明这一步不行，回退
            if (!hasPath) {
                marchedLength.decrementAndGet();
                visited[row][col] = false;
            }
        }

        return hasPath;
    }

    @Test
    public void test() {
        char[][] board = new char[][]{
                {'a','b','c','e'},
                {'s','f','c','s'},
                {'a','d','e','e'}
        };
        String word = "bfce";
        Assert.assertTrue(exist(board, word));
    }

}
