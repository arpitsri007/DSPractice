package org.codekart.algo.dynamicProgramming.dpGrid;

public class MaximumFruits {
    // leetcode 3363
    // https://leetcode.com/problems/find-the-maximum-number-of-fruits-collected/
    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        int c1 = child1Fruits(fruits, dp);
        int c2 = child2Fruits(0, n - 1, fruits, dp);
        int c3 = child3Fruits(n - 1, 0, fruits, dp);

        return c1 + c2 + c3;
    }

    private int child1Fruits(int[][] fruits, int[][] dp) {
        int n = fruits.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += fruits[i][i];
        }
        return result;
    }

    private int child2Fruits(int i, int j, int[][] fruits, int[][] dp) {
        int n = fruits.length;
        if (j < 0 || i >= n || j >= n) {
            return 0;
        }

        if (i == j || i > j) {
            return 0;
        }

        if (i == n - 1 && j == n - 1) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int bottomLeft = child2Fruits(i + 1, j - 1, fruits, dp);
        int bottomRight = child2Fruits(i + 1, j + 1, fruits, dp);
        int bottomDown = child2Fruits(i + 1, j, fruits, dp);

        dp[i][j] = fruits[i][j] + Math.max(bottomLeft, Math.max(bottomRight, bottomDown));
        return dp[i][j];
    }

    private int child3Fruits(int i, int j, int[][] fruits, int[][] dp) {
        int n = fruits.length;
        if (i < 0 || j < 0 || i >= n || j >= n) {
            return 0;
        }

        if (i == j || i < j) {
            return 0;
        }

        if (i == n - 1 && j == n - 1) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int topRight = child3Fruits(i - 1, j + 1, fruits, dp);
        int rightCell = child3Fruits(i, j + 1, fruits, dp);
        int bottomRight = child3Fruits(i + 1, j + 1, fruits, dp);

        dp[i][j] = fruits[i][j] + Math.max(topRight, Math.max(rightCell, bottomRight));
        return dp[i][j];
    }

    /**
     * Bottom up approach -
     * 1. 2D dp array as only two parameters are changing i and j
     * 2. t[i][j] = maximum fruits collected when reached (i, j) from source.
     */

    public int maxCollectedFruitsBottomUp(int[][] fruits) {
        int n = fruits.length;
        int[][] dp = new int[n][n];

        int result = 0;

        // child 1
        for (int i = 0; i < n; i++) {
            result += fruits[i][i];
        }

        // initialize dp array
        // child 2 won't be able to visit cells where i + j < n -1
        // child 3 won't be able to visit cells where i + j < n -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j && i + j < n - 1) {
                    dp[i][j] = 0;
                } else if (i > j && i + j < n - 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = fruits[i][j];
                }
            }
        }

        // child 2
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] += Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], j + 1 < n ? dp[i - 1][j + 1] : 0));
            }
        }

        // child 3
        for (int j = 1; j < n; j++) {
            for (int i = j + 1; i < n; i++) {
                dp[i][j] += Math.max(i + 1 < n ? dp[i + 1][j - 1] : 0, Math.max(dp[i][j - 1], dp[i - 1][j - 1]));
            }
        }

        result += dp[n - 2][n - 1] + dp[n - 1][n - 2];

        return result;
    }
}
