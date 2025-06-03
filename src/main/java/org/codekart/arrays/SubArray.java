package org.codekart.arrays;

import java.util.HashMap;
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
    public static int findMaxLengthSW(int[] nums) {
        int n = nums.length;
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int currentSum = 0;
        for (int i = 0; i < n; i++) {
            currentSum += nums[i] == 0 ? -1 : 1;

            if (map.containsKey(currentSum)) {
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

            count += (long)((len * (long)(len + 1)) / 2);

            i = j;

        }

        return count;
    }
}
