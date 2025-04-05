package org.codekart.Trie;

public class TrieNode {
    public TrieNode[] children;
    public boolean isWordCompleted;

    public TrieNode() {
        children = new TrieNode[26];
        isWordCompleted = false;
    }
}

