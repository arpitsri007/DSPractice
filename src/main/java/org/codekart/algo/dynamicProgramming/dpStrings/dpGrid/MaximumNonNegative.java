package org.codekart.algo.dynamicProgramming.dpStrings.dpGrid;

import util.Pair;

public class MaximumNonNegative {

    public int maxProductPath(int[][] grid) {
        int mod = 1000000007;
        Pair<Long, Long>[][] dp = new Pair[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                dp[i][j] = new Pair<>(Long.MIN_VALUE, Long.MAX_VALUE);
            }
        }

        Pair<Long, Long> result = maxProductPathHelper(0, 0, grid.length, grid[0].length, grid, dp);
        return (int) (result.getFirst() % mod) < 0 ? -1 : (int) (result.getFirst() % mod);
    }

    public Pair<Long, Long> maxProductPathHelper(int i, int j, int m, int n, int[][] grid, Pair<Long, Long>[][] dp) {
        if (i == m - 1 && j == n - 1) {
            return new Pair<>((long) grid[i][j], (long) grid[i][j]);
        }

        long maxValue = Long.MIN_VALUE;
        long minValue = Long.MAX_VALUE;

        Pair<Long, Long> right = null;
        Pair<Long, Long> down = null;

        // Add memoization
        if (dp[i][j].getFirst() != Long.MIN_VALUE && dp[i][j].getSecond() != Long.MAX_VALUE) {
            return dp[i][j];
        }

        if (j + 1 < n) {
            right = maxProductPathHelper(i, j + 1, m, n, grid, dp);
            maxValue = Math.max(maxValue, Math.max(grid[i][j] * right.getFirst(), grid[i][j] * right.getSecond()));
            minValue = Math.min(minValue, Math.min(grid[i][j] * right.getFirst(), grid[i][j] * right.getSecond()));
        }

        if (i + 1 < m) {
            down = maxProductPathHelper(i + 1, j, m, n, grid, dp);
            maxValue = Math.max(maxValue, Math.max(grid[i][j] * down.getFirst(), grid[i][j] * down.getSecond()));
            minValue = Math.min(minValue, Math.min(grid[i][j] * down.getFirst(), grid[i][j] * down.getSecond()));
        }

        dp[i][j] = new Pair<>(maxValue, minValue);
        return dp[i][j];
    }

    // bottom up approach maxProductPath
    public int maxProductPathBottomUp(int[][] grid) {
        int mod = 1000000007;
        int m = grid.length;
        int n = grid[0].length;
        Pair<Long, Long>[][] dp = new Pair[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = new Pair<>(Long.MIN_VALUE, Long.MAX_VALUE);
            }
        }

        // dp[i][j] : maxProductPath from (0,0) to (i,j)
        // dp[i][j].first : maxProductPath from (0,0) to (i,j)
        // dp[i][j].second : minProductPath from (0,0) to (i,j)

        dp[0][0] = new Pair<>((long)grid[0][0], (long)grid[0][0]);

        for(int i = 1; i < m; i++) {
            dp[i][0] = new Pair<>(dp[i-1][0].getFirst() * grid[i][0], dp[i-1][0].getSecond() * grid[i][0]);
        }

        for(int j = 1; j < n; j++) {
            dp[0][j] = new Pair<>(dp[0][j-1].getFirst() * grid[0][j], dp[0][j-1].getSecond() * grid[0][j]);
        }

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                long upMax = dp[i-1][j].getFirst() * grid[i][j];
                long upMin = dp[i-1][j].getSecond() * grid[i][j];
                long leftMax = dp[i][j-1].getFirst() * grid[i][j];
                long leftMin = dp[i][j-1].getSecond() * grid[i][j];

                long currentMax = Math.max(Math.max(upMax, upMin), Math.max(leftMax, leftMin));
                long currentMin = Math.min(Math.min(upMax, upMin), Math.min(leftMax, leftMin));

                dp[i][j] = new Pair<>(currentMax, currentMin);
            }
        }

        return (int) (dp[m-1][n-1].getFirst() % mod) < 0 ? -1 : (int) (dp[m-1][n-1].getFirst() % mod);
        
     }

    public static int minumumPathSumRecursion(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return minumumPathSumRecursionHelper(0, 0, grid.length, grid[0].length, grid, dp);
    }

    public static int minumumPathSumRecursionHelper(int i, int j, int m, int n, int[][] grid, int[][] dp) {
        if (i == m - 1 && j == n - 1) {
            return grid[i][j];
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int right = Integer.MAX_VALUE;
        int down = Integer.MAX_VALUE;

        if (j + 1 < n) {
            right = minumumPathSumRecursionHelper(i, j + 1, m, n, grid, dp);
        }

        if (i + 1 < m) {
            down = minumumPathSumRecursionHelper(i + 1, j, m, n, grid, dp);
        }

        dp[i][j] = grid[i][j] + Math.min(right, down);
        return dp[i][j];
    }

}
