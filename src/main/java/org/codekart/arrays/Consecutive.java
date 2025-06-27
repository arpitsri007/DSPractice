package org.codekart.arrays;

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
    public int findMaxConsecutiveOnesIII(int[] nums, int k) {
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
}
