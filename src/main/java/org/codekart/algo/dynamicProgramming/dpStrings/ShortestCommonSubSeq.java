package org.codekart.algo.dynamicProgramming.dpStrings;

public class ShortestCommonSubSeq {
    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";
        int[][] memo = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        System.out.println(shortestCommonSubSeq(s1, s2, 0, 0));
        System.out.println(shortestCommonSubSeq2(s1, s2, s1.length(), s2.length(), memo));

    }

    public static int shortestCommonSubSeq(String s1, String s2, int i, int j) {
        // base case - if we have reached the end of either string, return remaining
        // length of the other string
        if (i == s1.length() || j == s2.length()) {
            return s1.length() + s2.length() - i - j;
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + shortestCommonSubSeq(s1, s2, i + 1, j + 1);
        }
        return 1 + Math.min(shortestCommonSubSeq(s1, s2, i + 1, j), shortestCommonSubSeq(s1, s2, i, j + 1));
    }

    public static int shortestCommonSubSeq2(String s1, String s2, int m, int n, int[][] memo) {
        if (m == 0 || n == 0) {
            return m + n;
        }

        if (memo[m][n] != -1) {
            return memo[m][n];
        }

        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return memo[m][n] = 1 + shortestCommonSubSeq2(s1, s2, m - 1, n - 1, memo);
        }

        return memo[m][n] = 1 + Math.min(shortestCommonSubSeq2(s1, s2, m - 1, n, memo),
                shortestCommonSubSeq2(s1, s2, m, n - 1, memo));

    }

    // dp solution
    // dp[i][j] = shortestCommonSubSeq(s1, s2, i, j) - shortest sub sequence of s1
    // having length i and s2 having length j

    public static int shortestCommonSubSeq3(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = i + j;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    // print the shortest common subsequence
    public static void printShortestCommonSubSeq(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for(int i = 0; i <= s1.length(); i++) {
            for(int j = 0; j <= s2.length(); j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = i + j;
                } else if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int i = s1.length();
        int j = s2.length();
        StringBuilder sb = new StringBuilder();
        while(i > 0 && j > 0) {
            if(s1.charAt(i-1) == s2.charAt(j-1)) {
                sb.append(s1.charAt(i-1));
                i--;
                j--;
            } else if(dp[i-1][j] < dp[i][j-1]) {
                sb.append(s1.charAt(i-1));
                i--;
            } else {
                sb.append(s2.charAt(j-1));
                j--;
            }
        }

        while(i > 0) {
            sb.append(s1.charAt(i-1));
            i--;
        }
        while(j > 0) {
            sb.append(s2.charAt(j-1));
            j--;
        }
        System.out.println(sb.reverse().toString());
    }
}
