package org.codekart.arrays;

import java.util.Arrays;

public class IntervalType {
    // leetcode - 757 Set Intersection Size At Least Two
    public int intersectionSizeTwo(int[][] intervals) {
     
        int result = 0;
        int first = -1;
        int second = -1;

        Arrays.sort(intervals, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1]; // sort in ascending order of end time
            } else {
                return b[0] - a[0]; // sort in descending order of start time
            }
        });

        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];

            if (left <= first) {
                continue;
            }
            if (left > second) {
                result += 2;
                first = right - 1;
                second = right;
            } else if (left <= second) {
                result += 1;
                first = second;
                second = right;
            }
        }

        return result;
    }
}
