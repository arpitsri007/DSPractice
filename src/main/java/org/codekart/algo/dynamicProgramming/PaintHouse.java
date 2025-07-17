package org.codekart.algo.dynamicProgramming;

import java.util.Arrays;

public class PaintHouse {
    // leetcode 256 - Paint House
    /*
     * There is a row of n houses, where each house can be painted one of three
     * colors: red, blue, or green. The cost of painting each house with a certain
     * color is different. You have to paint all the houses such that no two
     * adjacent houses have the same color.
     * 
     * The cost of painting each house with a certain color is represented by an n x
     * 3 cost matrix costs.
     * 
     * For example, costs[0][0] is the cost of painting house 0 with the color red;
     * costs[1][2] is the cost of painting house 1 with color green, and so on...
     * Return the minimum cost to paint all houses.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
     * Output: 10
     * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2
     * into blue.
     * Minimum cost: 2 + 5 + 3 = 10.
     * Example 2:
     * 
     * Input: costs = [[7,6,2]]
     * Output: 2
     */

    public int minCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < n; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        return Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
    }

    // Painting the walls - recursion with memoization - leetcode 2742

    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        long[][] dp = new long[n][n + 1]; 
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }
        return (int) paintWallsHelper(cost, time, 0, n, dp);
    }

    private long paintWallsHelper(int[] cost, int[] time, int index, int remaining, long[][] dp) {
        if (remaining <= 0) {
            return 0;
        }
        if (index == cost.length) {
            return Long.MAX_VALUE;
        }
        if (dp[index][remaining] != -1) {
            return dp[index][remaining];
        }

        long paintCurrentWall = cost[index] + paintWallsHelper(cost, time, index + 1, remaining - time[index] - 1, dp);
        long skipCurrentWall = paintWallsHelper(cost, time, index + 1, remaining, dp);

        dp[index][remaining] = Math.min(paintCurrentWall, skipCurrentWall);
        return dp[index][remaining];
    }




}
