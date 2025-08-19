package org.codekart.algo.binarySearch;

import java.util.ArrayList;
import java.util.List;

public class MaximumFind {
    // leetcode 2106

    // TC: O(n + klogn)
    // SC: O(n)
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;

        if (n == 0 ) {
            return 0;
        }

        List<Integer> positions = new ArrayList<>();
        List<Integer> prefixSum = new ArrayList<>();

        int sum = 0;

        // precompute the prefix sum and positions
        // TC: O(n)
        // SC: O(n)
        for (int i = 0; i < n; i++) {
            int[] fruit = fruits[i];
            int pos = fruit[0];
            int count = fruit[1];
            sum += count;
            prefixSum.add(sum);
            positions.add(pos);
        }

        // case 1 : first go left then right;
        // i -> left position --> startPos - d
        // remaining steps = k - 2*d
        // d range = 0 to k/2

        int maxFruits = 0;

        // TC: O(klogn)
        // SC: O(1)
        for (int d = 0; d <= k / 2; d++) {
            int left = startPos - d;
            int right = startPos + (k - 2 * d);
            int leftIndex = binarySearchLowerBound(positions, left);
            int rightIndex = binarySearchUpperBound(positions, right);

            if (leftIndex <= rightIndex && leftIndex < n && rightIndex >= 0) {
                int currentFruits = prefixSum.get(rightIndex) - (leftIndex > 0 ? prefixSum.get(leftIndex - 1) : 0);
                maxFruits = Math.max(maxFruits, currentFruits);
            }
        }

        // case 2: first go right then left;
        // j -> right position --> startPos + d
        // remaining steps = k - 2*d
        // d range = 0 to k/2
        // i -> left position --> startPos - (k - 2*d)
        // d range = 0 to k/2

        for (int d = 0; d <= k / 2; d++) {
            int right = startPos + d;
            int left = startPos - (k - 2 * d);
            int leftIndex = binarySearchLowerBound(positions, left); // leftindex is the first index where the position
                                                                     // is greater
            // than or equal to left
            int rightIndex = binarySearchUpperBound(positions, right) - 1; // rightindex is the first index where the
                                                                           // position is
            // greater than or equal to right
            if (leftIndex <= rightIndex && leftIndex < n && rightIndex >= 0) {
                int currentFruits = prefixSum.get(rightIndex) - prefixSum.get(leftIndex - 1);
                maxFruits = Math.max(maxFruits, currentFruits);
            }
        }

        return maxFruits;
    }

    // first index where position[index] >= target
    private int binarySearchLowerBound(List<Integer> positions, int target) {
        int left = 0;
        int right = positions.size() - 1;
        int pos = positions.size(); // Default value if not found
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (positions.get(mid) >= target) {
                pos = mid;
                right = mid - 1; // continue searching in the left half for better result
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }

    // find last index where position[index] <= target
    private int binarySearchUpperBound(List<Integer> positions, int target) {
        int left = 0;
        int right = positions.size() - 1;
        int pos = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (positions.get(mid) <= target) {
                pos = mid;
                left = mid + 1; // continue searching in the right half for better result
            } else {
                right = mid - 1;
            }
        }
        return pos;
    }

    // main method
    public static void main(String[] args) {
        MaximumFind maximumFind = new MaximumFind();
        int[][] fruits = { { 2, 8 }, { 6, 3 }, { 8, 6 } };
        int startPos = 5;
        int k = 4;
        System.out.println(maximumFind.maxTotalFruits(fruits, startPos, k));
    }

}
