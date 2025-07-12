package org.codekart.algo.twopointer;

public class ReverseVowel {
    // leetcode 345 - Reverse Vowels of a String
    public String reverseVowels(String s) {
        // convert the string to a character array
        char[] chars = s.toCharArray();

        // use two pointers to swap the vowels
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            // if the left and right characters are vowels, swap them
            if (isVowel(chars[left]) && isVowel(chars[right])) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            } else if (isVowel(chars[left])) {
                right--;
            } else {
                left++;
            }
        }

        return new String(chars);
    }

    private boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }

    // leetcode 151 - Reverse Words in a String
    public String reverseWords(String s) {
        // First trim leading and trailing spaces
        s = s.trim();

        // split the string into words
        String[] words = s.split("\\s+");

        // reverse the words
        for (int i = 0; i < words.length / 2; i++) {
            String temp = words[i];
            words[i] = words[words.length - i - 1];
            words[words.length - i - 1] = temp;
        }

        return String.join(" ", words);
    }

    // leetcode 151 - Reverse Words in a String - using two pointers
    public String reverseWordsTwoPointers(String s) {
        // trim the string
        s = s.trim();

        // convert the string to a character array
        char[] chars = s.toCharArray();

        // use two pointers to reverse the words
        int left = 0;
        int right = chars.length - 1;

        reverse(chars, 0, chars.length - 1);

        // now reverse the words
        int i = 0;
        left = 0;
        right = 0;

        while (i < chars.length) {
            // if the right character is not a space,
            while (i < chars.length && chars[i] == ' ') {
                i++;
            }

            if (i < chars.length) {

                if (left != 0) {
                    left = right + 1;
                    chars[right++] = ' ';
                }

                // copy the word to the right pointer
                while (i < chars.length && chars[i] != ' ') {
                    chars[right++] = chars[i++];
                }

                // reverse the word
                reverse(chars, left, right - 1);
                left = right;
            }
        }

        // string from 0 to right - 1
        return new String(chars, 0, right);
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    // main method
    public static void main(String[] args) {
        ReverseVowel reverseVowel = new ReverseVowel();
        System.out.println(reverseVowel.reverseWordsTwoPointers("a good   example"));
    }
}
