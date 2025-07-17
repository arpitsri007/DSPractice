package org.codekart.algo.dynamicProgramming;

public class SubSequence {
    // leetcode 3201 - Find the maximum length of valid subsequence
    /*
     * You are given an integer array nums.
     * A subsequence sub of nums with length x is called valid if it satisfies:
     * 
     * (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x
     * - 1]) % 2.
     * Return the length of the longest valid subsequence of nums.
     * 
     * A subsequence is an array that can be derived from another array by deleting
     * some or no elements without changing the order of the remaining elements.
     */

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        System.out.println(longestValidSubsequence(nums));
    }

    public static int longestValidSubsequence(int[] nums) {
        int n = nums.length;
        int oddLenSubSeq = 0;
        int evenLenSubSeq = 0;
        int paritySubSeqLen = 0;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                evenLenSubSeq++;
            } else {
                oddLenSubSeq++;
            }
            maxLen = Math.max(maxLen, Math.max(oddLenSubSeq, evenLenSubSeq));
        }

        int parity = nums[0] % 2;
        for (int i = 1; i < n; i++) {
            if (nums[i] % 2 != parity) {
                paritySubSeqLen++;   
                maxLen = Math.max(maxLen, paritySubSeqLen);
                parity = nums[i] % 2;
            } 
        }

        return maxLen;
    }

    // public static int longestValidSubsequence2(int[] nums, int k) {
    //     int n = nums.length;
    //     int[] dp = new int[n];
    //     dp[0] = nums[0];
    //     for (int i = 1; i < n; i++) {
    //         dp[i] = dp[i - 1] + nums[i];
    //     }
    // }
}
