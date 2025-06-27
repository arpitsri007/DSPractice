package org.codekart.algo.dynamicProgramming;

import java.util.Arrays;
import java.util.Stack;

public class Paranthesis {

    // leetcode 20 - Valid Parentheses
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' && !isOpeningCharacterFound(stack, '(')) {
                return false;
            } else if (c == ']' && !isOpeningCharacterFound(stack, '[')) {
                return false;
            } else if (c == '}' && !isOpeningCharacterFound(stack, '{')) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    private boolean isOpeningCharacterFound(Stack<Character> stack, char c) {
        if(stack.isEmpty()) {
            return false;
        }
        return stack.pop() == c;
    }


    // leetcode 678 - Valid Parenthesis String - using recursion and the memoization
    public boolean checkValidString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return checkValidString(chars, 0, 0, dp);
    }

    // TC: without memoization - O(3^n)
    // SC: without memoization - O(n)
    // TC: with memoization - O(n^2)
    // SC: with memoization - O(n^2)
    private boolean checkValidString(char[] chars, int index, int open, int[][] dp) {
        if (index == chars.length) {
            return open == 0;
        }

        if (open < 0) {
            return false;
        }

        if (dp[index][open] != -1) {
            return dp[index][open] == 1;
        }

        if (chars[index] == '*') {
            dp[index][open] = (checkValidString(chars, index + 1, open + 1, dp)
                    || checkValidString(chars, index + 1, open - 1, dp)
                    || checkValidString(chars, index + 1, open, dp)) ? 1 : 0;
            return dp[index][open] == 1;
        } else if (chars[index] == '(') {
            dp[index][open] = checkValidString(chars, index + 1, open + 1, dp) ? 1 : 0;
            return dp[index][open] == 1;
        } else if (open > 0) {
            dp[index][open] = checkValidString(chars, index + 1, open - 1, dp) ? 1 : 0;
            return dp[index][open] == 1;
        }

        dp[index][open] = 0;
        return false;
    }
    // Bottom up approach
    // t[i][open] = true/false : string starting from index i having #open no of
    // brackets is valid or not
    // t[i][open] = t[i+1][open+1] || t[i+1][open-1] || t[i+1][open]

    public boolean checkValidStringBottomUp(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n + 1][n + 2];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[n][0] = 1; // empty string is valid

        // t[i][j] : i to n-1 s, having seen j open brackets --> true/false

        for (int i = n - 1; i >= 0; i--) {
            for (int open = 0; open <= n; open++) {
                boolean isValid = false;
                if (chars[i] == '*') {
                    // '*' can be '(' or ')' or ''
                    isValid = isValid || dp[i + 1][open + 1] == 1; // '('
                    if (open > 0) {
                        isValid = isValid || dp[i + 1][open - 1] == 1; // ')'
                    }
                    isValid = isValid || dp[i + 1][open] == 1; // ' '

                } else if (chars[i] == '(') {
                    isValid = isValid || dp[i + 1][open + 1] == 1; // '('
                } else if (open > 0) {
                    isValid = isValid || dp[i + 1][open - 1] == 1; // ')'
                }

                dp[i][open] = isValid ? 1 : 0;
            }
        }

        return dp[0][0] == 1;
    }

    // main function
    public static void main(String[] args) {
        Paranthesis paranthesis = new Paranthesis();
        System.out.println(paranthesis.checkValidStringBottomUp("()"));
       // System.out.println(paranthesis.checkValidStringBottomUp("()"));
    }

}
