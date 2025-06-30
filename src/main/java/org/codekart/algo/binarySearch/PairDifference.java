package org.codekart.algo.binarySearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PairDifference {
    // leetcode 2616 - Minimize the Maximum Difference of Pairs
    // mini - max pattern

    /*
     * Intuition:
     * 1. Brute Force:
     * - Generate all possible pairs and calculate the difference
     * - Return the minimum difference
     * 2. Binary Search:
     * - Sort the array
     * - Use binary search to find the minimum difference
     * - Return the minimum difference
     */

    // Brute Force using recursion - TC: O(2^n) - SC: O(n)
    /*
     * 1. Base Case
     * 
     * If we've already selected p pairs, return the current maximum difference
     * If p is 0, return 0 (as specified in the problem)
     * 
     * 2. Recursive Case
     * 
     * Try every possible pair of unused indices
     * For each pair, calculate the absolute difference
     * Update the maximum difference if needed
     * Recursively select the remaining p-1 pairs
     * Return the minimum of all possible maximum differences
     * 
     * Time Complexity:
     * 
     * The time complexity of this brute force approach is O(n^(2p)), where:
     * 
     * n is the length of the array
     * p is the number of pairs
     * 
     * This is because:
     * 
     * For each pair, we try O(n²) combinations
     * We need to select p pairs
     * Each recursive call spawns more recursive calls
     * 
     * For even moderately sized inputs, this will time out. However, it correctly
     * solves the problem for small inputs.
     * 5. Space Complexity
     * The space complexity is O(p + n):
     * 
     * O(p) for the recursion stack (maximum depth is p)
     * O(n) for the boolean array to track used indices
     */
    public int minimizeMaxBruteForce(int[] nums, int p) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (p == 0) {
            return 0;
        }

        // Array to track used indices
        boolean[] used = new boolean[nums.length];
        return minimizeMaxHelper(nums, p, 0, used, 0);
    }

    // find all pairs and calculate the difference
    private int minimizeMaxHelper(int[] nums, int p, int pairSelected, boolean[] used, int currentMaxDiff) {
        if (pairSelected == p) {
            return currentMaxDiff;
        }

        int result = Integer.MAX_VALUE;

        // try all possible pairs of unused indices
        for (int i = 0; i < nums.length - 1; i++) {
            if (used[i])
                continue;

            for (int j = i + 1; j < nums.length; j++) {
                if (used[j])
                    continue;

                // Mark indices as used
                used[i] = true;
                used[j] = true;

                // Calculate the difference
                int diff = Math.abs(nums[i] - nums[j]);

                // Update the maximum difference
                int newMaxDiff = Math.max(currentMaxDiff, diff);

                // Recursively select the remaining p-1 pairs
                int subResult = minimizeMaxHelper(nums, p, pairSelected + 1, used, newMaxDiff);

                result = Math.min(result, subResult);

                // Unmark indices as unused
                used[i] = false;
                used[j] = false;
            }
        }

        return result;
    }

    // Pattern - mini - max - Binary Search
    // TC: O(n log n) - SC: O(1)
    public int minimizeMaxBinarySearch(int[] nums, int p) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);

        // left is the minimum possible difference
        // right is the maximum possible difference
        int left = 0;
        int right = nums[nums.length - 1] - nums[0];

        int result = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canFormPairs(nums, p, mid)) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    // check if we can form p pairs with the given mid difference
    private boolean canFormPairs(int[] nums, int p, int mid) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (Math.abs(nums[i] - nums[i + 1]) <= mid) {
                count++;
                i++;
            }
        }
        return count >= p;
    }

    // leetcode 532 - K-diff Pairs in an Array
    // TC: O(n log n) - SC: O(1) - binary search
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);

        int result = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            int target = nums[i] + k;
            if (binarySearch(nums, target, i)) {
                result++;
            }
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return result;
    }

    // binary search to find the target
    private boolean binarySearch(int[] nums, int target, int left) {
        left = left + 1;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    // leetcode 532 - using two pointers
    public int findPairsTwoPointers(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);

        int i = 0;
        int j = 1;
        int result = 0;

        while (j < nums.length) {
            int diff = nums[j] - nums[i];
            if (diff == k) {
                result++;

                // skip duplicates
                int currentI = nums[i];
                int currentJ = nums[j];

                // move I to next unique element
                while (i < j && nums[i] == currentI) {
                    i++;
                }
                while (j < nums.length && nums[j] == currentJ) {
                    j++;
                }
            } else if (diff < k) {
                j++;
            } else {
                i++;
                if (i >= j) {
                    j = i + 1;
                }
            }

        }
        return result;
    }

    // find Pairs using brute force
    public int findPairsBruteForce(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 0) {
            return 0;
        }

        Set<String> uniquePairs = new HashSet<>();

        // Check all possible pairs
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int diff = Math.abs(nums[i] - nums[j]);

                if (diff == k) {
                    // Create a normalized pair representation to avoid duplicates
                    // Always put the smaller number first
                    int smaller = Math.min(nums[i], nums[j]);
                    int larger = Math.max(nums[i], nums[j]);
                    String pair = smaller + "," + larger;
                    uniquePairs.add(pair);
                }
            }
        }

        return uniquePairs.size();
    }

    // main method
    public static void main(String[] args) {
        PairDifference pairDifference = new PairDifference();
        int[] nums = { 1, 2, 4, 4, 3, 3, 0, 9, 2, 3 };
        int k = 3;
        System.out.println(pairDifference.findPairsTwoPointers(nums, k));
    }
}
