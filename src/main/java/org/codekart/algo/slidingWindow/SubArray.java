package org.codekart.algo.slidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubArray {

    // leetcode 2444: Count Subarrays With Fixed Bounds
    // brute force:
    // 1. generate all subarrays
    // 2. check if the subarray is valid
    // 3. count the number of valid subarrays
    // time complexity: O(n^3)
    // space complexity: O(1)
    public static int countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isValidSubarray(nums, i, j, minK, maxK)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isValidSubarray(int[] nums, int i, int j, int minK, int maxK) {
        for (int k = i; k <= j; k++) {
            if (nums[k] < minK || nums[k] > maxK) {
                return false;
            }
        }
        return true;
    }

    // sliding window:
    public static int countSubarraysSW(int[] nums, int minK, int maxK) {
        int maxkPosition = -1;
        int minkPosition = -1;
        int culpritIndex = -1;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                culpritIndex = i;
            }
            if (nums[i] == minK) {
                minkPosition = i;
            }
            if (nums[i] == maxK) {
                maxkPosition = i;
            }

            int left = Math.min(minkPosition, maxkPosition);
            long temp = left - culpritIndex;
            if (temp > 0) {
                result += temp;
            }

        }

        return result;

    }

    // K radius subarray avg brute force
    public static int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (i - k < 0 || i + k >= n) {
                result[i] = -1;
                continue;
            }
            int sum = 0;
            for (int j = i - k; j <= i + k; j++) {
                sum += nums[j];
            }
            result[i] = sum / (2 * k + 1);
        }
        return result;
    }

    // K radius subarray avg sliding window
    public static int[] getAveragesSW(int[] nums, int k) {
        int n = nums.length;

        if (k == 0) {
            return nums;
        }

        int[] result = new int[n];
        Arrays.fill(result, -1);
        int windowSize = 2 * k + 1;
        int windowSum = 0;
        if (windowSize > n) {
            return result;
        }
        int left = 0;
        int right = 2 * k;
        int i = k;

        for (int j = 0; j <= right; j++) {
            windowSum += nums[j];
        }
        result[k] = windowSum / windowSize;

        right++;
        i++;

        while (right < n) {
            int outgoing = nums[left];
            int incoming = nums[right];
            windowSum = windowSum - outgoing + incoming;
            right++;
            left++;
            result[i] = windowSum / windowSize;
            i++;

        }
        return result;
    }

    // Longest subarray of 1's after deleting one element brute force
    public static int longestSubarray(int[] nums) {
        int n = nums.length;
        int result = 0;
        int zeroCount = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                result = Math.max(result, findMaxSubArrayOf1(nums, i));
            }

        }
        return zeroCount == 0 ? n - 1 : result;
    }

    private static int findMaxSubArrayOf1(int[] nums, int skipIndex) {
        int maxLength = 0;
        int currentLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == skipIndex) {
                continue;
            }
            if (nums[i] == 1) {
                currentLength++;
            } else {
                currentLength = 0;
            }
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }

    // Longest subarray of 1's after deleting one element sliding window
    public static int longestSubarraySW(int[] nums) {
        int n = nums.length;
        int result = 0;
        int zeroCount = 0;
        int left = 0;
        int right = 0;

        while (right < n) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            result = Math.max(result, right - left);
            right++;
        }
        return result == n ? n - 1 : result;
    }

    // Longest subarray of 1's after deleting one element sliding window better
    // sliding window
    public static int longestSubarraySWBetter(int[] nums) {
        int n = nums.length;
        int result = 0;
        int lastZeroIndex = -1;
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (nums[right] == 0) {
                left = lastZeroIndex + 1;
                lastZeroIndex = right;
            }

            result = Math.max(result, right - left);
        }
        return result;
    }

    // brute force - leetcode 930
    public int numSubarraysWithSum(int[] nums, int goal) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int currsum = nums[i];
            for (int j = i; j < n; j++) {
                if (i != j)
                    currsum += nums[j];
                if (currsum == goal) {
                    count++;
                }
            }
        }

        return count;
    }

    // using map- leetcode 930
    public int numSubarraysWithSumMap(int[] nums, int goal) {
        int count = 0;
        int n = nums.length;

        Map<Integer, Integer> prefixSumMap = new HashMap<>();

        prefixSumMap.put(0, 1);
        int prefixSum = 0;
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            int target = prefixSum - goal;
            count += prefixSumMap.getOrDefault(target, 0);
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    // 1074. Number of Submatrices That Sum to Target - brute force
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int count = 0;

        for (int startRow = 0; startRow < rows; startRow++) {
            for (int startCol = 0; startCol < cols; startCol++) {
                for (int endRow = startRow; endRow < rows; endRow++) {
                    for (int endCol = startCol; endCol < cols; endCol++) {
                        int sum = 0;
                        for (int row = startRow; row <= endRow; row++) {
                            for (int col = startCol; col <= endCol; col++) {
                                sum += matrix[row][col];
                            }
                        }
                        if (sum == target) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static int numSubarraysWithSumSW(int[] nums, int goal) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        int result = 0;
        int currSum = 0;
        int zeroCount = 0;

        while (j < n) {
            currSum += nums[j];

            while (i < j && (nums[i] == 0 || currSum > goal)) {

                if (nums[i] == 0) {
                    zeroCount++;
                } else {
                    zeroCount = 0;
                }
                currSum -= nums[i];
                i++;
            }

            if (currSum == goal) {
                result += zeroCount + 1;
            }

            j++;
        }

        return result;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;

        int i = 0;
        int j = 0;
        int prod = 1;
        int count = 0;

        while (j < n) {
            prod = prod * nums[j];

            while (i < j && prod >= k) {
                prod = prod / nums[i];
                i++;
            }

            if (prod < k) {
                count += j - i + 1;
            }

            j++;
        }

        return count;
    }

    public static int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;

        int i = 0;
        int j = 0;
        int result = 0;

        Map<Integer, Integer> mp = new HashMap<>();

        while (j < n) {
            int updatedFreq = mp.getOrDefault(nums[j], 0) + 1;
            mp.put(nums[j], updatedFreq);

            while (i < j && mp.getOrDefault(nums[j], 0) > k) {
                int iFreq = mp.getOrDefault(nums[i], 0) - 1;
                mp.put(nums[i], iFreq);
                i++;
            }

            result = Math.max(result, j - i + 1);

            j++;
        }

        return result;
    }

    public static int maxSubarrayLengthApproach2(int[] nums, int k) {
        int n = nums.length;

        int i = 0;
        int j = 0;
        int result = 0;
        int culprit = 0;

        Map<Integer, Integer> mp = new HashMap<>();

        while (j < n) {
            int updatedFreq = mp.getOrDefault(nums[j], 0) + 1;
            mp.put(nums[j], updatedFreq);

            if (updatedFreq == k + 1) {
                culprit++;
            }

            if (culprit > 0) {
                int iFreq = mp.getOrDefault(nums[i], 0) - 1;
                mp.put(nums[i], iFreq);
                if (iFreq == k) {
                    culprit--;
                }
                i++;
            }

            if (culprit == 0) {
                result = Math.max(result, j - i + 1);
            }

            j++;
        }

        return result;
    }

    // leetcode 2962
    public static long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        long result = 0;

        int maxElem = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            maxElem = Math.max(maxElem, nums[i]);
        }

        int i = 0;
        int maxfreq = 0;
        int j = 0;

        while (j < n) {

            if (nums[j] == maxElem) {
                maxfreq++;
            }

            while (i <= j && maxfreq >= k) {
                result += n - j;
                if (nums[i] == maxElem) {
                    maxfreq--;
                }
                i++;
            }
            j++;
        }
        return result;
    }

    public static long countSubarraysApproach2(int[] nums, int k) {
        int n = nums.length;
        long result = 0;

        int maxElem = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            maxElem = Math.max(maxElem, nums[i]);
        }

        List<Integer> maxIndices = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (nums[i] == maxElem) {
                maxIndices.add(i);
            }

            int size = maxIndices.size();

            if (size > k) {
                int last_i = maxIndices.get(size - k);
                result += last_i + 1;
            }

        }

        return result;
    }

    // leetcode 209 - Minimum Size Subarray Sum
    // Time Complexity: O(n)
    public static int minSubArrayLen(int target, int[] nums) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        int n = nums.length;

        while (j < n) {
            sum += nums[j];
            while (sum >= target) {
                minLength = Math.min(minLength, j - i + 1);
                sum -= nums[i];
                i++;
            }
            j++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // leetcode 992
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return solver(nums, k) - solver(nums, k - 1);
    }

    private static int solver(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        int count = 0;

        Map<Integer, Integer> mp = new HashMap<>();

        while (j < n) {

            int updatedFreq = mp.getOrDefault(nums[j], 0) + 1;

            mp.put(nums[j], updatedFreq);

            while (mp.size() > k) {
                mp.put(nums[i], mp.getOrDefault(nums[i], 0) - 1);
                if (mp.getOrDefault(nums[i], 0) <= 0) {
                    mp.remove(nums[i]);
                }
                i++;
            }

            count += j - i + 1;
            j++;
        }

        return count;

    }

    // leetcode 992 - subarrays with k distinct integers with one pass
    public static int subarraysWithKDistinctOnePass(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        int result = 0;
        int i_big = 0;

        Map<Integer, Integer> mp = new HashMap<>();

        while (j < n) {
            int updatedFreq = mp.getOrDefault(nums[j], 0) + 1;
            mp.put(nums[j], updatedFreq);

            // Invalid window - shrink the window
            while (mp.size() > k) {
                mp.put(nums[i], mp.getOrDefault(nums[i], 0) - 1);
                if (mp.getOrDefault(nums[i], 0) <= 0) {
                    mp.remove(nums[i]);
                }
                i++;
                i_big = i;
            }

            // Valid window - find the smallest valid window

            while (mp.get(nums[i]) > 1) {
                mp.put(nums[i], mp.getOrDefault(nums[i], 0) - 1);
                i++;

            }

            if (mp.size() == k) {
                result += i - i_big + 1;
            }

            j++;
        }

        return result;

    }

    // leetcode 862 - Shortest Subarray with Sum at Least K
    // Time Complexity: O(n)
    // this problem is similar to the problem of minimum subArray len with target
    // sum with a twist it has negative numbers
    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> dq = new LinkedList<>(); // stores index in monotonic increasing order of cumulative sum
        long[] cumulativeSum = new long[n + 1];

        int result = Integer.MAX_VALUE;
        int j = 0;
        while (j < n) {
            if (j == 0) {
                cumulativeSum[j] = nums[j];
            } else {
                cumulativeSum[j] = cumulativeSum[j - 1] + nums[j];
            }

            if (cumulativeSum[j] >= k) {
                result = Math.min(result, j + 1);
            }

            // check if need to shrink the window
            while (!dq.isEmpty() && cumulativeSum[j] - cumulativeSum[dq.peekFirst()] >= k) {
                result = Math.min(result, j - dq.peekFirst());
                dq.pollFirst();
            }

            while (!dq.isEmpty() && cumulativeSum[j] <= cumulativeSum[dq.peekFirst()]) {
                dq.pollFirst();
            }

            dq.addLast(j);

            j++;
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    // leetcode 2461 - maximum sum of distinct subarrays with length at least k
    // brute force:
    // 1. generate all subarrays
    // 2. check if the subarray is valid
    // 3. return the maximum sum of valid subarrays
    // time complexity: O(n^2)
    // space complexity: O(1)
    public static int maxSumOfSubarray(int[] nums, int k) {
        int n = nums.length;
        int result = Integer.MIN_VALUE;

        for (int i = 0; i + k - 1 < n; i++) {
            int j = i + k - 1;
            int sum = 0;
            Set<Integer> set = new HashSet<>();
            for (int l = i; l <= j; l++) {
                if (set.contains(nums[l])) {
                    sum = 0;
                    break;
                }
                set.add(nums[l]);
                sum += nums[l];
            }
            result = Math.max(result, sum);
        }

        return result;
    }

    // leetcode 2461 - maximum sum of distinct subarrays with length at least k -
    // using sliding window
    public static long maxSumOfSubarraySW(int[] nums, int k) {
        int n = nums.length;
        long result = 0;

        int i = 0;
        int j = 0;
        long sum = 0;

        Set<Integer> set = new HashSet<>();

        while (j < n) {
            // Invalid window - shrink the window
            while (set.contains(nums[j])) {
                set.remove(nums[i]);
                sum -= nums[i];
                i++;
            }

            // Valid window - add the current element
            set.add(nums[j]);
            sum += nums[j];

            if (j - i + 1 == k) {
                result = Math.max(result, sum);
                set.remove(nums[i]);
                sum -= nums[i];
                i++;
            }

            j++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 5, 4, 2, 9, 9, 9 };
        int k = 3;
        long result = maxSumOfSubarraySW(nums, k);
        System.out.println(result);
    }
}

/*
 * Tips
 * total number of subarrays = n * (n + 1) / 2
 * total number of subarray ending at index j from index i = j - i +1;
 */
