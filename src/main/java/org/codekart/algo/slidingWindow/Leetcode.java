package org.codekart.algo.slidingWindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leetcode {
    public static void main(String[] args) {
        // String answerKey = "TTFTTFTT";
        // int k = 1;
        // System.out.println(maximiseConfusionSWOptimised(answerKey, k));
        int[] nums = new int[] { 3, 9, 6 };
        int k = 2;
       // System.out.println(maxFrequencySlidingWindow(nums, k));
        System.out.println(countCompleteSubstrings("gvgvvgv", 2));


    }

    // leetcode 2024 using brute force
    public static int maximiseConfusion(String answerKey, int k) {
        return Math.max(solve(answerKey, k, 'T'), solve(answerKey, k, 'F'));
    }

    private static int solve(String answerKey, int k, char c) {
        // brute force
        int maxConsecutive = 0;
        int n = answerKey.length();

        for (int start = 0; start < n; start++) {
            int changes = 0;
            for (int end = start; end < n; end++) {
                if (answerKey.charAt(end) != c) {
                    changes++;
                }
                // if we've used more than k changes then this subarray is not valid
                if (changes > k) {
                    break;
                }
                // update maximum length of consecutive characters
                maxConsecutive = Math.max(maxConsecutive, end - start + 1);
            }
        }
        return maxConsecutive;
    }

    // leetcode 2024 using sliding window - 2 pass sliding window
    public static int maximiseConfusionSWBetter(String answerKey, int k) {
        return Math.max(maximiseConfusionSW(answerKey, k, 'T'), maximiseConfusionSW(answerKey, k, 'F'));
    }

    // sliding window
    public static int maximiseConfusionSW(String answerKey, int k, char target) {
        int left = 0;
        int right = 0;
        int result = 0;
        int changes = 0;
        int n = answerKey.length();

        while (right < n) {
            if (answerKey.charAt(right) != target) {
                changes++;
            }
            while (changes > k) {
                if (answerKey.charAt(left) != target) {
                    changes--;
                }
                left++;
            }
            result = Math.max(result, right - left + 1);
            right++;
        }
        return result;
    }

    // leetcode 2024 using sliding window - 1 pass sliding window using map
    public static int maximiseConfusionSWOptimised(String answerKey, int k) {
        int left = 0;
        int right = 0;
        int result = 0;
        int n = answerKey.length();
        Map<Character, Integer> map = new HashMap<>();

        while (right < n) {
            map.put(answerKey.charAt(right), map.getOrDefault(answerKey.charAt(right), 0) + 1);

            while (Math.min(map.getOrDefault('T', 0), map.getOrDefault('F', 0)) > k) {
                map.put(answerKey.charAt(left), map.getOrDefault(answerKey.charAt(left), 0) - 1);

                left++;
            }

            result = Math.max(result, right - left + 1);
            right++;
        }
        return result;
    }

    // frequency of most frequent element - leetcode 1838 - brute force
    public static int maxFrequency(int[] nums, int k) {
        int maxFrequency = 1;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int operations = 0;
            int windowSize = 1;
            for (int j = i - 1; j >= 0; j--) {
                operations += nums[i] - nums[j];
                windowSize++;
                if (operations <= k) {
                    maxFrequency = Math.max(maxFrequency, windowSize);
                } else {
                    break;
                }
            }
        }

        return maxFrequency;
    }

    // frequency of most frequent element - leetcode 1838 - sliding window
    public static int maxFrequencyBinarySearchApproach(int[] nums, int k) {
        Arrays.sort(nums);
        long[] prefixSum = new long[nums.length]; // failing for
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        int maxFrequency = 1;

        for (int targetIndex = 0; targetIndex < nums.length; targetIndex++) {

            int frequency = binarySearch(nums, targetIndex, prefixSum, k);

            maxFrequency = Math.max(maxFrequency, frequency);
        }

        return maxFrequency;
    }

    public static int binarySearch(int[] nums, int targetIndex, long[] prefixSum, int k) {
        int left = 0;
        int right = targetIndex;

        int frequency = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int totalWindowElements = targetIndex - mid + 1;
            long newWindowSum = (long) totalWindowElements * nums[targetIndex];

            long currentSum = prefixSum[targetIndex] - prefixSum[mid] + nums[mid];

            if (newWindowSum - currentSum <= k) {
                frequency = totalWindowElements;
                right = mid - 1;
            }

            if (newWindowSum - currentSum > k) {
                left = mid + 1;
            }

        }

        return frequency;
    }

    public static int maxFrequencySlidingWindow(int[] nums, int k) {
        Arrays.sort(nums);

        int left = 0;
        int result = 0;
        int currentSum = 0;

        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            int windowSize = right - left + 1;
            long newWindowSum = (long) windowSize * nums[right];
            long oldWindowSum = currentSum;
            long operations = (long) (newWindowSum - oldWindowSum);

            while (operations > k && left < right) {
                currentSum -= nums[left];
                left++;
                operations = (long) ((right - left + 1) * nums[right] - currentSum);
            }

            if (newWindowSum - oldWindowSum <= k) {
                result = Math.max(result, windowSize);
            }
        }

        return result;
    }

    public static int countCompleteSubstrings(String word, int k) {
        int n = word.length();

        int result = 0;
        int lastIndex = 0;

        for (int i = 1; i <= n; i++) {
            if (i == n || Math.abs(word.charAt(i) - word.charAt(i - 1)) > 2) {
                result = result + solve(lastIndex, i - 1, word, k);
                lastIndex = i;
            }

        }
        return result;
    }

    public static int solve(int start, int end, String word, int k) {
        int res = 0;

        // int uniqueChar --> 1, 2...

        for (int uniqueChar = 1; uniqueChar <= 26 && uniqueChar * k <= end - start + 1; uniqueChar++) {

            int windowSize = uniqueChar * k;
            int i = start;

            int goodChar = 0;
            int[] count = new int[26];

            for (int j = start; j <= end; j++) {
                count[word.charAt(j) - 'a']++;

                if (count[word.charAt(j) - 'a'] == k) {
                    goodChar++;
                } else if (count[word.charAt(j) - 'a'] == k + 1) {
                    goodChar--;
                }

                if (j - i + 1 > windowSize) { // shift left
                    if (count[word.charAt(i) - 'a'] == k) {
                        goodChar--;
                    } else if (count[word.charAt(i) - 'a'] == k + 1) {
                        goodChar++;
                    }

                    count[word.charAt(i) - 'a']--;
                    i++;
                }

                if (goodChar == uniqueChar) {
                    res++;
                }
            }

        }
        return res;
    }
}
