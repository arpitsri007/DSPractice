package org.codekart.strings;

public class Algo {
    // String pattern matching
    // KMP algorithm
    // Boyer-Moore algorithm
    // Rabin-Karp algorithm
    // Z algorithm
    // Aho-Corasick algorithm

    // pattern matching using brute force
    public static boolean bruteForce(String pattern, String text) {
        int n = text.length();
        int m = pattern.length();
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        return j == m;
    }

    // pattern matching using KMP algorithm
    // Concept of LPS array and prefix function
    // LPS array is also known as longest prefix suffix array
    // LPS[i] is the length of the longest prefix which is also suffix in the
    // pattern[0..i]
    // LPS array is used to reduce the number of comparisons in the pattern matching
    // LPS array is used to store the index of the next character to compare in the
    // pattern

    public static int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        // lps[0] is always 0
        lps[0] = 0;

        // len holds the length of the current longest prefix suffix
        int len = 0;

        // i is the index we're calculating the LPS value for
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                // If characters match, increment length and store it
                len++;
                lps[i] = len;
                i++;
            } else {
                // If characters don't match
                if (len != 0) {
                    // Look for a shorter prefix that is also a suffix
                    len = lps[len - 1];
                    // Don't increment i here - we're still working on the same position
                } else {
                    // No possible prefix found
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public static boolean kmp(String pattern, String text) {
        int[] lps = computeLPSArray(pattern);
        int i = 0;
        int j = 0;
        while (i < text.length() && j < pattern.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return j == pattern.length();
    }

    // main method
    public static void main(String[] args) {
        String pattern = "ababc";
        String text = "ababcababababc";
        System.out.println(kmp(pattern, text));
    }

}
