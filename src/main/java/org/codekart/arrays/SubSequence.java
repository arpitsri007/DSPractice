package org.codekart.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class SubSequence {
    // leetcode 2099 - Find Subsequence of Length K with the Largest Sum
    public int[] maxSubsequence(int[] nums, int k) {
        int[] result = new int[k];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(k, (a, b) -> b[0] - a[0]);

        for (int i = 0; i < nums.length; i++) {
            if (maxHeap.size() < k) {
                maxHeap.add(new int[] { nums[i], i });
            } else {
                if (maxHeap.peek()[0] < nums[i]) {
                    maxHeap.poll();
                    maxHeap.add(new int[] { nums[i], i });
                }
            }
        }

        List<int[]> list = new ArrayList<int[]>();
        for (int i = 0; i < k; i++) {
            list.add(maxHeap.poll());
        }

        Collections.sort(list, (a, b) -> a[1] - b[1]);

        for (int i = 0; i < k; i++) {
            result[i] = list.get(i)[0];
        } 

        return result;
    }

    // main method
    public static void main(String[] args) {
        SubSequence subSequence = new SubSequence();
        int[] nums = { 3, 4, 3, 3 };
        int k = 2;
        int[] result = subSequence.maxSubsequence(nums, k);
        System.out.println(Arrays.toString(result));
    }
}
