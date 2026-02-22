package org.codekart.algo.lineSweep;

import java.util.Arrays;

public class PermutationSum {
    // 1589. Maximum Sum Obtained of Any Permutation
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length;
        int[] events = new int[n];

        for (int[] request : requests) {
            int start = request[0];
            int end = request[1];
            events[start] += 1; // Start of a request adds 1
            if (end + 1 < n) {
                events[end + 1] -= 1; // End of a request subtracts 1
            }
        }
        // Compute prefix sum to get the count of each index being requested
        for (int i = 1; i < events.length; i++) {
            events[i] += events[i - 1];
        }

        // Sort nums in descending order to maximize sum
        Arrays.sort(nums);
        Arrays.sort(events);

        int mod = 1000000007;
        long result = 0;

        for (int i = 0; i < n; i++) {
            if (events[i] > 0) {
                result = (result + ((long) nums[i] * events[i]) % mod) % mod;
            }
        }
        return (int) result;
    }
}
