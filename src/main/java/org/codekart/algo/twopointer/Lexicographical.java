package org.codekart.algo.twopointer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lexicographical {
    // leetcode 3403 - Lexicographically Smallest String After Applying Operations
    // brute force - try all possible operations

    public String smallestString(String word, int numFriends) {
        // Edge case: If numFriends equals the word length, every character is a
        // separate piece
        if (numFriends == word.length()) {
            char maxChar = 0;
            for (char c : word.toCharArray()) {
                maxChar = (char) Math.max(maxChar, c);
            }
            return String.valueOf(maxChar);
        }

        // Set to store all possible substrings that could go in the box
        Set<String> allPossiblePieces = new HashSet<>();
        // Generate all possible ways to split the word
        generateAllSplits(word, numFriends, 0, new ArrayList<>(), allPossiblePieces);

        // Find the lexicographically largest string
        String largest = "";
        for (String piece : allPossiblePieces) {
            if (piece.compareTo(largest) > 0) {
                largest = piece;
            }
        }

        return largest;

    }

    private void generateAllSplits(String word, int numPieces, int startIndex,
            List<String> currentSplit, Set<String> allPieces) {
        // Base case 1: We've reached the end of the word but still need more pieces
        if (startIndex == word.length()) {
            return;
        }

        // Base case 2: We need exactly one more piece - take the remaining substring
        if (numPieces == 1) {
            String lastPiece = word.substring(startIndex);
            currentSplit.add(lastPiece);

            // Add all pieces from this complete split to our set
            for (String piece : currentSplit) {
                allPieces.add(piece);
            }

            // Remove the last piece for backtracking
            currentSplit.remove(currentSplit.size() - 1);

            return;
        }

        // Recursive case: Try different split positions
        for (int i = startIndex + 1; i <= word.length() - (numPieces - 1); i++) {
            String piece = word.substring(startIndex, i);
            currentSplit.add(piece);

            // Recursively generate splits for the rest of the word
            generateAllSplits(word, numPieces - 1, i, currentSplit, allPieces);

            // Backtrack
            currentSplit.remove(currentSplit.size() - 1);
        }
    }

    // optimized approach - try all possible substrings that could be in a valid
    // split
    // and check if the lexicographically smallest string is possible

    public String smallestStringOptimized(String word, int numFriends) {
        int n = word.length();

        if (numFriends > n) {
            return "";
        }

        if (numFriends == 1) {
            return word;
        }

        // If numFriends equals the word length, return the largest character
        if (numFriends == word.length()) {
            char maxChar = 0;
            for (char c : word.toCharArray()) {
                maxChar = (char) Math.max(maxChar, c);
            }
            return String.valueOf(maxChar);
        }

        String largest = "";

        for (int start = 0; start < n; start++) {
            for (int end = start + 1; end <= n; end++) {
                String piece = word.substring(start, end);
                if (isValidSplit(word, numFriends, piece.length())) {
                    if (piece.compareTo(largest) > 0) {
                        largest = piece;
                    }
                }
            }
        }

        return largest;
    }

    private boolean isValidSplit(String word, int numFriends, int pieceLength) {
        // valid if remaining characters can be distributed among remaining
        // friends(numFriends - 1)
        int remainingFriends = numFriends - 1;
        int remainingCharacters = word.length() - pieceLength;
        return remainingCharacters >= remainingFriends;
    }

    // More optimized approach -
    // find the max character and maxPossibleLength of the piece that can be put in
    // the box

    public String smallestStringMoreOptimized(String word, int numFriends) {
        int n = word.length();
        if (numFriends > n) {
            return "";
        }

        if (numFriends == 1) {
            return word;
        }

        char maxChar = 0;
        for (char c : word.toCharArray()) {
            maxChar = (char) Math.max(maxChar, c);
        }

        int maxPossibleLength = n - (numFriends - 1);

        String largest = "";

        for (int i = 0; i < n; i++) {
            int availableLength = Math.min(maxPossibleLength, n - i);
            String currentPiece = word.substring(i, i + availableLength);

            if (currentPiece.compareTo(largest) > 0) {
                largest = currentPiece;
            }

        }

        return largest;
    }

    // More optimized approach - Two pointer

    public String smallestStringTwoPointer(String word, int numFriends) {
        int n = word.length();
        if (numFriends > n) {
            return "";
        }

        if (numFriends == 1) {
            return word;
        }

        int i = bestStartingIndex(word);
        int maxPossibleLength = n - (numFriends - 1);
        int availableLength = Math.min(maxPossibleLength, n - i);

        return word.substring(i, i + availableLength);

    }

    private int bestStartingIndex(String word) {
        int n = word.length();
        int i = 0;
        int j = 1;

        while (j < n) {
            int k = 0;

            // skipping equal characters
            while (j + k < n && word.charAt(i + k) == word.charAt(j + k)) {
                k++;
            }

            if (j + k == n && word.charAt(i + k) < word.charAt(j + k)) {
                i = j;
                j = j + 1;
            } else {
                j = j + k + 1;
            }

        }
        return i;
    }

    // main method
    public static void main(String[] args) {
        Lexicographical l = new Lexicographical();
        // System.out.println(l.smallestString("abc", 2));
        System.out.println(l.smallestStringMoreOptimized("dbca", 2));
    }

}
