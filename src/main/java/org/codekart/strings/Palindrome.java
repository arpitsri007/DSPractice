package org.codekart.strings;

import java.util.HashMap;
import java.util.Map;

public class Palindrome {
    //leetcode 409 - longest palindrome
    /*
     * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
     * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
     */

    // Approach 1: using hashmap
    // TC: O(n)
    // SC: O(1)
    public int longestPalindrome(String s) {
       Map<Character, Integer> map = new HashMap<>();
       for (char c : s.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0) + 1);
       }
       int result = 0;
       for (int count : map.values()) {
        result += count / 2 * 2;    
       }
       return result < s.length() ? result + 1 : result;
    }

    // Approach 2: using array
    // TC: O(n)
    // SC: O(1)
    public int longestPalindromeUsingArray(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c - 'A']++;
        }
        int result = 0;
        for (int c : count) {
            result += c / 2 * 2;
        }
        return result < s.length() ? result + 1 : result;
    }

    // leetcode 266 - palindrome permutation
    /*
     * Given a string s, return true if a permutation of the string could form a palindrome.
     */

    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int oddCount = 0;
        for (int count : map.values()) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }
        return oddCount <= 1;
    }
}
