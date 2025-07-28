package org.codekart.algo.twopointer;

public class CountHillsValleys {
    // leetcode 2210
    public int countHillsValleys(int[] nums) {
        int n = nums.length;
        int count = 0;
        int i = 0;
        int j = 1;
        while (j + 1 < n) {
            if ((nums[i] < nums[j] && nums[j] > nums[j + 1])
                    || (nums[i] > nums[j] && nums[j] < nums[j + 1])) {
                count++;
                i = j;
            }
            j++;
        }
        return count;
    }
}
