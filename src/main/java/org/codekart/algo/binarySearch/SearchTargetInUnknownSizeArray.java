package org.codekart.algo.binarySearch;

public class SearchTargetInUnknownSizeArray {

    interface ArrayReader {
        public int get(int index);
    }

    // leetcode 702 - Search in a Sorted Array of Unknown Size
    public int search(ArrayReader reader, int target) {
        int left = 0;
        int right = 10000;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = reader.get(mid);
            if (value == target) {
                return mid;
            } else if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /*
     * Arbitrary Upper Bound: The hard-coded value of 10000 for the right boundary
     * is problematic:
     * 
     * What if the target is at index 15000?
     * What if the array is much smaller? We'll waste time checking invalid indices.
     * 
     * 
     * Missing Problem Context: The solution doesn't account for the typical
     * behavior of ArrayReader.get() for out-of-bounds access. These interfaces
     * typically return a sentinel value (often Integer.MAX_VALUE) for out-of-bounds
     * accesses.
     */

    // Find the upper bound dynamically using an exponential search strategy
    public int searchOptimised(ArrayReader reader, int target) {
        int right = 1;
        int left = 0;

        while (reader.get(right) < Integer.MAX_VALUE && reader.get(right) < target) {
            left = right;
            right = right * 2;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = reader.get(mid);
            if (value == target) {
                return mid;
            } else if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
