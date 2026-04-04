package org.codekart.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinaryString {
    // leetcode 1980 - Find Unique Binary String
    public String findDifferentBinaryString(String[] nums) {
        Set<Integer> st = new HashSet<>();

        for (String s : nums) {
            st.add(Integer.parseInt(s, 2));
        }

        int n = nums.length;
        for (int i = 0; i < (1 << n); i++) {
            if (!st.contains(i)) {
                return Integer.toBinaryString(i | (1 << n)).substring(1);
            }
        }

        return "";
    }

    // Approach 2: Generate all strings of length - n and return missing string

    public String findDifferentBinaryStringApproach2(String[] nums) {
      int n = nums.length;
      Set<String> st = new HashSet<>(Arrays.asList(nums));

      return generate(new StringBuilder(), n, st);
      
    }

    private String generate(StringBuilder sb, int n, Set<String> st) {

        // Base case
        if (sb.length() == n) {
            String s = sb.toString();
            if (!st.contains(s)) {
                return s;
            }
            return "";
        }

        // Recursive calls
        sb.append('0');
        String result = generate(sb, n, st);
        if (!result.isEmpty()) {
            return result;
        }
        sb.deleteCharAt(sb.length() - 1);

        sb.append('1');
        result = generate(sb, n, st);
        if (!result.isEmpty()) {
            return result;
        }
        sb.deleteCharAt(sb.length() - 1);

        return "";
       
    }

    // Approach 3: Check each string and invert ith bit in result
    public String findDifferentBinaryStringApproach3(String[] nums) {
        int n = nums.length;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (nums[i].charAt(i) == '0') {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }

        return sb.toString();
    }


}
