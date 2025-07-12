package org.codekart.algo.twopointer;

public class ArrayPointers {
    // leetcode 26 - Remove Duplicates from Sorted Array
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = i + 1;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
            j++;
        }
        return i + 1;
    }

    // leetcode 962 - Maximum Width Ramp
    // brute force approach
    public int maxWidthRamp(int[] nums) {
        int maxWidth = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] <= nums[j]) {
                    maxWidth = Math.max(maxWidth, j - i);
                }
            }
        }
        return maxWidth;
    }

    // leetcode 962 - Maximum Width Ramp
    // better brute force approach
    public int maxWidthRamp2(int[] nums) {
        int maxWidth = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] <= nums[j]) {
                    maxWidth = Math.max(maxWidth, j - i);
                    break;
                }
            }
        }
        return maxWidth;
    }

    // leetcode 962 - Maximum Width Ramp - two pointer approach
    public int maxWidthRamp3(int[] nums) {
        int n = nums.length;
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];

        for (int k = n - 2; k >= 0; k--) {
            rightMax[k] = Math.max(rightMax[k + 1], nums[k]);
        }

        int ramp = 0;
        int i = 0;
        int j = 0;

        while (j < n) {
            while (i < j && nums[i] > rightMax[j]) {
                i++;
            }
            ramp = Math.max(ramp, j - i);
            j++;
        }
        return ramp;
    }
}
