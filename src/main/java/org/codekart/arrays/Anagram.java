package org.codekart.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagram {

    // main me
    public static void main(String[] args) {
        Anagram anagram = new Anagram();
        // String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
        // System.out.println(anagram.groupAnagrams(strs));
        // System.out.println(anagram.groupAnagramsImproved(strs));
        String[] words = { "abba", "baba", "bbaa", "cd", "cd" };
        System.out.println(anagram.removeAnagrams(words));
    }

    // leetcode 49
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    } // TC: O(n * k * log k) SC: O(n)

    // Improved version of above solution
    public List<List<String>> groupAnagramsImproved(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        int[] frequency = new int[26];
        for (String str : strs) {
            Arrays.fill(frequency, 0);
            for (char c : str.toCharArray()) {
                frequency[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                int freq = frequency[i];
                if (freq > 0) {
                    sb.append((char) (i + 'a')).append(freq);
                }
            }
            String key = sb.toString();
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    } // TC: O(n * k) SC: O(n)

    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] strChars = str.toCharArray();
            char[] sortedChars = new char[26];

            for (char c : strChars) {
                sortedChars[c - 'a']++;
            }

            String sorted = new String(sortedChars);
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    // leetcode 2273 - Find Resultant Array After Removing Anagrams
    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> result = new ArrayList<>();

        result.add(words[0]);
        int i = 1;

        while (i < n) {
            if (!isAnagram(words[i], result.get(result.size() - 1))) {
                result.add(words[i]);
            }

            i++;
        }
        return result;
    }

    private boolean isAnagram(String word1, String word2) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        for (char c : word1.toCharArray()) {
            freq1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            freq2[c - 'a']++;
        }
        return Arrays.equals(freq1, freq2);
    }

}
