package org.codekart.algo.dynamicProgramming;

public class knapsack {

    int mod = 1000000007;
    // leetcode 2787
    public int numberOfWays(int n, int x) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        return solve(n, x, 1, dp);
    }

    private int solve(int n, int x, int num, int[][] dp) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            return 0;
        }

        if (Math.pow(num, x) > n) {
            return 0;
        }

        if (dp[n][num] != -1) {
            return dp[n][num] % mod;
        }

        int pick = solve(n - (int) Math.pow(num, x), x, num + 1, dp);
        int notPick = solve(n, x, num + 1, dp);

        dp[n][num] = pick + notPick;
        return dp[n][num] % mod;
    }
}
