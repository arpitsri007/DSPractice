package org.codekart.algo.slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubString {
    public static void main(String[] args) {
        String s = "abcooodcf";

        int result = countVowelsSW(s, 3);
        System.out.println(result);
    }

    // maximum number of vowels in substring of given length brute for
    public static int maxVowels(String s, int k) {
        int maxVowels = 0;

        for (int i = 0; i < s.length() - k + 1; i++) {
            int j = i + k - 1;
            String substring = s.substring(i, j + 1);
            int vowels = countVowels(substring);
            maxVowels = Math.max(maxVowels, vowels);
        }

        return maxVowels;
    }

    public static int countVowels(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o'
                    || s.charAt(i) == 'u') {
                count++;
            }
        }
        return count;
    }

    // maxVowel using sliding window
    public static int countVowelsSW(String s, int k) {
        int i = 0;
        int j = 0;

        int maxVowel = 0;
        int currMax = 0;

        while (j < s.length()) {

            if (isVowel(s.charAt(j))) {
                currMax++;
            }

            if (j - i + 1 == k) {
                maxVowel = Math.max(maxVowel, currMax);
                if (i + 1 < s.length()) {
                    char c = s.charAt(i);
                    if (isVowel(c)) {
                        currMax--;
                    }
                    i++;
                }
            }

            j++;

        }

        return maxVowel;
    }

    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    // leetcode 1208 - get the equal substring with maximum length with at most k
    // cost brute force
    public static int equalSubstring(String s, String t, int maxCost) {
        // generate all the substrings
        int n = s.length();
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String s1Sub = s.substring(i, j + 1);
                String t1Sub = t.substring(i, j + 1);

                int currentCost = getConversionCost(s1Sub, t1Sub);

                if (currentCost <= maxCost) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }

            }
        }
        return maxLength;
    }

    public static int getConversionCost(String s1Sub, String t1Sub) {
        int cost = 0;
        for (int i = 0; i < s1Sub.length(); i++) {
            cost += Math.abs(s1Sub.charAt(i) - t1Sub.charAt(i));
        }
        return cost;
    }

    // leetcode 438 - brute force
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int n = s.length();
        int m = p.length();

        for (int i = 0; i < n - m + 1; i++) {
            String s1Sub = s.substring(i, i + m);
            if (isAnagram(s1Sub, p)) {
                result.add(i);
            }
        }
        return result;
    }

    public static boolean isAnagram(String s1Sub, String p) {
        int[] freq = new int[26];

        for (int i = 0; i < s1Sub.length(); i++) {
            freq[s1Sub.charAt(i) - 'a']++;
        }

        for (int i = 0; i < p.length(); i++) {
            freq[p.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> findAnagramsSW(String s, String p) {
        int n = s.length();
        int m = p.length();

        int i = 0;
        int j = 0;
        List<Integer> res = new ArrayList();
        Map<Character, Integer> mp = new HashMap();

        for (int k = 0; k < m; k++) {
            mp.put(p.charAt(k), mp.getOrDefault(p.charAt(k), 0) + 1);
        }

        while (j < n) {
            mp.put(s.charAt(j), mp.getOrDefault(s.charAt(j), 0) - 1);
            if (j - i + 1 == m) {
                if (allZero(mp)) {
                    res.add(i);
                }
                mp.put(s.charAt(i), mp.getOrDefault(s.charAt(i), 0) + 1);
                i++;
            }

            j++;
        }

        return res;
    }

    public static boolean allZero(Map<Character, Integer> mp) {
        for (Map.Entry<Character, Integer> entry : mp.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    // leetcode 340 - Given a string s and an integer k, return the length of the
    // longest substring of s that contains at most k distinct characters.
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        int i = 0;
        int j = 0;
        Map<Character, Integer> mp = new HashMap<>();
        int maxLen = 0;

        while (j < n) {
            char c = s.charAt(j);
            mp.put(c, mp.getOrDefault(c, 0) + 1);

            while (i < j && mp.size() > k) {
                int iCharFreq = mp.getOrDefault(s.charAt(i), 0);
                if (iCharFreq == 0) {
                    mp.remove(s.charAt(i));
                } else {
                    mp.put(s.charAt(i), iCharFreq - 1);
                }

                i++;
            }

            maxLen = Math.max(maxLen, j - i + 1);
            j++;
        }
        return maxLen;
    }

    // leetcode 2516 - take k of each character from the string either from left or
    // right
    // return the minimum no of minutes to take k of each character, return -1 if
    // not possible
    // brute force - using recursion
    public static int takeKOfEachCharacter(String s, int k) {
        int n = s.length();
        int[] freq = new int[3];

        // check if it is possible to take k of each character
        int[] totalCount = new int[3];

        for (int i = 0; i < n; i++) {
            totalCount[s.charAt(i) - 'a']++;
        }

        if (totalCount[0] < k || totalCount[1] < k || totalCount[2] < k) {
            return -1;
        }

        int[] result = new int[1];
        result[0] = Integer.MAX_VALUE;

        takeKOfEachCharacterHelper(s, k, 0, n - 1, freq, 0, result);

        return result[0] == Integer.MAX_VALUE ? -1 : result[0];
    }

    private static void takeKOfEachCharacterHelper(String s, int k, int left, int right, int[] freq, int minutes,
            int[] result) {

        if (freq[0] >= k && freq[1] >= k && freq[2] >= k) {
            result[0] = Math.min(result[0], minutes);
            return;
        }

        if (minutes >= result[0]) {
            return;
        }

        if (left > right) {
            return;
        }

        if(left <= right){
            freq[s.charAt(left) - 'a']++;
            takeKOfEachCharacterHelper(s, k, left + 1, right, freq, minutes + 1, result);
            freq[s.charAt(left) - 'a']--;
        }
        if(left <= right){
            freq[s.charAt(right) - 'a']++;
            takeKOfEachCharacterHelper(s, k, left, right - 1, freq, minutes + 1, result);
            freq[s.charAt(right) - 'a']--;
        }

    }

    // using sliding window
    public static int takeKOfEachCharacterSW(String s, int k) {
        int n = s.length();

        int[] freq = new int[3];

        for(char c : s.toCharArray()){
            freq[c - 'a']++;
        }

        if(freq[0] < k || freq[1] < k || freq[2] < k){
            return -1;
        }

        // Now we would search for window which not has to be deleted
        // we would use two pointers to find the window
        int i = 0;
        int j = 0;
        int notDeletedWindowLength = 0;

        while (j < n) {

            char c = s.charAt(j);
            freq[c - 'a']--;

            while(i <= j && (freq[0] < k || freq[1] < k || freq[2] < k)){
                char c1 = s.charAt(i);
                freq[c1 - 'a']++;
                i++;
            }

            notDeletedWindowLength = Math.max(notDeletedWindowLength, j - i + 1);
            j++;

        }

        return n - notDeletedWindowLength;
    }

}
