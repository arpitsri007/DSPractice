package org.codekart.algo.slidingWindow;

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

}
