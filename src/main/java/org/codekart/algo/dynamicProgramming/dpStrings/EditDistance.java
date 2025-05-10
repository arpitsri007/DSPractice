package org.codekart.algo.dynamicProgramming.dpStrings;

import java.util.Arrays;

public class EditDistance {

    // main method to calculate edit distance
    public static void main(String[] args) {
        // String s1 = "horse";
        // String s2 = "ros";
        // System.out.println(editDistance(s1, s2));
        String minInsertions = "mbadm";
        System.out.println(minInsertions(minInsertions));
        System.out.println(minInsertionsBottomUp(minInsertions));
    }

    // edit distance function using recursion - convert s1 to s2
    public static int editDistance(String s1, String s2) {
        return editDistance(s1, s2, 0, 0);
    }

    // edit distance function using recursion
    public static int editDistance(String s1, String s2, int i, int j) {
        if (i == s1.length()) {
            return s2.length() - j; // now add
        }
        if (j == s2.length()) {
            return s1.length() - i; // now delete
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return editDistance(s1, s2, i + 1, j + 1);
        }

        int insert = editDistance(s1, s2, i, j + 1);
        int delete = editDistance(s1, s2, i + 1, j);
        int replace = editDistance(s1, s2, i + 1, j + 1);
        return 1 + Math.min(insert, Math.min(delete, replace));
    }

    public static int editDistanceReverse(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                dp[i][j] = -1;
            }
        }
        return editDistanceReverse(s1, s2, s1.length(), s2.length(), dp);
    }

    public static int editDistanceReverse(String s1, String s2, int i, int j, int[][] dp) {
        if (i == 0) {
            return j; // now add
        }
        if (j == 0) {
            return i; // now delete
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            return editDistanceReverse(s1, s2, i - 1, j - 1, dp);
        }

        int insert = editDistanceReverse(s1, s2, i, j - 1, dp);
        int delete = editDistanceReverse(s1, s2, i - 1, j, dp);
        int replace = editDistanceReverse(s1, s2, i - 1, j - 1, dp);
        dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
        return dp[i][j];
    }

    // bottom up approach
    // dp[i][j] = minimum opetations to convert s1 of length i and s2 of length j to
    // be same
    public static int editDistanceBottomUp(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = i + j; // now add
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static int minInsertions(String s) {

        int[][] temp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(temp[i], -1);
        }
        return solve(s, 0, s.length() - 1, temp);
    }

    public static int solve(String s, int i, int j, int[][] temp) {
        if (i > j)
            return 0;

        if (temp[i][j] != -1) {
            return temp[i][j];
        }

        if (s.charAt(i) == s.charAt(j)) {
            return temp[i][j] = solve(s, i + 1, j - 1, temp);
        }

        return temp[i][j] = 1 + Math.min(solve(s, i + 1, j, temp), solve(s, i, j - 1, temp));

    }

    public static int minInsertionsBottomUp(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for(int i=0;i<s.length();i++) {
            dp[i][i] = 0;
        }

        //  for(int i=0;i<s.length()-1;i++) {
        //     if(s.charAt(i) == s.charAt(i+1)) {
        //         dp[i][i+1] = 0;
        //     } else {
        //         dp[i][i+1] = 1;
        //     }
        // }

        for(int l = 2; l <= s.length(); l++) {
            for(int i = 0; i + l - 1 < s.length() ; i++) {
                int j = i + l - 1;
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1];
                } else {
                    dp[i][j] = 1+ Math.min(dp[i+1][j], dp[i][j-1]);
                }

            }
        }
        // print dp array
        for(int i=0;i<s.length();i++) {
            for(int j=0;j<s.length();j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[0][s.length()-1]; 
    }

}