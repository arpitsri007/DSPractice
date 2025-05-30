package org.codekart.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MedianFinder {
    // Heap is a complete binary tree.
    // Heap is either min heap or max heap.
    // a binary heap where the parent node's value is always less than or equal to
    // its children's values, ensuring the root node holds the minimum element

    // Max heap: a binary heap where the parent node's value is always greater than
    // or equal to its children's values, ensuring the root node holds the maximum
    // element

    // Heapify: the process of converting a binary tree into a heap.

    // Heap sort: a sorting algorithm that uses a heap to sort the elements.

    private List<Integer> nums;

    public MedianFinder() {
        nums = new ArrayList<>();
    }

    // TC: O(n)
    public void addNum(int num) {
        int position = 0;

        while (position < nums.size() && nums.get(position) < num) {
            position++;
        }

        nums.add(position, num);
    }

    // TC: O(1)
    public double findMedian() {
        int size = nums.size();
        if (size % 2 == 0) {
            return (nums.get(size / 2 - 1) + nums.get(size / 2)) / 2.0;
        }
        return nums.get(size / 2);
    }

    // with binary search:
    public static class MedianFinderBinarySearch {
        private List<Integer> nums;

        public MedianFinderBinarySearch() {
            nums = new ArrayList<>();
        }

        // TC: O(log n)
        public void addNum(int num) {
            int left = 0;
            int right = nums.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (nums.get(mid) < num) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            nums.add(left, num);
        }

        // TC: O(1)
        public double findMedian() {
            int size = nums.size();
            if (size % 2 == 0) {
                return (nums.get(size / 2 - 1) + nums.get(size / 2)) / 2.0;
            }
            return nums.get(size / 2);
        }
    }

    // with two heaps:
    public static class MedianFinderTwoHeaps {
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        public MedianFinderTwoHeaps() {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        // TC: O(log n)
        public void addNum(int num) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            // balance the heaps
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        MedianFinderBinarySearch medianFinderBinarySearch = new MedianFinderBinarySearch();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian()); // Overall TC: O(N^2)
        System.out.println(medianFinderBinarySearch.findMedian()); // Overall TC: O(N^2)
    }

}