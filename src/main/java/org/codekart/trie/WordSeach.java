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

        for (String word : words) {
            trie.insert(word);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];

                if (trie.root.children[c - 'a'] != null) {
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

        for (int[] direction : directions) {
            int newI = i + direction[0];
            int newJ = j + direction[1];
            dfs(board, node, newI, newJ, result);
        }

        board[i][j] = temp;

    }
}
