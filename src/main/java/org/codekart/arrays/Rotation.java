package org.codekart.arrays;

public class Rotation {
    /*
     * Given an integer array arr[] of size n. The task is to find the maximum value
     * of the sum of the value of i * arr[i] where i varies from 0 to n-1. The only
     * operation allowed is to rotate the array any number of times.
     */

    // Naive Approach
    // TC: O(n^2) - SC: O(1)
    public int maxSum(int[] arr) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                // compute the rotated index
                int rotatedIndex = (i + j) % n;
                sum += j * arr[rotatedIndex];
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    // Optimized Approach
    // TC: O(n) - SC: O(1)
    // Idea:
    // 1. Compute the total sum of the array
    // 2. Compute the current sum of the array
    // 3. Compute the maximum sum of the array
    // 4. Return the maximum sum
    // Formula :
    // currSum = currSum - prevSum + n-1 * arr[n-i-1]
    public int maxSumOptimized(int[] arr) {
        int n = arr.length;

        // compute sum of all array elements
        int currSum = 0;
        for (int i = 0; i < n; i++) {
            currSum += arr[i];
        }

        // compute the value of i * arr[i] for the first rotation
        int currValue = 0;
        for (int i = 0; i < n; i++) {
            currValue += i * arr[i];
        }

        int result = currValue;

        for (int i = 1; i < n; i++) {

            // compute next value using previous value
            int nextValue = currValue - (currSum - arr[i - 1]) + (n - 1) * arr[i - 1];

            // update current value
            currValue = nextValue;

            // update result
            result = Math.max(result, currValue);
        }

        return result;

    }
}
