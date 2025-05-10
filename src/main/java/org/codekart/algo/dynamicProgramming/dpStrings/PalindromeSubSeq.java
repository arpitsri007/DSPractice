package org.codekart.algo.dynamicProgramming.dpStrings;

public class PalindromeSubSeq {

    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(longestPalindromeSubSeq(s));
    }

    // recursive solution
    public static int longestPalindromeSubSeq(String s) {
        int[][] memo = new int[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < s.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return longestPalindromeSubSeq(s, 0, s.length() - 1, memo);
    }

    // memo[i][j] = length of longest palindrome subsequence of s[i..j]
    public static int longestPalindromeSubSeq(String s, int i, int j, int[][] memo) {
        if(i > j) {
            return 0;
        }

        if(i == j) {
            return 1;
        }

        if(memo[i][j] != -1) {
            return memo[i][j];
        }

        if(s.charAt(i) == s.charAt(j)) {
            return memo[i][j] = 2 + longestPalindromeSubSeq(s, i+1, j-1, memo);
        }

        return memo[i][j] = Math.max(longestPalindromeSubSeq(s, i+1, j, memo), longestPalindromeSubSeq(s, i, j-1, memo));
        
    }

    // using LCS
    public int longestPalindromeSubseqUsingLCSRecursion(String s1) {
        String s2= new StringBuilder(s1).reverse().toString();
         int[][] memo = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return LongestCommonSubSeq.longestCommonSubSeqMemo(s1, s2, 0, 0, memo);
        
    }



    public static int longestPalindromeSubSeqUsingLCSTabulation(String s1) {
        String s2= new StringBuilder(s1).reverse().toString();
        int[][] dp = new int[s1.length()][s2.length()];
        for(int i = 0; i < s1.length(); i++) {
            for(int j = 0; j < s2.length(); j++) {
                dp[i][j] = -1;
            }
        }
        return LongestCommonSubSeq.longestCommonSubSeqBottomUp(s1, s2);
    }

    // BluePrint Approach
    // dp[i][j] = length of longest palindrome subsequence of s[i..j]
    public static int longestPalindromeSubSeqUsingBluePrint(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for(int len = 2; len <= n; len++) {
            for(int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
        
    
}