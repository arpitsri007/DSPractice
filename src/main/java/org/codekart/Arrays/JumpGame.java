package org.codekart.Arrays;

public class JumpGame {
    /*  You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

        Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:

        0 <= j <= nums[i] and
        i + j < n
        Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
    * */

    public int jump(int[] nums) {
        return minJump(nums, 0, Integer.MAX_VALUE);
    }


    // recursive
    private int minJump(int[] nums, int idx, int minJump) {
        if(idx >= nums.length-1) return 0;
        if(nums[idx] == 0) return Integer.MAX_VALUE;

        int reach = idx + nums[idx];
        int min = Integer.MAX_VALUE;

        for(int jump=idx+1; jump <= reach ; jump++) {
            if(jump < nums.length) {
                min = Math.min(minJump(nums, jump, minJump+1), min);
            }
        }

        return min == Integer.MAX_VALUE ? min : min + 1;
    }

}
