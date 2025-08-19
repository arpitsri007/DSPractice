package org.codekart.recursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimumOperation {
    // leetcode 1658
    // using recursion

    private int minCount = Integer.MAX_VALUE;
    private int count = 0;

    public int minOperations(int[] nums, int x) {
        minOperationsHelper(nums, x, 0, nums.length - 1);
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    private void minOperationsHelper(int[] nums, int remain, int left, int right) {
        if (remain == 0) {
            minCount = Math.min(minCount, count);
            return;
        }

        if (remain < 0 || left > right || count >= minCount) {
            return;
        } else {
            count++;
            minOperationsHelper(nums, remain - nums[left], left + 1, right);
            minOperationsHelper(nums, remain - nums[right], left, right - 1);
            count--;
        }
    }

    // using hashmap
    public int minOperations2(int[] nums, int x) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLength = -1;

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            map.put(sum, i);
        }

        int target = sum - x;
        map.put(0, -1);
        sum = 0;

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int remain =  sum - target;
            if(map.containsKey(remain)) {
                int index = map.get(remain);
                maxLength = Math.max(maxLength, i - index);
            } 
        }

        return maxLength == -1 ? -1 : nums.length - maxLength;
    }
  



       


}
