package org.codekart.algo.dynamicProgramming.dpStrings;

public class SortedString {
    // leetcode 1641 - Count Sorted Vowel Strings
    // using brute force
    char[] vowels = { 'a', 'e', 'i', 'o', 'u' };

    public int countVowelStrings(int n) {
        return countVowelStringsHelper(n, 0);
    }

    private int countVowelStringsHelper(int n, int index) {
        if (n == 0) {
            return 1; // why this is 1? because we are not adding any vowel to the string

        }
        int result = 0;
        for (int i = index; i < vowels.length; i++) {
            if (vowels[i] >= vowels[index]) {
                result += countVowelStringsHelper(n - 1, i);
            }
        }
        return result;
    }

    // ========== DP SOLUTIONS ==========

    // Solution 1: Top-down DP with Memoization
    public int countVowelStringsTopDown(int n) {
        Integer[][] memo = new Integer[n + 1][vowels.length];
        return countVowelStringsTopDownHelper(n, 0, memo);
    }

    private int countVowelStringsTopDownHelper(int n, int index, Integer[][] memo) {
        if (n == 0) {
            return 1;
        }

        if (memo[n][index] != null) {
            return memo[n][index];
        }

        int result = 0;
        for (int i = index; i < vowels.length; i++) {
            if (vowels[i] >= vowels[index]) {
                result += countVowelStringsTopDownHelper(n - 1, i, memo);
            }
        }

        memo[n][index] = result;
        return result;
    }

    // Solution 2: Bottom-up DP with Tabulation
/*     public int countVowelStringsBottomUp(int n) {
        // dp[i][j] = number of strings of length i ending with vowel at index j
        int[][] dp = new int[n + 1][5];

        // Base case: strings of length 1
        for (int j = 0; j < 5; j++) {
            dp[1][j] = 1;
        }

        // Fill the dp table
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 5; j++) {
                // For each vowel j, we can append it to strings ending with vowels <= j
                for (int k = 0; k <= j; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        // Sum all possibilities for length n
        int result = 0;
        for (int j = 0; j < 5; j++) {
            result += dp[n][j];
        }

        return result;
    } */

    // Solution 3: Optimized Bottom-up DP (Space Optimized)
    public int countVowelStringsOptimized(int n) {
        // We only need the previous row, so we can use 1D array
        int[] dp = new int[5];

        // Base case: strings of length 1
        for (int j = 0; j < 5; j++) {
            dp[j] = 1;
        }

        // Fill the dp array for each length
        for (int i = 2; i <= n; i++) {
            int sum = 0;
            for (int j = dp.length - 1; j >= 0; j--) {
                sum += dp[j];
                dp[j] = sum;
            }
        }

        // Sum all possibilities
        int result = 0;
        for (int j = 0; j < 5; j++) {
            result += dp[j];
        }

        return result;
    }

    // Solution 4: Mathematical Approach (Most Efficient)
    public int countVowelStringsMath(int n) {
        // This is a combination with repetition problem
        // C(n+4, 4) = (n+4)! / (4! * n!)
        // For n=1: C(5,4) = 5
        // For n=2: C(6,4) = 15
        // For n=3: C(7,4) = 35

        return (n + 4) * (n + 3) * (n + 2) * (n + 1) / 24;
    }
}
