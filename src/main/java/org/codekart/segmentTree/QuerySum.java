package org.codekart.segmentTree;

import java.util.ArrayList;
import java.util.List;

public class QuerySum {

    public List<Integer> query(int[] arr, int[][] queries) {
        int[] segmentTree = new int[4 * arr.length];
        List<Integer> results = new ArrayList<>();
        int qCount = queries.length;
        BuildSegmentTree.buildSegmentTree(arr, segmentTree, 0, 0, arr.length - 1);
        for (int i = 0; i < qCount; i++) {
            int startQuery = queries[i][0];
            int endQuery = queries[i][1];
            int result = BuildSegmentTree.findRangeSum(arr, segmentTree, 0, startQuery, endQuery, 0,
                    arr.length - 1);
            results.add(result);
        }
        return results;
    }

    // leetcode 307. Range Sum Query - Mutable
    // https://leetcode.com/problems/range-sum-query-mutable/
    class NumArray {

        int n;
        int[] segmentTree;
        int[] lazyTree;
        int[] nums;

        public NumArray(int[] nums) {
            n = nums.length;
            segmentTree = new int[4 * n];
            lazyTree = new int[4 * n];
            this.nums = nums;
            BuildSegmentTree.buildSegmentTree(nums, segmentTree, 0, 0, n - 1);
        }

        public void update(int index, int val) {
            BuildSegmentTree.updateSegmentTree(nums, segmentTree, 0, 0, n - 1, index, val);
        }

        public int sumRange(int left, int right) {
            return BuildSegmentTree.findRangeSum(nums, segmentTree, 0, left, right, 0, n - 1);
        }

    }

}
