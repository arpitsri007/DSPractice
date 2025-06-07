package org.codekart.recursion;

public class WordSearch {

    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    // leetcode 79
    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == word.charAt(0) && existHelper(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }

    private static boolean existHelper(char[][] board, String word, int i, int j, int index) {
        if(index == word.length()) return true;
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) return false;

        char temp = board[i][j];
        board[i][j] = '#';

        for(int[] direction : directions) {
            int newI = i + direction[0];
            int newJ = j + direction[1];
            if(existHelper(board, word, newI, newJ, index + 1)) return true;
        }
        board[i][j] = temp;
        return false;
    }
}
