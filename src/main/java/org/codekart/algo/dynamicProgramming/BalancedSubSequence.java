package org.codekart.algo.dynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BalancedSubSequence {

    // driver code
    public static void main(String[] args) {
        int[] arr = { 5, -1 };
        System.out.println(maxBalancedSubsequenceSum(arr));
        System.out.println(maxBalancedSubsequenceSumBottomUp(arr));
    }

    public static long maxBalancedSubsequenceSum(int[] arr) {

        Map<String, Long> map = new HashMap<>();

        int max = Arrays.stream(arr).max().getAsInt();

        if (max <= 0) {
            return max;
        }

        return solve(-1, 0, arr, map);
    }

    public static long solve(int prevIndex, int index, int[] arr, Map<String, Long> map) {

        if (index >= arr.length) {
            return 0;
        }

        String key = String.valueOf(prevIndex) + "-" + String.valueOf(index);

        if (map.containsKey(key)) {
            return map.get(key);
        }

        long skip = Integer.MIN_VALUE;
        long take = Integer.MIN_VALUE;

        if (prevIndex == -1 || arr[index] - index >= arr[prevIndex] - prevIndex) {
            take = solve(index, index + 1, arr, map) + arr[index];
        }
        skip = solve(prevIndex, index + 1, arr, map);
        map.put(key, Math.max(skip, take));
        return Math.max(skip, take);
    }

    public static long maxBalancedSubsequenceSumBottomUp(int[] arr) {
        int n = arr.length;
        long max = Arrays.stream(arr).max().getAsInt();
        // dp[i] = max sum of balanced subsequence ending at i
        if (max <= 0) {
            return max;
        }

        long maxSum = Integer.MIN_VALUE;

        long[] dp = new long[n];
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] - i >= arr[j] - j) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                    maxSum = Math.max(maxSum, dp[i]);
                }
            }
        }

        return maxSum;
    }

    // optimal solution using ordered map
    public static long maxBalancedSubsequenceSumOptimal(int[] arr) {
        int n = arr.length;
        long max = Arrays.stream(arr).max().getAsInt();
        if (max <= 0) {
            return max;
        }

        TreeMap<Integer, Long> map = new TreeMap<>();

        long maxSum = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            Integer key = arr[i] - i;
            long currentSum = arr[i];

            Map.Entry<Integer, Long> prevEntry = map.floorEntry(key);

            if (prevEntry != null) {
                currentSum = Math.max(currentSum, prevEntry.getValue() + arr[i]);
            }
            maxSum = Math.max(maxSum, currentSum);

            // Update the tree map, only keep the entry if it provides a higher sum
            if (!map.containsKey(key) || map.get(key) < currentSum) {
                map.put(key, currentSum);

                // Cleanup: remove any entries with larger keys that have a lower sum
                while (true) {
                    Map.Entry<Integer, Long> higherEntry = map.higherEntry(key);
                    if (higherEntry == null || higherEntry.getValue() > currentSum) {
                        break;
                    }
                    map.remove(higherEntry.getKey());
                }
            }
        }

        return maxSum;
    }
}