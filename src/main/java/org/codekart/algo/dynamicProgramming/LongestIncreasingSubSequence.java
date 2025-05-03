package org.codekart.algo.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (index >= arr.length) {
            return 0;
        }

        if (prevIndex != -1 && dp[index][prevIndex] != -1) {
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

        int maxLength = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
        }
        return maxLength;
    }

    // Patience Sorting
    // - this is a sorting algorithm that is used to find the longest increasing
    // subsequence
    // - it is a greedy algorithm that is used to find the longest increasing
    // subsequence

    public static int patienceSorting(int[] arr) {
        int n = arr.length;

        if (n == 0) {
            return 0;
        }

        int[] piles = new int[n];

        int numOfPiles = 0;

        for (int i = 0; i < n; i++) {
            // find just greater element in sorted array if found replace it else add it to
            // the sorted array
            int pileIndex = findPileToReplace(piles, numOfPiles, arr[i]);

            piles[pileIndex] = arr[i];

            if (pileIndex == numOfPiles) {
                numOfPiles++;
            }
        }
        return numOfPiles;
    }

    // Binary Search to find the first pile with top card >= target
    protected static int findPileToReplace(int[] piles, int numOfPiles, int target) {
        int left = 0, right = numOfPiles - 1;
        int result = numOfPiles; // default result is the last pile
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (piles[mid] >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    // maximum length of pair chain
    public static int maximumLengthOfPairChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int n = pairs.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        return maximumLengthOfPairChain(pairs, -1, 0, dp);
    }

    public static int maximumLengthOfPairChainBottomUp(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int n = pairs.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];

    }

    protected static int maximumLengthOfPairChain(int[][] pairs, int prev, int index, int[][] dp) {
        if (index == pairs.length) {
            return 0;
        }

        int take = 0;
        int notTake = 0;

        if (prev != -1 && dp[index][prev] != -1) {
            return dp[index][prev];
        }

        if (prev == -1 || pairs[index][0] > pairs[prev][1]) {
            take = 1 + maximumLengthOfPairChain(pairs, index, index + 1, dp);
        }

        notTake = maximumLengthOfPairChain(pairs, prev, index + 1, dp);

        if (prev == -1) {
            return Math.max(take, notTake);
        }

        return dp[index][prev] = Math.max(take, notTake);
    }

    // longest string chain top down approach
    public static int longestStringChain(String[] words) {
        int n = words.length;
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return longestStringChain(words, 0, -1, dp);
    }

    protected static int longestStringChain(String[] words, int index, int prev, int[][] dp) {
        if (index == words.length) {
            return 0;
        }

        int take = 0;
        int notTake = 0;

        if (prev != -1 && dp[index][prev] != -1) {
            return dp[index][prev];
        }

        if (prev == -1 || isPredecessor(words[prev], words[index])) {
            take = 1 + longestStringChain(words, index + 1, index, dp);
        }

        notTake = longestStringChain(words, index + 1, prev, dp);

        if (prev == -1) {
            return Math.max(take, notTake);
        }

        return dp[index][prev] = Math.max(take, notTake);

    }

    protected static boolean isPredecessor(String previousWord, String currentWord) {
        if (currentWord.length() != previousWord.length() + 1) {
            return false;
        }
        int i = 0, j = 0;
        while (i < previousWord.length() && j < currentWord.length()) {
            if (previousWord.charAt(i) == currentWord.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == previousWord.length();
    }

    // longest string chain bottom up approach
    public static int longestStringChainBottomUp(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int n = words.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (isPredecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // Build Array Where You Can Find The Maximum Exactly K Comparisons
    public static int numberOfArrays(int n, int m, int k) {
        // recursion approach
        // numOfArraysHelper(int idx, int searchCost, int maxSoFar, dp)
        long[][][] dp = new long[n][k + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int l = 0; l <= m; l++) {
                    dp[i][j][l] = -1;
                }
            }
        }
        return numOfArraysHelper(0, 0, 0, n, m, k, dp);
    }

    protected static int numOfArraysHelper(int idx, int searchCost, int maxSoFar, int n, int m, int k, long[][][] dp) {
        int MOD = 1000000007;
        if (idx == n) {
            if (searchCost == k) {
                return 1;
            }
            return 0;
        }

        if (searchCost > k || searchCost + (n - idx) < k) {
            return 0;
        }

        if (dp[idx][searchCost][maxSoFar] != -1) {
            return (int) dp[idx][searchCost][maxSoFar];
        }

        long result = 0;
        for (int i = 1; i <= m; i++) {
            if (i > maxSoFar) {
                result += numOfArraysHelper(idx + 1, searchCost + 1, i, n, m, k, dp) % MOD;
            } else {
                result += numOfArraysHelper(idx + 1, searchCost, maxSoFar, n, m, k, dp) % MOD;
            }
        }
        dp[idx][searchCost][maxSoFar] = result % MOD;
        return (int) dp[idx][searchCost][maxSoFar];
    }

    // TODO: bottom up approach of numberOfArrays

    public static int numberOfArraysBottomUp(int n, int m, int k) {
        return 0;
    }

    // Largest Divisible Subset
    // Problem Statement: Given an array of integers, find the largest subset such
    // that every pair (i, j) in the subset satisfies i % j == 0 or j % i == 0.

    // recursive approach

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        List<Integer> result = new ArrayList<>();
        List<Integer> currElement = new ArrayList<>();
        int prev = -1;

        return largestDivisibleSubset(nums, 0, prev, result, currElement);
    }

    protected static List<Integer> largestDivisibleSubset(int[] nums, int index, int prev, List<Integer> result,
            List<Integer> currElement) {
        if (index >= nums.length) {
            if (currElement.size() > result.size()) {
                result = new ArrayList<>(currElement);
            }
            return result;
        }

        // Take option
        if (prev == -1 || nums[index] % prev == 0 || prev % nums[index] == 0) {
            currElement.add(nums[index]);
            result = largestDivisibleSubset(nums, index + 1, nums[index], result, currElement);
            currElement.remove(currElement.size() - 1);
        }

        // Not Take option
        return largestDivisibleSubset(nums, index + 1, prev, result, currElement);
    }

    protected static List<Integer> largestDivisibleSubsetTopDownMemo(int[] nums) {
        Arrays.sort(nums);
        Map<String, List<Integer>> memo = new HashMap<>();
        return largestDivisibleSubsetTopDownMemo(nums, 0, -1, memo);
    }

    protected static List<Integer> largestDivisibleSubsetTopDownMemo(int[] nums, int index, int prevIndex,
            Map<String, List<Integer>> memo) {
        if (index >= nums.length) {
            return new ArrayList<>();
        }

        String key = index + "-" + prevIndex;

        if (memo.containsKey(key)) {
            return new ArrayList<>(memo.get(key));
        }

        List<Integer> skipCurrent = largestDivisibleSubsetTopDownMemo(nums, index + 1, prevIndex, memo);
        List<Integer> takeCurrent = new ArrayList<>();

        int prev = prevIndex == -1 ? Integer.MIN_VALUE : nums[prevIndex];

        if (prevIndex == -1 || nums[index] % prev == 0 || prev % nums[index] == 0) {
            List<Integer> nextElements = largestDivisibleSubsetTopDownMemo(nums, index + 1, index, memo);
            takeCurrent = new ArrayList<>(nextElements);
            takeCurrent.add(0, nums[index]);
        }

        List<Integer> result = takeCurrent.size() > skipCurrent.size() ? takeCurrent : skipCurrent;

        memo.put(key, new ArrayList<>(result));
        return result;
    }

    // bottom up approach

    public static List<Integer> largestDivisibleSubsetBottomUp(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        int[] prev = new int[n];
        int maxLength = 1;
        int maxIndex = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                   // maxLength and maxIndex
                   if(dp[i] > maxLength) {
                    maxLength = dp[i];
                    maxIndex = i;
                   }
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        while (maxIndex != -1) {
            result.add(nums[maxIndex]);
            maxIndex = prev[maxIndex];
        }

        return result;
    }

}
