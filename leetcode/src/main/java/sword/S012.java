package sword;

import org.junit.Test;

/**
 12. 矩阵中的路径

 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。

 回溯算法，递归
 */
public class S012 {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0
                || board[0] == null || board[0].length == 0
                || word == null) {
            return false;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        int matchedLength = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (hasPath(board, word, row, col, visited, matchedLength)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasPath(char[][] board, String word,
                            int row, int col,
                            boolean[][] visited, int matchedLength) {
        if (matchedLength == word.length()) {
            return true;
        }

        boolean hasPath = false;
        if (row >= 0 && row < board.length
                && col >= 0 && col < board[0].length
                && board[row][col] == word.charAt(matchedLength)
                && !visited[row][col]) {
            matchedLength++;
            visited[row][col] = true;

            hasPath = hasPath(board, word, row - 1, col, visited, matchedLength)
                    || hasPath(board, word, row + 1, col, visited, matchedLength)
                    || hasPath(board, word, row, col - 1, visited, matchedLength)
                    || hasPath(board, word, row, col + 1, visited, matchedLength);

            if (!hasPath) {
                matchedLength--;
                visited[row][col] = false;
            }
        }
        return hasPath;
    }

    @Test
    public void test() {

    }

}
