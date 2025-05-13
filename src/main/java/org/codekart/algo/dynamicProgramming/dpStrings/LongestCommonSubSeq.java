package org.codekart.algo.dynamicProgramming.dpStrings;

public class LongestCommonSubSeq {
    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";
        System.out.println(longestCommonSubSeq(s1, s2, 0, 0));
        int[][] memo = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        System.out.println(longestCommonSubSeqMemo(s1, s2, 0, 0, memo));
        System.out.println(longestCommonSubSeqBottomUp(s1, s2));
        System.out.println(longestCommonSubSeqTabReverse(s1, s2));
    }

    public static int longestCommonSubSeq(String s1, String s2, int i, int j) {
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + longestCommonSubSeq(s1, s2, i + 1, j + 1);
        }
        return Math.max(longestCommonSubSeq(s1, s2, i + 1, j), longestCommonSubSeq(s1, s2, i, j + 1));
    }

    // with memoization
    public static int longestCommonSubSeqMemo(String s1, String s2, int i, int j, int[][] memo) {
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = 1 + longestCommonSubSeqMemo(s1, s2, i + 1, j + 1, memo);
        } else {
            memo[i][j] = Math.max(longestCommonSubSeqMemo(s1, s2, i + 1, j, memo), longestCommonSubSeqMemo(s1, s2, i, j + 1, memo));
        }
        return memo[i][j];
    }

    // with tabulation
    // time complexity: O(n*m)
    // space complexity: O(n*m)
    // dp[i][j] = length of longest common subsequence of s1[0..i-1] and s2[0..j-1]
    public static int longestCommonSubSeqBottomUp(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        // base case
        dp[0][0] = 0;

        // base case
        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = 0;
        }

        // base case
        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = 0;
        }

        // fill the dp table
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    // let's fill DP in reverse
    // dp[i][j] = length of longest common subsequence of s1[i..n-1] and s2[j..m-1]
    public static int longestCommonSubSeqTabReverse(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        // base case
        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = 0;
        }

        // base case
        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = 0;
        }

        // fill the dp table
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1]; // index i+1 and j+1 because we are filling the dp table in reverse
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }

    // print the longest common subsequence
    public static void printLongestCommonSubSeq(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        // base case
        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = s1.length();
        int j = s2.length();
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        System.out.println(sb.reverse().toString());
    }

    public static boolean isSubsequence(String parentString, String childString) {
        int i = 0;
        int j = 0;
        while(i < parentString.length() && j < childString.length()) {
            if(parentString.charAt(i) == childString.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == childString.length();
    }
}