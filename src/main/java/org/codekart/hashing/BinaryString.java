package org.codekart.hashing;

import java.util.HashSet;
import java.util.Set;

public class BinaryString {
    // leetcode 1461. Check If a String Contains All Binary Codes of Size K
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        Set<String> set = new HashSet();

        int i = 0;
        int j = 0;

        while (j <= n) {
            if (j - i == k) {
                set.add(s.substring(i, j));
                i++;
                continue;
            }
            j++;
        }

        return set.size() == Math.pow(2, k);
    }


    public static void main(String[] args) {
        BinaryString bs = new BinaryString();
        System.out.println(bs.hasAllCodes("00110", 2)); // true
        // System.out.println(bs.hasAllCodes("0110", 1)); // false
        // System.out.println(bs.hasAllCodes("0000000001011100", 4)); // false
    }

}
