package org.codekart.arrays;

import java.util.Deque;
import java.util.LinkedList;

public class LongestSubArray {

    public int longestSubarray(int[] nums, int limit) {
        // Deque to store max values in decreasing order
        Deque<Integer> maxDeque = new LinkedList<>();
        // Deque to store min values in increasing order
        Deque<Integer> minDeque = new LinkedList<>();

        int left = 0; // Left pointer of the sliding window
        int longest = 0; // Variable to store the length of the longest subarray

        // Iterate through the array using the right pointer -
        for (int right = 0; right < nums.length; right++) {
            // Maintain the decreasing order in maxDeque
            while (!maxDeque.isEmpty() && nums[right] > maxDeque.peekLast()) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(nums[right]);

            // Maintain the increasing order in minDeque -
            while (!minDeque.isEmpty() && nums[right] < minDeque.peekLast()) {
                minDeque.pollLast();
            }
            minDeque.offerLast(nums[right]);

            // If the absolute difference between max and min exceeds the limit, shrink the
            // window
            while (!maxDeque.isEmpty() && !minDeque.isEmpty() &&
                    (maxDeque.peekFirst() - minDeque.peekFirst()) > limit) {
                // Shrink the window from the left
                if (nums[left] == maxDeque.peekFirst()) {
                    maxDeque.pollFirst();
                }
                if (nums[left] == minDeque.peekFirst()) {
                    minDeque.pollFirst();
                }
                left++; // Move the left pointer to shrink the window
            }

            // Update the longest subarray length
            longest = Math.max(longest, right - left + 1);
        }

        return longest;
    }

}
