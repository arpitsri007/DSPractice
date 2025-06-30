package org.codekart.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch {

    static int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static int[][] directions2 = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
            { -1, -1 } };

    // leetcode 79
    public static void main(String[] args) {
        char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
                { 'i', 'f', 'l', 'v' } };
        String[] words = { "oath", "pea", "eat", "rain" };
        System.out.println(findWords2(board, words));

    }

    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) { // TC: O(m*n)
                if (board[i][j] == word.charAt(0) && existHelper(board, word, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    /*
     * Calculate the overall complexity
     * For each of the m×n starting points, the DFS can explore up to 4×3^(k-1)
     * paths, which simplifies to O(3^k).
     * Therefore, the overall time complexity is:
     * O(m×n × 3^k)
     */
    /*
     * The space complexity is O(k) for the recursion stack, as the maximum depth of
     * recursion is the length of the word we're searching for.
     */
    private static boolean existHelper(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) // TC: O(w)
            return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) // O(1)
            return false;

        char temp = board[i][j];
        board[i][j] = '#';

        boolean found = false;
        for (int[] direction : directions) {
            int newI = i + direction[0];
            int newJ = j + direction[1];
            if (existHelper(board, word, newI, newJ, index + 1)) { // TC: O(w)
                found = true;
                break;
            }
        }

        // Always restore the original character
        board[i][j] = temp;
        return found;
    }

    // 212. Word Search II -
    // Using Depth First Search -
    /*
     * Breaking it down step by step:
     * 
     * Outer loop iterations: We have n×m starting positions, where n is the number
     * of rows and m is the number of columns.
     * DFS exploration: From each starting position, the DFS can potentially explore
     * the entire board.
     * Word construction and checking: For each path in the DFS:
     * 
     * We append/remove characters from a StringBuilder (O(1) operation)
     * We check if the current string is in the set (O(L) where L is the average
     * word length)
     * We possibly add it to the result list (O(L) for copying)
     * 
     * 
     * Branching factor: At each step, we have up to 4 directions to explore.
     * 
     * The worst-case time complexity is O(n×m×4^L), where:
     * 
     * n×m is the board size
     * 4 is the branching factor (4 directions)
     * L is the maximum possible word length
     * 
     * This is because for each of the n×m starting positions, we might explore up
     * to 4^L different paths in the board.
     * 
     * 
     * Space Complexity Analysis
     * 
     * Visited array: O(n×m) for the boolean array
     * Recursion stack: O(L) where L is the maximum DFS depth (limited by the
     * longest word)
     * StringBuilder: O(L) to store the current word being constructed
     * Set and Result list: O(total characters in all words) for storing the
     * dictionary and results
     * 
     * Therefore, the overall space complexity is O(n×m + L + total chars in words).
     * 
     */

    public List<String> findWords(char[][] board, String[] words) {
        int n = board.length;
        int m = board[0].length;
        List<String> result = new ArrayList<>();

        Set<String> set = new HashSet<>(Arrays.asList(words));

        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                StringBuilder sb = new StringBuilder();
                dfs(board, set, visited, i, j, sb, result);
            }
        }
        return result;

    }

    private static void dfs(char[][] board, Set<String> set, boolean[][] visited, int i, int j, StringBuilder sb,
            List<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j])
            return;

        // try
        visited[i][j] = true;

        sb.append(board[i][j]);

        if (set.contains(sb.toString())) {
            result.add(sb.toString());
            set.remove(sb.toString());
        }

        // explore all directions
        for (int[] direction : directions2) {
            int newI = i + direction[0];
            int newJ = j + direction[1];
            dfs(board, set, visited, newI, newJ, sb, result);
        }
        // backtrack and unmark the current cell as visited
        visited[i][j] = false;

        // remove the last character from the string builder
        sb.deleteCharAt(sb.length() - 1);
    }

    // Word Search II - Using search each word in the board
    // TC - O(n * m * w) - n*m cells and w words
    // SC - O(w) - visited array
    public static List<String> findWords2(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        // search each word in the board
        for (String word : words) {
            if (exist(board, word))
                result.add(word);
        }
        return result;
    }

    // Word Search II - Using Trie

}
