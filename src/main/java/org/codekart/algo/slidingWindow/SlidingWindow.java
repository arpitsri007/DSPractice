package org.codekart.algo.slidingWindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SlidingWindow {

    // driver code for sliding window
    public static void main(String[] args) {
        String str = "forxxorfxdofr";
        String pattern = "for";
        System.out.println(countAnagramsUsingBruteForce(str, pattern));
        System.out.println(countAnagramsUsingSlidingWindow(str, pattern));
    }

    // count occurences of anagrams of a pattern in a string using brute force
    // Time Complexity: O(n * m)
    // Space Complexity: O(1)
    public static int countAnagramsUsingBruteForce(String str, String pattern) {
        int count = 0;
        int patternLength = pattern.length();

        // If the pattern is longer than the string, no anagrams are possible
        if (patternLength > str.length()) {
            return 0;
        }

        // Create a frequency map of the pattern
        int[] patternCount = new int[26];
        for (int i = 0; i < patternLength; i++) {
            patternCount[pattern.charAt(i) - 'a']++;
        }

        // check each possible substring of the length patternLength
        for (int i = 0; i <= str.length() - patternLength; i++) {
            String substring = str.substring(i, i + patternLength);
            int[] windowCount = new int[26];
            for (int j = 0; j < patternLength; j++) {
                windowCount[substring.charAt(j) - 'a']++;
            }

            if (Arrays.equals(patternCount, windowCount)) {
                count++;
            }
        }
        return count;
    }

    // count occurences of anagrams of a pattern in a string using sliding window
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static int countAnagramsUsingSlidingWindow(String str, String pattern) {
        int count = 0;
        int patternLength = pattern.length();

        // If the pattern is longer than the string, no anagrams are possible
        if (patternLength > str.length()) {
            return 0;
        }

        // Create a frequency map of the pattern
        int[] patternCount = new int[26];
        for (int i = 0; i < patternLength; i++) {
            patternCount[pattern.charAt(i) - 'a']++;
        }

        // Create a frequency map of the current window
        int[] windowCount = new int[26];

        int i = 0;
        int j = 0;

        while (j < str.length()) {
            // add the current character to the window
            windowCount[str.charAt(j) - 'a']++;

            // if the window size is equal to the pattern length
            if (j - i + 1 == patternLength) {
                // check if the window is an anagram of the pattern
                if (Arrays.equals(patternCount, windowCount)) {
                    count++;
                }

                // remove the first character from the window
                windowCount[str.charAt(i) - 'a']--;
                i++;
            }

            // move the window to the right
            j++;
        }

        return count;
    }

    // Minimum Size Subarray Sum
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
    } // Time Complexity: O(n)

    // First negative integer in every window of size k
    public static int[] firstNegativeInteger(int[] nums, int k) {
        int i = 0;
        int j = 0;
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Queue<Integer> queue = new LinkedList<>();

        while (j < n) {
            if (nums[j] < 0) {
                queue.add(nums[j]);
            }
            if (j - i + 1 == k) {
                if (queue.isEmpty()) {
                    result[i] = 0;
                } else {
                    result[i] = queue.peek();
                    if (nums[i] == queue.peek()) {
                        queue.poll();
                    }
                }
                i++;
            }
            j++;
        }
        return result;
    } // Time Complexity: O(n)

    // Minimum Window Substring | Google | Leetcode 76
    public static String minWindow(String s, String t) {
        String ans = "";

        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return ans;
        }

        Map<Character, Integer> tMap = new HashMap<>();
        for (int k = 0; k < t.length(); k++) {
            tMap.put(t.charAt(k), tMap.getOrDefault(t.charAt(k), 0) + 1);
        }

        int left = 0;
        int right = 0;
        int windowLength = Integer.MAX_VALUE;
        int required = t.length();

        while (right < s.length()) {
            char c = s.charAt(right);
            // if the character is in the tMap, we need to check if it is required
            if (tMap.getOrDefault(c, 0) > 0) {
                required--;
            }
            tMap.put(c, tMap.getOrDefault(c, 0) - 1);

            while (required == 0) {
                int currentWindowLength = right - left + 1;
                if (currentWindowLength < windowLength) {
                    windowLength = currentWindowLength;
                    ans = s.substring(left, right + 1);
                }
                char leftChar = s.charAt(left);
                tMap.put(leftChar, tMap.getOrDefault(leftChar, 0) + 1);

                if (tMap.getOrDefault(leftChar, 0) > 0) {
                    required++;
                }

                left++;
            }
            right++;
        }
        return windowLength == Integer.MAX_VALUE ? "" : ans;
    }

}
