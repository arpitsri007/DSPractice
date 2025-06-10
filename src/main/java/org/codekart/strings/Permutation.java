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
    private static void findAllPermutationsHelper(String str, StringBuilder sb, Set<Character> used, List<String> result) {
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
    
    
}
