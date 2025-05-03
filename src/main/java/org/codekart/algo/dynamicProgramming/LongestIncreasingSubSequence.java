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

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
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
        for (int i = 0; i < n ; i++) {
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
            for (int j = i-1; j >= 0; j--) {
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
        long[][][] dp = new long[n][k+1][m+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                for(int l = 0; l <= m; l++) {
                    dp[i][j][l] = -1;
                }
            }
        }
        return numOfArraysHelper(0, 0, 0, n, m, k, dp);
    }

    protected static int numOfArraysHelper(int idx, int searchCost, int maxSoFar, int n, int m, int k, long[][][] dp) {
        int MOD = 1000000007;
        if (idx == n) {
            if(searchCost == k) {
                return 1;
            }
            return 0;
        }

        if(searchCost > k || searchCost + (n-idx) < k) {
            return 0;
        }

        if(dp[idx][searchCost][maxSoFar] != -1) {
            return (int)dp[idx][searchCost][maxSoFar];
        }

        long result = 0;
        for(int i = 1; i <= m; i++) {
            if(i > maxSoFar) {
                result += numOfArraysHelper(idx+1, searchCost+1, i, n, m, k, dp) % MOD;
            } else {
                result += numOfArraysHelper(idx+1, searchCost, maxSoFar, n, m, k, dp) % MOD;
            }
        }
        dp[idx][searchCost][maxSoFar] = result % MOD;
        return (int)dp[idx][searchCost][maxSoFar];
    }

    // TODO: bottom up approach of numberOfArrays
    
    public static int numberOfArraysBottomUp(int n, int m, int k) {
       return 0;
    }

}
