package org.codekart.algo.slidingWindow;

import java.util.Map;
import java.util.TreeMap;

public class ContinousSubArray {
    

    // leetcode 2762 - continuous subarray sum
    // Let i, i + 1, ..., j be the indices in the subarray. Then, for each pair of indices i <= i1, i2 <= j, 0 <= |nums[i1] - nums[i2]| <= 2.
    // Return the total number of continuous subarrays.
    // brute force - using two loops
    public static int continuousSubarray(int[] nums) {
        int n = nums.length;
        int result = 0;

        for(int i = 0; i < n; i++){
            int max = nums[i];
            int min = nums[i];
            for(int j = i; j < n; j++){
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                if(max - min <= 2){
                    result++;
                }
            }
        }

        return result;
    }

    // TC:  O(n) - given the map is always max 3 elements. otherwise in general insert/delete operation is O(logn)
    // SC: O(1) - given the map is always max 3 elements.

    // using sliding window
    public static long continuousSubarraySW(int[] nums) {
        int n = nums.length;
        long result = 0;

        TreeMap<Integer, Integer> mp = new TreeMap<>();

        int i = 0;
        int j = 0;

        while(j < n){
            mp.put(nums[j], mp.getOrDefault(nums[j], 0) + 1);

            // while the window is invalid i.e  |max - min| > 2, shrink the window
            while(mp.lastKey() - mp.firstKey() > 2){
                mp.put(nums[i], mp.get(nums[i]) - 1);
                if(mp.get(nums[i]) == 0){
                    mp.remove(nums[i]);
                }
                i++;
            }

            result += j - i + 1;
            j++;
        }
        return result;
    }

}
