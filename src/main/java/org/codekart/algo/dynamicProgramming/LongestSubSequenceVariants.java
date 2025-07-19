package org.codekart.algo.dynamicProgramming;

import java.util.Arrays;

public class LongestSubSequenceVariants {
    // leetcode 3202
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5 };
        int k = 2;
        System.out.println(maximumLength(nums, k));
    }

    // Find the Maximum Length of Valid Subsequence II
    public static int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int result = 1;
        int[][] dp = new int[k][n];
        for (int i = 0; i < k; i++) {
            Arrays.fill(dp[i], 1);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int mod = (nums[j] + nums[i]) % k;
                dp[mod][i] = Math.max(dp[mod][i], dp[mod][j] + 1);
                result = Math.max(result, dp[mod][i]);
            }
        }
        return result;
    }
}
