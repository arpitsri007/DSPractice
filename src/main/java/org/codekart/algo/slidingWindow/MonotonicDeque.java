package org.codekart.algo.slidingWindow;

import java.util.Deque;
import java.util.LinkedList;

public class MonotonicDeque {

    // sliding window maximum using brute force
    public static int[] slidingWindowMaximum(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];

        for(int i = 0; i <= n - k; i++){
            int max = nums[i];
            for(int j = i; j < i + k; j++){
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }

    // sliding window maximum using monotonic deque
    public static int[] slidingWindowMaximumUsingDeque(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];

        Deque<Integer> deque = new LinkedList<>();

        for(int i = 0; i < n; i++){

            // step1 : remove elements from the deque that are out of the current window
            while(!deque.isEmpty() && deque.peekFirst() <= i - k){
                deque.pollFirst();
            }

            // step2 : remove elements from the deque that are less than the current element because those can never be the maximum in the current window
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }

            // step3 : add the current element to the deque - monotonic decreasing deque
            deque.offerLast(i);

            if(i >= k - 1){
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
    
}
