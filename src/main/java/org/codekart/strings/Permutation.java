package org.codekart.strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutation {

    public static void main(String[] args) {
        String str = "abc";
        System.out.println(findAllPermutations2(str));
    }

    // find all permutations of a string in lexicographical order sorted
    // brute force
    public static List<String> findAllPermutations(String str) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Set<Character> used = new HashSet<>();
        findAllPermutationsHelper(str, sb, used, result);
        return result;
    }

    // TC: O(n!)
    // SC: O(n) - for the set
    private static void findAllPermutationsHelper(String str, StringBuilder sb, Set<Character> used,
            List<String> result) {
        if (sb.length() == str.length()) {
            result.add(sb.toString());
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            // if the character is already used, skip it
            if (used.contains(str.charAt(i))) {
                continue;
            }

            // add the character to the set
            used.add(str.charAt(i));
            sb.append(str.charAt(i));

            // recursive call
            findAllPermutationsHelper(str, sb, used, result);

            // backtrack
            used.remove(str.charAt(i));
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    // Approach 2: using swap
    public static List<String> findAllPermutations2(String str) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder(str);
        findAllPermutationsHelper2(sb, 0, result);
        return result;
    }

    private static void swap(StringBuilder sb, int i, int j) {
        char temp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, temp);
    }

    private static void findAllPermutationsHelper2(StringBuilder sb, int idx, List<String> result) {
        if (idx == sb.length()) {
            result.add(sb.toString());
            return;
        }

        for (int i = idx; i < sb.length(); i++) {
            swap(sb, idx, i);
            findAllPermutationsHelper2(sb, idx + 1, result);
            swap(sb, idx, i);
        }

    }

    // Print all palindrome permutations of a string
    public static List<String> printPalindromePermutations(String str) {
        List<String> result = new ArrayList<>();
        printPalindromePermutationsHelper(str, result);
        return result;
    }

    private static void printPalindromePermutationsHelper(String str, List<String> result) {
        // find all permutations of the string
        List<String> permutations = findAllPermutations2(str);
        for (String permutation : permutations) {
            if (isPalindrome(permutation)) {
                result.add(permutation);
            }
        }
    }

    private static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Print all palindrome permutations of a string using frequency array
    public static List<String> printPalindromePermutations2(String str) {
        List<String> result = new ArrayList<>();
        char[] freq = new char[26];
        for (char c : str.toCharArray()) {
            freq[c - 'a']++;
        }

        int oddCount = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
            }
        }

        if (oddCount > 1) {
            return result;
        }

        int n = str.length();

        StringBuilder sb = new StringBuilder();

        char oddChar = ' ';
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddChar = (char) (i + 'a');

            }

            for (int j = 0; j < freq[i] / 2; j++) {
                sb.append((char) (i + 'a'));
            }
        }

        // generate all permutations of the first half of the string
        List<String> permutations = findAllPermutations2(sb.toString());

        for (String permutation : permutations) {
            StringBuilder temp = new StringBuilder(permutation);
            String firstHalf = temp.toString();
            String secondHalf = temp.reverse().toString();

            if (oddChar != ' ') {
                result.add(firstHalf + oddChar + secondHalf);
            } else {
                result.add(firstHalf + secondHalf);
            }
        }

        return result;
    }

    // leetcode 567 - Permutation in String 
    // brute force
    public static boolean isPermutation(String s1, String s2) {
        List<String> permutations = findAllPermutations2(s1);
        for (String permutation : permutations) {
            if (s2.contains(permutation)) {
                return true;
            }
        }
        return false;
    }

    // leetcode 567 - Permutation in String 
    // sliding window
    // public static boolean isPermutation2(String s1, String s2) {
    //     int[] freq = new int[26];
    //     for (char c : s1.toCharArray()) {
}
