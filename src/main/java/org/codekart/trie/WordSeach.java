package org.codekart.trie;

import java.util.ArrayList;
import java.util.List;

public class WordSeach {

    private static Trie trie;

    public WordSeach() {
        trie = new Trie();
    }

    static int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    // main method - word Search II
    public static void main(String[] args) {
        char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
                { 'i', 'f', 'l', 'v' } };
        String[] words = { "oath", "pea", "eat", "rain" };
        System.out.println(findWords(board, words));
    }

    // word Search II - Using Trie
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        for (String word : words) { // TC: O(w) - w is the number of words
            trie.insert(word); // TC: O(L) - L is the length of the word
        }

        for (int i = 0; i < board.length; i++) { // TC: O(m) - m is the number of rows
            for (int j = 0; j < board[0].length; j++) { // TC: O(n) - n is the number of columns
                char c = board[i][j]; // TC: O(1)

                if (trie.root.children[c - 'a'] != null) { // TC: O(1)
                    dfs(board, trie.root, i, j, result);
                }
            }
        }
        return result;
    }

    private static void dfs(char[][] board, TrieNode root, int i, int j, List<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }

        char c = board[i][j];
        if (board[i][j] == '#' || root.children[c - 'a'] == null) {
            return;
        }

        TrieNode node = root.children[c - 'a'];

        if (node.isWordCompleted) {
            result.add(node.word);
            node.isWordCompleted = false;
        }

        char temp = board[i][j];
        board[i][j] = '#';

        for (int[] direction : directions) { // TC: O(4^L) - L is the length of the word
            int newI = i + direction[0];
            int newJ = j + direction[1];
            dfs(board, node, newI, newJ, result);
        }

        board[i][j] = temp;

    }

    /*
     * Time and Space Complexity Analysis
     * 
     * Time Complexity:
     * Building the Trie:
     * 
     * We iterate through each word in the input array
     * For each word of average length L, we perform L insertions into the Trie
     * Each insertion is an O(1) operation
     * 
     * Therefore, building the Trie takes O(W × L) time, where:
     * 
     * W is the number of words
     * L is the average word length
     * 
     * DFS exploration:
     * Every cell in the board contains a valid prefix
     * We need to explore in all 4 directions at each step
     * The maximum word length is L
     * 
     * For each cell, the maximum depth of DFS is L (the longest word length), and
     * at each step, we explore up to 4 directions. This gives us a complexity of
     * O(4^L) for each starting cell.
     * 
     * 
     * Total DFS complexity: O(n×m×4^L) in the worst case, but much better in
     * practice due to the Trie pruning.
     * 
     * Space Complexity:
     * Trie construction: O(w * L)
     * DFS recursion stack: O(L)
     * 
     * 
     * 
     */
}
