package org.codekart.strings;

public class Validword {
    // leetcode 3136 - Valid word
    /*
     * A word is considered valid if:
     * 
     * It contains a minimum of 3 characters.
     * It contains only digits (0-9), and English letters (uppercase and lowercase).
     * It includes at least one vowel.
     * It includes at least one consonant.
     * You are given a string word.
     * 
     * Return true if word is valid, otherwise, return false.
     */
    public boolean isValidWord(String word) {
        int n = word.length();
        char[] ch = word.toCharArray();

        boolean vowelPresent = false;
        boolean consonentPresent = false;

        for (int i = 0; i < ch.length; i++) {
            if (!(Character.isDigit(ch[i]) || Character.isLetter(ch[i])))
                return false;

            if (Character.isLetter(ch[i])) {
                if (isVowel(ch[i]) && !vowelPresent) {
                    vowelPresent = true;
                } else if (!consonentPresent && !isVowel(ch[i])) {
                    consonentPresent = true;
                }

            }
        }

        if (n >= 3 && vowelPresent && consonentPresent)
            return true;

        return false;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    // main method
    public static void main(String[] args) {
        Validword vw = new Validword();
        System.out.println(vw.isValidWord("22aN"));
    }
}
