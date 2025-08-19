package org.codekart.segmentTree;

import java.util.Arrays;

public class BuildSegmentTree {
    /*
     * Efficient way to answer range queries like sum, min, max, gcd, etc.
     * 1. It is a binary tree where each node represents a range of the array.
     * 2. 2 children of all non leaf nodes.
     * 3. leaf Nodes - Represents a single element of the array.
     * 4. Non leaf nodes - Represents a range of the array
     * 5. Root node - Represents the entire array
     * 6. Height of the tree is log(n)
     * 7. It is balanced binary tree which means that the left and right subtree of
     * any node differ in height by at most 1.
     * 8. Number of nodes in the tree is 2n - 1 or 2n.
     */

    // Build Segment Tree
    // TC - O(n) - n is the number of elements in the array and we are visiting each
    // element almost twice.
    // SC - O(n) - for segment tree and O(logn) for recursion stack
    public static void buildSegmentTree(int[] arr, int[] segmentTree, int index, int left, int right) {
        if (left == right) {
            segmentTree[index] = arr[left];
            return;
        }
        int mid = (left + right) / 2;
        buildSegmentTree(arr, segmentTree, 2 * index + 1, left, mid);
        buildSegmentTree(arr, segmentTree, 2 * index + 2, mid + 1, right);
        segmentTree[index] = segmentTree[2 * index + 1] + segmentTree[2 * index + 2]; // Range sum query
    }

    // Update Segment Tree
    // TC - O(logn) - n is the number of elements in the array and we are visiting
    // each element almost twice.
    // SC - O(logn) - for recursion stack
    public static void updateSegmentTree(int[] arr, int[] segmentTree, int index, int left, int right, int pos,
            int value) {
        if (left == right) {
            arr[pos] = value;
            segmentTree[index] = value;
            return;
        }

        int mid = (left + right) / 2;
        if (pos <= mid) {
            updateSegmentTree(arr, segmentTree, 2 * index + 1, left, mid, pos, value);
        } else {
            updateSegmentTree(arr, segmentTree, 2 * index + 2, mid + 1, right, pos, value);
        }
        segmentTree[index] = segmentTree[2 * index + 1] + segmentTree[2 * index + 2]; // Range sum query
    }

    /* Updating entire range of indexes in segment tree */
    public static void updateRangeSum(int start, int end, int value, int index, int left, int right,
            int[] segmentTree, int[] lazyTree) {
        if (lazyTree[index] != 0) {
            segmentTree[index] += (right - left + 1) * lazyTree[index];
            if (left != right) {
                lazyTree[2 * index + 1] += lazyTree[index];
                lazyTree[2 * index + 2] += lazyTree[index];
            }
            lazyTree[index] = 0;
        }

        // out of bounds
        if (start > right || end < left) {
            return;
        }

        // update range is entirely covered within left to right range
        if (start <= left && end >= right) {
            segmentTree[index] += (right - left + 1) * value;
            if (left != right) { // not a leaf node
                lazyTree[2 * index + 1] += value;
                lazyTree[2 * index + 2] += value;
            }
            return;
        }

        // partial overlap
        int mid = (left + right) / 2;
        updateRangeSum(start, end, value, 2 * index + 1, left, mid, segmentTree, lazyTree);
        updateRangeSum(start, end, value, 2 * index + 2, mid + 1, right, segmentTree, lazyTree);
        segmentTree[index] = segmentTree[2 * index + 1] + segmentTree[2 * index + 2];
    }

    // Query Segment Tree
    // TC - O(logn) - n is the number of elements in the array and we are visiting
    // each element almost twice.
    // SC - O(logn) - for recursion stack
    public static int findRangeSum(int[] arr, int[] segmentTree, int index, int startQuery, int endQuery, int left,
            int right) {

        // out of bounds
        if (startQuery > right || endQuery < left) {
            return 0;
        }

        // complete overlap
        if (startQuery <= left && endQuery >= right) { // this node contains sum from left to right range so we can take
                                                       // this node value for the sum
            return segmentTree[index]; // | ---startQuery--- left ----- right --- endQuery ---|
        }

        // partial overlap
        int mid = (left + right) / 2;
        int leftSum = findRangeSum(arr, segmentTree, 2 * index + 1, startQuery, endQuery, left, mid);
        int rightSum = findRangeSum(arr, segmentTree, 2 * index + 2, startQuery, endQuery, mid + 1, right);
        return leftSum + rightSum;
    }

    // main method
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        int[] segmentTree = new int[2 * arr.length];
        buildSegmentTree(arr, segmentTree, 0, 0, arr.length - 1);
        System.out.println(Arrays.toString(segmentTree));
        System.out.println(findRangeSum(arr, segmentTree, 0, 0, 2, 0, arr.length - 1));
    }

}
