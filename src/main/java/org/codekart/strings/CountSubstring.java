package org.codekart.strings;

public class CountSubstring {
    // leetcode 3234. Count the Number of Substrings With Dominant Ones
    // Brute force:
    // 1. Generate all substrings
    // 2. Check if the substring is valid
    // 3. Count the number of valid substrings
    // Time complexity: O(n^3)
    // Space complexity: O(1)
    public int countSubstrings(String s) {
        int count = 0;
        int n = s.length();
        // pre-calculate the ones and zeros in the string
        int[] countOnes = new int[n];

        for(int i = 0; i < n; i++) {
            countOnes[i] = s.charAt(i) == '1' ? (i == 0 ? 1 : countOnes[i - 1] + 1) : (i == 0 ? 0 : countOnes[i - 1]);
        }
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int ones = countOnes[j] - (i == 0 ? 0 : countOnes[i - 1]);
                int zeros = j - i + 1 - ones;
                if(ones >= zeros * zeros) {
                    count++;
                }
            }
        }
        return count;
    }


}
