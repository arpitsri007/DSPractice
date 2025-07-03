package org.codekart.arrays;

import java.util.Arrays;
import java.util.HashSet;

public class Consecutive {
    // leetcode 485 - Max Consecutive Ones
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int currentCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                currentCount++;
                maxCount = Math.max(maxCount, currentCount);
            } else {
                currentCount = 0;
            }
        }
        return maxCount;
    }

    // leetcode 487 - Max Consecutive Ones II
    /*
     * Given a binary array nums, return the maximum number of consecutive 1's in
     * the array if you can flip at most one 0.
     */
    public int findMaxConsecutiveOnesII(int[] nums) {
        int i = 0;
        int j = 0;
        int currFlip = 0;
        int result = 0;

        while (j < nums.length) {
            if (nums[j] != 1) {
                currFlip++;
            }
            while (currFlip > 1) {
                if (nums[i] == 0) {
                    currFlip--;
                }
                i++;
            }
            result = Math.max(result, j - i + 1);
            j++;
        }
        return result;
    }

    // leetcode 1004 - Max Consecutive Ones III
    /*
     * Given a binary array nums and an integer k, return the maximum number of
     * consecutive 1's in the array if you can flip at most k 0's.
     */
    public int longestOnes(int[] nums, int k) {
        int i = 0;
        int j = 0;
        int currFlip = 0;
        int result = 0;

        while (j < nums.length) {
            if (nums[j] != 1) {
                currFlip++;
            }
            while (currFlip > k) {
                if (nums[i] == 0) {
                    currFlip--;
                }
                i++;
            }
            result = Math.max(result, j - i + 1);
            j++;
        }
        return result;
    }

    // leetcode 128 - Longest Consecutive Sequence
    /*
     * Given an unsorted array of integers nums, return the length of the longest
     * consecutive elements sequence.
     */
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int curr = 1;
        Arrays.sort(nums);
        int result = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                curr++;
            } else if (nums[i] != nums[i - 1]) {
                curr = 1;
            }
        }
        return result;
    }

    // leetcode 128 - Longest Consecutive Sequence - using hashset
    /*
     * Given an unsorted array of integers nums, return the length of the longest
     * consecutive elements sequence.
     */
    public int longestConsecutiveSW(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int result = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curr = num;
                int currStreak = 1;
                while (set.contains(curr + 1)) {
                    curr++;
                    currStreak++;
                }
                result = Math.max(result, currStreak);  
            }
        }
        return result;
    }
}
