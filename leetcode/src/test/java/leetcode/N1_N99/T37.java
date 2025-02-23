package leetcode.N1_N99;

import org.junit.Test;

/**
 * 37. 解数独
 * https://leetcode-cn.com/problems/sudoku-solver/
 */
public class T37 {
    boolean success;
    public void solveSudoku(char[][] board) {
        backtrace(board, 0, 0);
    }
    void backtrace(char[][] board, int row, int col) {
        if (success) {
            return;
        }
        int N = 9;
        if (row == N) {
            success = true;
            return;
        }
        if (col == N) {
            backtrace(board, row + 1, 0);
            return;
        }
        if (board[row][col] != '.') {
            backtrace(board, row, col + 1);
            return;
        }
        for (char num = '1'; num <= '9'; num++) {
            if (!isValid(board, row, col, num)) {
                continue;
            }
            board[row][col] = num;
            backtrace(board, row, col + 1);
            // 成功一次，就不用再回溯了（不用再往下算）
            if (success) {
                return;
            }
            // 回溯
            board[row][col] = '.';
        }
    }

    // 判断在 board[row][col] 位置放置数字 num 是否合适
    boolean isValid(char[][] board, int row, int col, char num) {
        // 1. 判断行
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        // 2. 判断列
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // 3. 判断 [row][col] 所在的 3x3 宫格
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        char[][] board = new char[][]{
            new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'},
        };
        solveSudoku(board);
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
