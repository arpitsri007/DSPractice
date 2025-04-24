package org.codekart.algo.dynamicProgramming;

public class MaximumAlternatingSubSequence {

    // driver code
    public static void main(String[] args) {
        int[] arr = { 4, 2, 5, 3 };
        System.out.println(maxAlternatingSubSequenceTopDown(arr));
        System.out.println(maxAlternatingSubSequenceBottomUp(arr));
    }

    // top down approach
    public static int maxAlternatingSubSequenceTopDown(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][2];
        return maxAlternatingSubSequenceTopDown(arr, 0, true, dp);
    }

    public static int maxAlternatingSubSequenceTopDown(int[] arr, int index, boolean isEven, int[][] dp) {
        if (index == arr.length) {
            return 0;
        }
        if (dp[index][isEven ? 0 : 1] != 0) {
            return dp[index][isEven ? 0 : 1];
        }

        int skip = maxAlternatingSubSequenceTopDown(arr, index + 1, isEven, dp);
        int take = arr[index] * (isEven ? 1 : -1) + maxAlternatingSubSequenceTopDown(arr, index + 1, !isEven, dp);

        dp[index][isEven ? 0 : 1] = Math.max(skip, take);
        return dp[index][isEven ? 0 : 1];
    }

    // bottom up approach
    public static int maxAlternatingSubSequenceBottomUp(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n + 1][2];
        return maxAlternatingSubSequenceBottomUp(arr, 0, true, dp);
    }

    public static int maxAlternatingSubSequenceBottomUp(int[] arr, int index, boolean isEven, int[][] dp) {
        if (index == arr.length) {
            return 0;
        }

        for (int i = 1; i <= arr.length ; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - arr[i-1]); // even - 0 , odd - 1
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + arr[i-1]); // even - 1 , odd - 0
        }

        return Math.max(dp[arr.length][0], dp[arr.length][1]);

    }

}
