package org.codekart.algo.dynamicProgramming;

import java.util.Arrays;

public class HouseRobber {
    
    // House Robber I - Top Down Approach
    public static int houseRobber(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return houseRobber(nums, dp, 0);
    }

    private static int houseRobber(int[] nums, int[] dp, int index) {
        if (index >= nums.length) {
            return 0;
        }

        if (dp[index] != -1) {
            return dp[index];
        }

        int rob = nums[index] + houseRobber(nums, dp, index + 2);
        int notRob = houseRobber(nums, dp, index + 1);

        dp[index] = Math.max(rob, notRob);
        return dp[index];
    }

    // House Robber I - Bottom Up Approach
    public static int houseRobberBottomUp(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }   
        return dp[nums.length - 1];
    }

    // House Robber I - Bottom Up with Space Optimization
    public static int houseRobberBottomUpSpaceOptimization(int[] nums) {
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int curr = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    // House Robber II - Top Down Approach
    public static int houseRobberII(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        int firstHouse = houseRobberI(nums, dp, 0, nums.length - 2);
        Arrays.fill(dp, -1);
        int secondHouse = houseRobberI(nums, dp, 1, nums.length - 1);
        return Math.max(firstHouse, secondHouse);
    }

    // House Robber II - Bottom Up Approach
    public static int houseRobberIIBottomUp(int[] nums) {
        int firstHouse = houseRobberIBottomUp(nums, 0, nums.length - 2);
        int secondHouse = houseRobberIBottomUp(nums, 1, nums.length - 1);
        return Math.max(firstHouse, secondHouse);
    }

    private static int houseRobberIBottomUp(int[] nums, int startIndex, int endIndex) {
        int[] dp = new int[nums.length];
        dp[startIndex] = nums[startIndex];
        dp[startIndex + 1] = Math.max(nums[startIndex], nums[startIndex + 1]);
        for (int i = startIndex + 2; i <= endIndex; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[endIndex];
    }

    private static int houseRobberI(int[] nums, int[] dp, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return 0;
        }

        if (dp[startIndex] != -1) {
            return dp[startIndex];
        }

        int rob = nums[startIndex] + houseRobberI(nums, dp, startIndex + 2, endIndex);
        int notRob = houseRobberI(nums, dp, startIndex + 1, endIndex);

        dp[startIndex] = Math.max(rob, notRob);
        return dp[startIndex];
    }
} 