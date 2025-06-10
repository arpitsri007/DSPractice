package org.codekart.trie;

public class TrieNode {
    public TrieNode[] children;
    public boolean isWordCompleted;
    public String word;

    public TrieNode() {
        children = new TrieNode[26];
        isWordCompleted = false;
    }

    public TrieNode(String word) {
        this();
        this.word = word;
    }
}

