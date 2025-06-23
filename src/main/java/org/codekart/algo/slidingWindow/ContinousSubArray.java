package org.codekart.algo.slidingWindow;

import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import util.Pair;

public class ContinousSubArray {

    // leetcode 2762 - continuous subarray sum
    // Let i, i + 1, ..., j be the indices in the subarray. Then, for each pair of
    // indices i <= i1, i2 <= j, 0 <= |nums[i1] - nums[i2]| <= 2.
    // Return the total number of continuous subarrays.
    // brute force - using two loops
    public static int continuousSubarray(int[] nums) {
        int n = nums.length;
        int result = 0;

        for (int i = 0; i < n; i++) {
            int max = nums[i];
            int min = nums[i];
            for (int j = i; j < n; j++) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                if (max - min <= 2) {
                    result++;
                }
            }
        }
        return result;
    }

    // TC: O(n) - given the map is always max 3 elements. otherwise in general
    // insert/delete operation is O(logn)
    // SC: O(1) - given the map is always max 3 elements.

    // using sliding window
    public static long continuousSubarraySW(int[] nums) {
        int n = nums.length;
        long result = 0;

        TreeMap<Integer, Integer> mp = new TreeMap<>();

        int i = 0;
        int j = 0;

        while (j < n) {
            mp.put(nums[j], mp.getOrDefault(nums[j], 0) + 1);

            // while the window is invalid i.e |max - min| > 2, shrink the window
            while (mp.lastKey() - mp.firstKey() > 2) {
                mp.put(nums[i], mp.get(nums[i]) - 1);
                if (mp.get(nums[i]) == 0) {
                    mp.remove(nums[i]);
                }
                i++;
            }

            result += j - i + 1;
            j++;
        }
        return result;
    }

    // instead of using tree map, we can use two pointers to keep track of the max
    // and min
    public static long continuousSubarraySW2(int[] nums) {
        int n = nums.length;
        long result = 0;

        int i = 0;
        int j = 0;

        int max = nums[0];
        int min = nums[0];

        while (j < n) {
            if (nums[j] > max) {
                max = nums[j];
            }
            if (nums[j] < min) {
                min = nums[j];
            }

            if (max - min <= 2) {
                result += j - i + 1;
            } else {
                while (i <= j && (max - min) > 2) {
                    if (nums[i] == max) {
                        max = nums[i + 1];
                    }
                    if (nums[i] == min) {
                        min = nums[i + 1];
                    }
                    i++;
                }
                result += j - i + 1;
            }
            j++;
        }

        return result;
    }

    // use min and max heap to keep track of the max and min
    public static long continuousSubarraySW3(int[] nums) {
        int n = nums.length;
        long result = 0;

        PriorityQueue<Pair<Integer, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getFirst() - a.getFirst()); // Pair<value, index>
        PriorityQueue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> a.getFirst() - b.getFirst());

        int i = 0;
        int j = 0;

        while (j < n) {
            maxHeap.add(new Pair<>(nums[j], j));
            minHeap.add(new Pair<>(nums[j], j));

            int diff = maxHeap.peek().getFirst() - minHeap.peek().getFirst();

            while (diff > 2) {
                i++;
                while (maxHeap.peek().getSecond() < i) {
                    maxHeap.poll();
                }
                while (minHeap.peek().getSecond() < i) {
                    minHeap.poll();
                }
                diff = maxHeap.peek().getFirst() - minHeap.peek().getFirst();
            }

            result += j - i + 1;
            j++;
        }
        return result;
    }

    // main method
    public static void main(String[] args) {
        int[] nums = { 42, 41, 42, 41, 41, 40, 39, 38 };
        // System.out.println(continuousSubarray(nums));
        // System.out.println(continuousSubarraySW(nums));
        System.out.println(continuousSubarraySW2(nums));
    }

}
