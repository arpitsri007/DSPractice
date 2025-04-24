package org.codekart.algo.dynamicProgramming;

import java.util.Arrays;

public class ClimbingStairs {
    
    // Top Down Approach
    public static int climbingStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return climbingStairs(n, dp);
    }

    private static int climbingStairs(int n, int[] dp) {
        if (n < 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        dp[n] = climbingStairs(n - 1, dp) + climbingStairs(n - 2, dp);
        return dp[n];
    }

    // Bottom Up Approach
    public static int climbingStairsBottomUp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];  
        }
        return dp[n];
    }

    // Bottom Up with Space Optimization
    public static int climbingStairsBottomUpSpaceOptimization(int n) {
        int prev2 = 1;
        int prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
} 