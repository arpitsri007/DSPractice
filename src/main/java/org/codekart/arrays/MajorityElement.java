package org.codekart.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MajorityElement {
    // leetcode 169
    // TC: O(n)
    // SC: O(1)

    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    // leetcode 229
    // TC: O(n)
    // SC: O(1)

    public List<Integer> majorityElementII(int[] nums) {
        int candidate1 = nums[0];
        int candidate2 = nums[0];
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == candidate1) {
                count1++;
            } else if (nums[i] == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        List<Integer> result = new ArrayList<>();

        int freq1 = 0;
        int freq2 = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == candidate1) {
                freq1++;
            } else if (nums[i] == candidate2) {
                freq2++;
            }
        }

        if (freq1 > Math.floor(nums.length / 3)) {
            result.add(candidate1);
        }
        if (freq2 > Math.floor(nums.length / 3)) {
            result.add(candidate2);
        }
        return result;
    }
}
