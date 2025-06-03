package org.codekart.arrays;

import java.util.Arrays;

public class Arithmetic {
    
    // leetcode 2183 - brute force
    public static long countPairs(int[] nums, int k) {
        int n = nums.length;
        long result = 0;

        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                int currentProduct = getProduct(nums, i, j);

                if(currentProduct % k == 0){
                    result++;
                }
            }
        }

        return result;
    }

    public static int getProduct(int[] nums, int i, int j){
        int product = nums[i] * nums[j];
        return product;
    }

    //leetcode 628 - using sorting in descending order
    public static int maximumProduct(int[] nums) {
        int n = nums.length;    
        Arrays.sort(nums);
        int maxProduct = nums[n-1] * nums[n-2] * nums[n-3];

        int maxProduct2 = nums[0] * nums[1] * nums[n-1];

        return Math.max(maxProduct, maxProduct2);
    }

    //leetcode 628 - using 3 variables
    public static int maximumProductApproach2(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for(int num : nums){
            if(num > max1){
                max3 = max2;
                max2 = max1;
                max1 = num;
            }
            else if(num > max2){
                max3 = max2;
                max2 = num;
            }
            else if(num > max3){
                max3 = num;
            }

            if(num < min1){
                min2 = min1;
                min1 = num;
            }   
            else if(num < min2){
                min2 = num;
            }
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }


}
