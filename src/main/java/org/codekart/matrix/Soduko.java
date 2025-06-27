package org.codekart.matrix;

public class Soduko {
    // leetcode 36 - Valid Sudoku
    public boolean isValidSudoku(char[][] board) {
        // check each row
        for (int i = 0; i < 9; i++) {
            if (!isValidRow(board, i)) {
                return false;
            }
            if (!isValidColumn(board, i)) {
                return false;
            }
        }
        // check each 3x3 sub-grid
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!isValidSubGrid(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidRow(char[][] board, int row) {
        boolean[] seen = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != '.') {
                if (seen[board[row][i] - '1']) {
                    return false;
                }
                seen[board[row][i] - '1'] = true;
            }
        }
        return true;
    }

    private boolean isValidColumn(char[][] board, int column) {
        boolean[] seen = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (board[i][column] != '.') {
                if (seen[board[i][column] - '1']) {
                    return false;
                }
                seen[board[i][column] - '1'] = true;
            }
        }
        return true;
    }

    private boolean isValidSubGrid(char[][] board, int startRow, int startColumn) {
        boolean[] seen = new boolean[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startColumn + j] != '.') {
                    if (seen[board[startRow + i][startColumn + j] - '1']) {
                        return false;
                    }
                    seen[board[startRow + i][startColumn + j] - '1'] = true;
                }
            }
        }
        return true;
    }
    
    
    
}
