package org.codekart.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubArray {

    // leetcode 525 - brute force
    public static int findMaxLength(int[] nums) {
        int n = nums.length;
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int currentSum = getSum(nums, i, j);
                if (currentSum == 0) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;

    }

    public static int getSum(int[] nums, int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            if (nums[k] == 0) {
                sum += -1;
            } else {
                sum += 1;
            }
        }
        return sum;
    }

    // leetcode 525 - using prefix sum
    // similar to leetcode 560
    public static int findMaxLengthPrefixSum(int[] nums) {
        int n = nums.length;
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int currentSum = 0;
        for (int i = 0; i < n; i++) {
            currentSum += nums[i] == 0 ? -1 : 1;

            if (map.containsKey(currentSum)) {
                // sum is repeated it means some part of array has sum 0
                // i - map.get(currentSum) is the length of the subarray with sum 0
                // Example: [1,0,1,0,1]
                // currentSum = 0 at index 1
                // i = 3
                // map.get(currentSum) = 1
                // i - map.get(currentSum) = 3 - 1 = 2
                // maxLength = 2
                maxLength = Math.max(maxLength, i - map.get(currentSum));
            } else {
                map.put(currentSum, i);
            }
        }
        return maxLength;
    }

    // leetcode 3031 - Count Alternating Subarrays brute force
    public static int countAlternatingSubarrays(int[] nums) {
        int n = nums.length;
        int count = n;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isAlternating(nums, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean isAlternating(int[] nums, int i, int j) {
        for (int k = i; k < j; k++) {
            if (nums[k] == nums[k + 1]) {
                return false;
            }
        }
        return true;
    }

    // leetcode 3031 - Count Alternating Subarrays - using sliding window
    public static long countAlternatingSubarraysSW(int[] nums) {

        int n = nums.length;
        if (n == 1) {
            return 1;
        }

        long count = 0;

        for (int i = 0; i < n; i++) {
            int j = i;

            while (j < n - 1 && nums[j] != nums[j + 1]) {
                j++;
            }

            int len = j - i + 1;

            count += (long) ((len * (long) (len + 1)) / 2);

            i = j;

        }

        return count;
    }

    // leetcode 3480 - Maximize subarrays after removing one conflicting pair
    public static long maximizeSubarrays(int n, int[][] conflictingPairs) {
        /*
         * Story points
         * 1. for all ending points in array, find the list of restricting starting
         * points
         * 2. make track of maxConflicting point and second maxConflicting point
         * 3. Count valid subarrays ending at each point and also maintain a list of
         * extra subArray if the restricting starting point and second
         * maxConflicting point are removed.
         * 4. for each point, if there is a restricting pair, then we can remove it and
         * add the extra subArray to the list.
         * 5. return the count + max element of list.
         * 6. valid count += current point - maxConflicting point ;
         * 7. extra count += maxConflicting point - second maxConflicting point ;
         * 
         */

        int maxConflictingPoint = 0;
        int secondMaxConflictingPoint = 0;

        long validCount = 0;

        Map<Integer, List<Integer>> conflictingPoints = new HashMap<>();

        for (int[] pair : conflictingPairs) {
            // (start, end)
            int start = Math.min(pair[0], pair[1]);
            int end = Math.max(pair[0], pair[1]);

            conflictingPoints.computeIfAbsent(end, k -> new ArrayList<>()).add(start); // {1,2, .... start, ... ,end}
        }

        long[] extra = new long[n + 1];
        // extra[i] = number of extra subarrays possible if the restricting starting
        // point and second maxConflicting point are removed.

        // O(n) + O(P) where P is the number of conflicting pairs
        for (int end = 1; end <= n; end++) {
            if (conflictingPoints.containsKey(end)) {
                List<Integer> restrictingPoints = conflictingPoints.get(end);
                List<Integer> sortedRestrictingPoints = getSortedRestrictingPoints(restrictingPoints, maxConflictingPoint,
                        secondMaxConflictingPoint);
                maxConflictingPoint = sortedRestrictingPoints.get(0);
                secondMaxConflictingPoint = sortedRestrictingPoints.get(1);
            }
            validCount += end - maxConflictingPoint;
            extra[maxConflictingPoint] += maxConflictingPoint - secondMaxConflictingPoint;
        }

        return validCount + Arrays.stream(extra).max().orElse(0L);
    }

    // O(P)
    private static List<Integer> getSortedRestrictingPoints(List<Integer> restrictingPoints, int firstMax,
            int secondMax) {

        for (int point : restrictingPoints) {
            if (point >= firstMax) {
                secondMax = firstMax;
                firstMax = point;
            } else if (point > secondMax) {
                secondMax = point;
            }
        }

        return Arrays.asList(firstMax, secondMax);
    }

    public static void main(String[] args) {
        int[][] conflictingPairs = { { 5, 7 }, { 6, 5 } };
        System.out.println(maximizeSubarrays(10, conflictingPairs));
    }

}
