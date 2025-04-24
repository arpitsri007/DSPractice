package org.codekart.algo.dynamicProgramming;

public class Fibonacci {
    
    // Bottom Up Approach
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Top Down Approach
    public static int fibonacciTopDown(int n) {
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];
        return fibonacciTopDown(n, dp);
    }

    private static int fibonacciTopDown(int n, int[] dp) {
        if (n <= 1) {
            return n;
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        dp[n] = fibonacciTopDown(n - 1, dp) + fibonacciTopDown(n - 2, dp);
        return dp[n];
    }
} 