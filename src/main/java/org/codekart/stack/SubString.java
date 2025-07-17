package org.codekart.stack;

import java.util.Stack;

public class SubString {
    // leetcode 1717 - Maximum Score From Removing Substrings
    /*
     * Approach:
     * 1. first delete maximum value substring from s1
     * 2. then delete Minimum value substring from s2
     * 
     * Example: baba
     * Option 1: delete ba the ba --> y + y
     * Option 2: delete ab(middle) the ba --> x + y
     * given that x > y so we will choose option 2
     * 
     */

    public int maximumGain(String s, int x, int y) {

        // Determine which substring to remove first (the more valuable one)
        char first, second;
        int firstValue, secondValue;

        if (x > y) {
            // Remove "ab" first if it's worth more
            first = 'a';
            second = 'b';
            firstValue = x;
            secondValue = y;
        } else {
            // Remove "ba" first if it's worth more
            first = 'b';
            second = 'a';
            firstValue = y;
            secondValue = x;
        }

        // Process string in two passes
        StringBuilder remaining = new StringBuilder();
        int totalPoints = 0;

        // First pass: remove the higher-value substring
        totalPoints += removeConsecutivePair(s, first, second, firstValue, remaining);

        // Second pass: remove the lower-value substring
        String afterFirstPass = remaining.toString();
        remaining = new StringBuilder(); // Reset for second pass
        totalPoints += removeConsecutivePair(afterFirstPass, second, first, secondValue, remaining);

        return totalPoints;

    }

    private int removeConsecutivePair(String s, char first, char second, int points, StringBuilder result) {
        int count = 0;
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == first && c == second) {
                // Found a pair to remove
                stack.pop();
                count++;
            } else {
                stack.push(c);
            }
        }

        // Add remaining characters to result
        for (char c : stack) {
            result.append(c);
        }

        return count * points;
    }

    // leetcode 1717 - Maximum Score From Removing Substrings - using constant space
    public int maximumGainOptimized(String s, int x, int y) {
        int n = s.length();
        int score = 0;

        String maxString = x > y ? "ab" : "ba";
        String minString = x > y ? "ba" : "ab";

        // first pass
        String temp_first = removeSubStringConstantSpace(s, maxString);
        int tLen = temp_first.length();

        score += (n - tLen) / 2 * Math.max(x, y);

        String temp_second = removeSubStringConstantSpace(temp_first, minString);
        int t2Len = temp_second.length();

        score += (tLen - t2Len) / 2 * Math.min(x, y);

        return score;

    }

    /*
     * private String removeSubStringUsingStack(String s, String matchString) {
     * int n = s.length();
     * Stack<Character> stack = new Stack<>();
     * 
     * for (int i = 0; i < n; i++) {
     * if (!stack.isEmpty() && stack.peek() == matchString.charAt(0) && s.charAt(i)
     * == matchString.charAt(1)) {
     * stack.pop();
     * } else {
     * stack.push(s.charAt(i));
     * }
     * }
     * 
     * StringBuilder result = new StringBuilder();
     * while (!stack.isEmpty()) {
     * result.insert(0, stack.pop());
     * }
     * 
     * return result.toString();
     * 
     * }
     */

    private String removeSubStringConstantSpace(String s, String subString) {
        int n = s.length();
        int m = subString.length();
        int i = 0;
        int j = 0;

        char[] arr = s.toCharArray();

        while (j < n) {
            char ch = arr[j];
            arr[i] = ch;
            i++;

            if (i >= m && new String(arr, i - m, m).equals(subString)) {
                i = i - m;
            }
            j++;
        }

        return new String(arr, 0, i);
    }

    // main method
    public static void main(String[] args) {
        SubString subString = new SubString();
        System.out.println(subString.maximumGainOptimized("aabbaaxybbaabb", 5, 4));
    }
}
