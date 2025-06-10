package org.codekart.strings;

import java.util.ArrayList;
import java.util.List;

public class KMP {
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
                i = i - j + 1; // i has to start from the next character of the last i position 
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

    // Example of LPS array
    // txt = "ABABABD"
    // pat = "ABABD"
    // lps = [0, 0, 1, 2, 0]
    // lps[0] = 0 because there is no prefix which is also suffix in the pattern[0..0]
    // lps[1] = 0 because there is no prefix which is also suffix in the pattern[0..1]
    // lps[2] = 1 because "A" is a prefix which is also suffix in the pattern[0..2]
    // lps[3] = 2 because "AB" is a prefix which is also suffix in the pattern[0..3]
    // lps[4] = 0 because there is no prefix which is also suffix in the pattern[0..4]

    // Basically prevent moving i backwards and j to 0,
    // instead move j to next position after the longest prefix which is also suffix

    // AS soon as we find a mismatch, we don't move i backwards, 
    // we see string from 0 to j-1 and find the longest prefix which is also suffix
    // now jump j to after that position and continue matching
    // This holds true because since we have reached till j-1 and found a mismatch
    // so if we just start comparing after the position of the longest prefix which is also suffix
    // bcs before that position, we have already matched the string

    public static int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        // lps[0] is always 0 - because there is no prefix which is also suffix in the pattern[0..0]
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

    // Example of KMP algorithm
    // pattern = "aaacaaaa"
    // text = "aaacaaaaacaaaa"
    // lps = [0, 1, 2, 0, 1, 2, 3, 3]



}
