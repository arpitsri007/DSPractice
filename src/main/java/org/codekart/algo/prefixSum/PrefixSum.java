package org.codekart.algo.prefixSum;

import java.util.Arrays;

public class PrefixSum {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int[] result = getAveragesPrefixSum(nums, 2);
        System.out.println(Arrays.toString(result));
    }

    public static int[] getAveragesPrefixSum(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n];


        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for(int i = 1; i < n; i++){
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        for(int i = 0; i < n; i++){
            if(i - k < 0 || i + k >= n){
                result[i] = -1;
                continue;
            }
            int left = i - k;
            int right = i + k;
            int windowSum = prefixSum[right] - prefixSum[left] + nums[left];
            result[i] = windowSum / (2 * k + 1);
        }
        return result;
    }
    
    
}
