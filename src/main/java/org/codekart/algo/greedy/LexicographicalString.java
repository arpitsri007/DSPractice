package org.codekart.algo.greedy;

import java.util.Arrays;

public class LexicographicalString {

    // main method
    public static void main(String[] args) {
        LexicographicalString lexicographicalString = new LexicographicalString();
        System.out.println(lexicographicalString.smallestString("vzhofnpo"));
    }

    // leetcode 2434 - Lexicographically Smallest String After Substring Operation
    // brute force - O(n^2)
    public String smallestString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        char[] minSuffix = new char[chars.length];
        char minChar = s.charAt(n - 1);

        minSuffix[n - 1] = chars[n - 1];
        StringBuilder temp = new StringBuilder();
        StringBuilder page = new StringBuilder();

        for (int i = n - 2; i >= 0; i--) {
            minSuffix[i] = chars[i] < minSuffix[i + 1] ? chars[i] : minSuffix[i + 1];
        }

        for (int i = 0; i < n; i++) {
            temp.append(chars[i]);
            minChar = i + 1 < n ? minSuffix[i + 1] : chars[i];

            while (temp.length() > 0 && temp.charAt(temp.length() - 1) <= minChar) {
                page.append(temp.charAt(temp.length() - 1));
                temp.deleteCharAt(temp.length() - 1);
            }
        }
        while (temp.length() > 0) {
            page.append(temp.charAt(temp.length() - 1));
            temp.deleteCharAt(temp.length() - 1);
        }

        return page.toString();
    }

}
