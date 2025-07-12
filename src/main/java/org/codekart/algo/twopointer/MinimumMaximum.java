package org.codekart.algo.twopointer;

import java.util.Arrays;

public class MinimumMaximum {
    // leetcode 1877 - Minimize Maximum Pair Sum in Array

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int max = 0;

        while (left < right) {
            max = Math.max(max, nums[left] + nums[right]);
            left++;
            right--;
        }
        return max;
    }

    // leetcode 881 - Boats to Save People
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int i = 0;
        int j = people.length - 1;
        int count = 0;

        while (i <= j) {
            if (people[i] + people[j] <= limit) {
                count++;
                i++;
                j--;
            } else if (people[j] <= limit) {
                count++;
                j--;
            } else if (people[i] <= limit) {
                count++;
                i++;
            }
        }

        return count;

    }
}
