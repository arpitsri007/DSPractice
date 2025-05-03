package org.codekart.algo.maths;

import java.util.Arrays;

public class Basics {

    // 2 Keys Keyboard
    public int minSteps(int n) {
        if (n == 1)
            return 0;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        return 1 + helper(1, 1, n, dp);

    }

    protected int helper(int current, int copy, int n, int[][] dp) {
        if (current > n || copy > n)
            return 100000;
        if (current == n)
            return 0;
        if (dp[current][copy] != -1)
            return dp[current][copy];

        int copyPaste = 1 + helper(current + current, current, n, dp);
        int paste = 1 + helper(current + copy, copy, n, dp);
        return dp[current][copy] = Math.min(copyPaste, paste);
    }

    // bottom up approach
    // dp[i] = min op to print i length character
    public int minStepsBottomUp(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 100000);
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 2;
        for(int i = 3; i <= n; i++) {
            int factor = i/2;
            while(factor >= 1) {
                if(i % factor == 0) {
                    int stepFactor = dp[factor];
                    int copy  = 1;
                    int paste = i/factor - 1;
                    dp[i] = 1 + stepFactor + copy + paste;
                    break;
                }
                factor--;
            }
        }
        return dp[n];
    }
    

}
