package org.codekart.algo.dynamicProgramming;

import java.util.Arrays;

public class LongestIncreasingSubSequence {

    // driver code
    public static void main(String[] args) {
        int[] arr = { 10, 9, 2, 5, 3, 7, 101, 18 };
        System.out.println(longestIncreasingSubSequence(arr));
    }

    // top down approach
    public static int longestIncreasingSubSequence(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        return longestIncreasingSubSequence(arr, 0, -1, dp);
    }

    public static int longestIncreasingSubSequence(int[] arr, int index, int prevIndex, int[][] dp) {
        if (index == arr.length) {
            return 0;
        }
        if (prevIndex != -1 && dp[index][prevIndex] != 0) {
            return dp[index][prevIndex];
        }

        int take = 0;

        if (prevIndex == -1 || arr[index] > arr[prevIndex]) {
            take = 1 + longestIncreasingSubSequence(arr, index + 1, index, dp);
        }

        int notTake = longestIncreasingSubSequence(arr, index + 1, prevIndex, dp);

        if (prevIndex == -1) {
            return Math.max(take, notTake);
        }
        return dp[index][prevIndex] = Math.max(take, notTake);

    }

    // bottom up approach
    public static int longestIncreasingSubSequenceBottomUp(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }

}
