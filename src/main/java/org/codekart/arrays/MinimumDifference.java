package org.codekart.arrays;

import java.util.Collections;
import java.util.PriorityQueue;

public class MinimumDifference {
    // leetcode 2163 - Minimum Difference in Sums After Removal of Elements
    public long minimumDifference(int[] nums) {
        int n = nums.length;
        int k = n / 3;
        long[] leftMin = new long[n];
        long[] rightMax = new long[n];

        PriorityQueue<Integer> leftQueue = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> rightQueue = new PriorityQueue<>();

        long leftSum = 0;
        long rightSum = 0;

        for (int i = 0; i < n-k; i++) {
            leftQueue.add(nums[i]);
            leftSum += nums[i];
            if(leftQueue.size() > k) {
                leftSum -= leftQueue.poll();
            }
            leftMin[i] = leftSum;
        }

        for (int i = n-1; i >= k; i--) {
            rightQueue.add(nums[i]);
            rightSum += nums[i];
            if(rightQueue.size() > k) {
                rightSum -= rightQueue.poll();
            }
            rightMax[i] = rightSum;
        }

        long minDiff = Long.MAX_VALUE;
        for (int i = k-1; i < n-k; i++) {
            minDiff = Math.min(minDiff, leftMin[i] - rightMax[i+1]);
        }
        return minDiff;
    }

    // main method
    public static void main(String[] args) {
        MinimumDifference md = new MinimumDifference();
        int[] nums = { 7, 9, 5, 8, 1, 3 };
        System.out.println(md.minimumDifference(nums));
    }
}
