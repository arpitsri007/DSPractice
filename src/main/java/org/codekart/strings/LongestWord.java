package org.codekart.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestWord {
    // leetcode 524 - Longest Word in Dictionary through Deleting
    /*
     * Given a string s and a string array dictionary, return the longest string in
     * the dictionary that can be formed by deleting some of the characters of the
     * given string without changing its order. If there is more than one possible
     * result, return the longest word with the smallest lexicographical order. If
     * no possible result, return the empty string.
     */

    /*
     * Intuition:
     * 1. Check if the word is a subsequence of the given string
     */

    public String findLongestWord(String s, List<String> dictionary) {
        String result = "";
        for (String word : dictionary) {
            if (isSubsequence(word, s)) {
                if (word.length() > result.length()
                        || (word.length() == result.length() && word.compareTo(result) < 0)) {
                    result = word;
                }
            }
        }
        return result;
    }

    private boolean isSubsequence(String word, String s) {
        int i = 0;
        int j = 0;

        while (i < word.length() && j < s.length()) {
            if (word.charAt(i) == s.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == word.length();
    }

    // Approach2: use comparator
    public String findLongestWordUsingComparator(String s, List<String> dictionary) {
        // sort the dictionary by length in descending order and lexicographical order
        Collections.sort(dictionary, (a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        });

        for (String word : dictionary) {
            if (isSubsequence(word, s)) {
                return word;
            }
        }
        return "";
    }

    // leetcode 792 - Number of Matching Subsequences
    /*
     * Given a string s and a dictionary of words words, return the number of words[i] that is a subsequence of s.
     * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
     * For example, "ace" is a subsequence of "abcde".
     */

    public int numMatchingSubseq(String s, String[] words) {
        int count = 0;
        for (String word : words) {
            if (isSubsequence(word, s)) {
                count++;
            }
        }
        return count;
    }

    // Optimized approach using map and binary search
    public int numMatchingSubseqUsingMapAndBinarySearch(String s, String[] words) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(s.charAt(i), k -> new ArrayList<>()).add(i);
        }
        int count = 0;
        for (String word : words) {
            if (isSubsequenceOptimised(word, map)) {
                count++;
            }
        }
        return count;
    }

    private boolean isSubsequenceOptimised(String word, Map<Character, List<Integer>> charPositions) {
        int lastUsedIndex = -1;
    
        // For each character in the word, find its next occurrence after lastUsedIndex
        for (char c : word.toCharArray()) {
            if (!charPositions.containsKey(c)) {
                return false; // Character doesn't exist in string s
            }
            
            List<Integer> positions = charPositions.get(c);
            int nextIndex = findNextPosition(positions, lastUsedIndex);
            
            if (nextIndex == -1) {
                return false; // No valid position found
            }
            
            lastUsedIndex = nextIndex;
        }
        
        return true;
    }

    private int findNextPosition(List<Integer> positions, int lastUsedIndex) {
        int left = 0;
        int right = positions.size() - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (positions.get(mid) > lastUsedIndex) {
                result = positions.get(mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

}
