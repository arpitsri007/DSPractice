package org.codekart.algo.twopointer;

import java.util.Arrays;

public class SubSequence {
    // leetcode 1498 - Number of Subsequences That Satisfy the Given Sum Condition

    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        int mod = 1000000007;
        int[] power = new int[n];
        power[0] = 1;
        for (int i = 1; i < n; i++) {
            power[i] = (power[i - 1] * 2) % mod;
        }

        Arrays.sort(nums);
        int left = 0;
        int right = n - 1;
        int ans = 0;
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                // ans = (ans + (int) Math.pow(2, right - left)) % mod; // this would give
                // TLE
                ans = (ans + power[right - left] % mod) % mod;
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}
