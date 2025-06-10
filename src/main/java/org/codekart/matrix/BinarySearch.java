package org.codekart.matrix;

public class BinarySearch {
    // search in a sorted matrix
    public static boolean searchInSortedMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int first = 0;
        int last = n - 1;

        while (first <= last) {
            int mid = (first + last) / 2;

            if (matrix[mid][0] <= target && matrix[mid][m - 1] >= target) {
                return binarySearch(matrix[mid], target);
            }
            if (matrix[mid][0] < target) {
                first = mid + 1;
            }

            if (matrix[mid][0] > target) {
                last = mid - 1;
            }
        }

        return false;
    }

    private static boolean binarySearch(int[] matrix, int target) {
        int first = 0;
        int last = matrix.length - 1;

        while (first <= last) {
            int mid = (first + last) / 2;

            if (matrix[mid] == target) {
                return true;
            }

            if (matrix[mid] < target) {
                first = mid + 1;
            }

            if (matrix[mid] > target) {
                last = mid - 1;
            }
        }
        return false;
    }
}