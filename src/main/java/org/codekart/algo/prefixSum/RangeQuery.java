package org.codekart.algo.prefixSum;

public class RangeQuery {
    // leetcode 2559 - Count Vowel Strings in Ranges
    // prefix[i] = number of vowel strings in words[0] to words[i-1]
    public int[] vowelStrings(String[] words, int[][] queries) {
        int[] result = new int[queries.length];
        int[] prefixSum = new int[words.length + 1];
        for (int i = 0; i < words.length; i++) {
            if(isVowel(words[i].charAt(0)) && isVowel(words[i].charAt(words[i].length() - 1))) {
                prefixSum[i + 1] = prefixSum[i] + 1;
            } else {
                prefixSum[i + 1] = prefixSum[i];
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            result[i] = prefixSum[right + 1] - prefixSum[left];
        }
        return result;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
