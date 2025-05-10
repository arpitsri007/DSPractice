package org.codekart.algo.dynamicProgramming.dpStrings;

import java.util.ArrayList;
import java.util.List;

public class PalindromicSubString {
    public static void main(String[] args) {
        String s = "cbbd";
        //findAllSubstrings(s);
        System.out.println(longestPalindrome(s));
    }

    // code to find all substrings of a string
    public static List<String> findAllSubstrings(String s) {
        List<String> result = new ArrayList<>();


        for(int i = 0; i < s.length(); i++) {
            for(int j = i + 1; j <= s.length(); j++) {
                if(isPalindrome(s.substring(i, j))) {
                    result.add(s.substring(i, j));
                }
            }
        }
        return result;
    }

    // code to check if a string is a palindrome using memoization
    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static List<String> findAllSubstringsRecuStrings(String s) {
        List<String> result = new ArrayList<>();
        int[][] dp = new int[s.length()][s.length()];   
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < s.length(); j++) {
                dp[i][j] = -1;
            }
        }
        for(int i = 0; i < s.length(); i++) {
            for(int j = i + 1; j <= s.length(); j++) {
                if(isPalindrome(s.substring(i, j), i, j, dp)) {
                    result.add(s.substring(i, j));
                }
            }
        }
        return result;
    }

    // using recursion and memoization
    public static boolean isPalindrome(String s, int i, int j, int[][] dp) {
        if(i >= j) {
            return true;
        }
        // dp[i][j] = 0 means not palindrome
        // dp[i][j] = 1 means palindrome
        if(dp[i][j] != -1) {
            return dp[i][j] == 1;
        }
        if(s.charAt(i) != s.charAt(j)) {
            dp[i][j] = 0;
            return false;
        }
        dp[i][j] = isPalindrome(s, i+1, j-1, dp) ? 1 : 0;
        return dp[i][j] == 1;
    }

    // Let's do it using tabulation
    /**
    * 
     * Approach:
     * 1. Create a dp array of size n*n
     * 2. Initialize the dp array with false
     * 3. Iterate over the string and check if the substring is a palindrome
     * 
     *  t[i][j] = true s[i:j] is a palindrome else false
     * check for 1 length string --> t[i][i] = true
     * check for 2 length string --> t[i][i+1] = true if s[i] == s[i+1] else false
     * 
     * for length > 2
     *  t[i][j] = t[i+1][j-1] && s[i] == s[j]
     * 
     * 4. If it is, then mark the dp array as true
     * 5. Return the dp array
     */

    public static List<String> findAllSubstringsTabulation(String s) {
        List<String> result = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for(int i = 0; i < s.length() - 1; i++) {
            if(s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;
            }
        }
        for(int len = 3; len <= s.length(); len++) {
            for(int i = 0; i+len-1 < s.length(); i++) {
                int j = i + len - 1;
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    result.add(s.substring(i, j+1));
                }
            }
        }
        // count the number of palindromic substrings
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < s.length(); j++) {
                if(dp[i][j]) {
                    count++;
                }
            }
        }
        System.out.println("Number of palindromic substrings: " + count);
        return result;
    }

    public static String longestPalindrome(String s) {
        String result = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }

        int maxLen = 1;
        int startIdx = 0;

        for(int i = 0; i < s.length() - 1; i++) {
            if(s.charAt(i) == s.charAt(i+1)) {
                maxLen = 2;
                startIdx = i;
                dp[i][i+1] = true;
            }
        }

    
        for(int len = 2; len <= s.length(); len++) {
            for(int i = 0; i+len-1 < s.length(); i++) {
                int j = i + len - 1;
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    if(j-i+1 > maxLen) {
                        maxLen = j-i+1;
                        startIdx = i;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }

       result = s.substring(startIdx, maxLen+startIdx);
       return result;
    }

    // palindrome partitioning

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for(int i = 0; i < s.length() - 1; i++) {
            if(s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;
            }
        }

        for(int len = 3; len <= s.length(); len++) {
            for(int i = 0; i+len-1 < s.length(); i++) {
                int j = i + len - 1;
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                }
            }
        }
        partitionHelper(s, 0, result, new ArrayList<>(), dp);
        return result;
    }

    public static void partitionHelper(String s, int start, List<List<String>> result, List<String> current, boolean[][] dp) {
        if(start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for(int end = start; end < s.length(); end++) {
            if(dp[start][end]) {
                current.add(s.substring(start, end+1));
                partitionHelper(s, end+1, result, current, dp);
                current.remove(current.size()-1);
            }
        }
        return;
    }

    // palindrome partitioning II
    // Problem: Given a string s, partition s such that every substring of the partition is a palindrome.






}