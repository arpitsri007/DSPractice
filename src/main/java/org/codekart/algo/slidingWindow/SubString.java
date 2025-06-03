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



        for(int k = 0; k < m ; k++) {
            mp.put(p.charAt(k), mp.getOrDefault(p.charAt(k), 0) +1);
        }

        while(j < n) {
            mp.put(s.charAt(j), mp.getOrDefault(s.charAt(j), 0) - 1);
            if(j - i + 1 == m) {
                if(allZero(mp)) {
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
}
