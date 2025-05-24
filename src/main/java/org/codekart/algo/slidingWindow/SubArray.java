package org.codekart.algo.slidingWindow;

public class SubArray {
    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 2, 7, 5 };
        int minK = 1;
        int maxK = 5;
        int result = countSubarrays(arr, minK, maxK);
        System.out.println(result);
   
    }

    // leetcode 2444: Count Subarrays With Fixed Bounds
    // brute force:
    // 1. generate all subarrays
    // 2. check if the subarray is valid
    // 3. count the number of valid subarrays
    // time complexity: O(n^3)
    // space complexity: O(1)
    public static int countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isValidSubarray(nums, i, j, minK, maxK)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isValidSubarray(int[] nums, int i, int j, int minK, int maxK) {
        for (int k = i; k <= j; k++) {
            if (nums[k] < minK || nums[k] > maxK) {
                return false;
            }
        }
        return true;
    }

    // sliding window:

    public static int countSubarraysSW(int[] nums, int minK, int maxK) {
        int maxkPosition = -1;
        int minkPosition = -1;
        int culpritIndex = -1;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < minK || nums[i] > maxK){
                culpritIndex = i;
            }
            if(nums[i] == minK){
                minkPosition = i;
            }
            if(nums[i] == maxK){
                maxkPosition = i;
            }

            int left = Math.min(minkPosition, maxkPosition);
            long temp = left - culpritIndex;
            if(temp > 0) {
                result += temp;
            }
        
        }

        return result;
      
    }

    // K radius subarray avg brute force
    public static int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if(i - k < 0 || i + k >= n){
                result[i] = -1;
                continue;
            }
            int sum = 0;
            for (int j = i - k; j <= i + k; j++) {
                sum += nums[j];
            }
            result[i] = sum / (2 * k + 1);
        }
        return result;
    }

    // K radius subarray avg sliding window


}
    

