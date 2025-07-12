package org.codekart.algo.twopointer;

import java.util.Arrays;

public class StringOperation {
    // leetcode 1750 - Minimum Length of String After Deleting Similar Ends

    public int minimumLength(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right && s.charAt(left) == s.charAt(right)) {
            char c = s.charAt(left);
            while (left < right && s.charAt(left) == c) {
                left++;
            }
            while (left <= right && s.charAt(right) == c) {
                right--;
            }
        }
        return right - left + 1;
    }

    // leetcode 838 - Push Dominoes - brute force
    // check for each ith domino, left force and right force

    public static String pushDominoes(String dominoes) {
        int n = dominoes.length();

        int[] leftClosestR = new int[n];
        Arrays.fill(leftClosestR, -1);
        int[] rightClosestL = new int[n];
        Arrays.fill(rightClosestL, -1);

        char[] result = new char[n];

        for (int i = 0; i < n; i++) {
            // find the closest right domino and left domino
            int left = i;
            int right = i;
            while (left >= 0 && dominoes.charAt(left) == '.') {
                left--;
            }
            while (right < n && dominoes.charAt(right) == '.') {
                right++;
            }
            if (left >= 0 && dominoes.charAt(left) == 'R') {
                leftClosestR[i] = left;
            }
            if (right < n && dominoes.charAt(right) == 'L') {
                rightClosestL[i] = right;
            }

            if (leftClosestR[i] == -1 && rightClosestL[i] == -1) {
                result[i] = '.';
            } else if (leftClosestR[i] == -1) {
                result[i] = 'L';
            } else if (rightClosestL[i] == -1) {
                result[i] = 'R';
            } else if (leftClosestR[i] != -1 && rightClosestL[i] != -1) {
                int leftForce = i - leftClosestR[i];
                int rightForce = rightClosestL[i] - i;
                if (leftForce < rightForce) {
                    result[i] = 'R';
                } else if (leftForce > rightForce) {
                    result[i] = 'L';
                } else {
                    result[i] = '.';
                }
            }
        }
        return new String(result);
    }

    // Push Dominoes - optimized

    public static String pushDominoesOptimizedTwoPointer(String dominoes) {
        int n = dominoes.length();
        char[] result = new char[n];
        int[] leftClosestR = new int[n];
        Arrays.fill(leftClosestR, -1);
        int[] rightClosestL = new int[n];
        Arrays.fill(rightClosestL, -1);

        // fill leftClosestR
        if(dominoes.charAt(0) == 'R') {
            leftClosestR[0] = 0;
        }

        for (int i = 1; i < n; i++) {
            if (dominoes.charAt(i) == 'R') {
                leftClosestR[i] = i;
            } else if (dominoes.charAt(i) == '.') {
                leftClosestR[i] = leftClosestR[i - 1];
            }
        }

        if(dominoes.charAt(n - 1) == 'L') {
            rightClosestL[n - 1] = n - 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            if (dominoes.charAt(i) == 'L') {
                rightClosestL[i] = i;
            } else if (dominoes.charAt(i) == '.') {
                rightClosestL[i] = rightClosestL[i + 1];
            }
        }

        for (int i = 0; i < n; i++) {
            if (leftClosestR[i] == -1 && rightClosestL[i] == -1) {
                result[i] = '.';
            } else if (leftClosestR[i] == -1) {
                result[i] = 'L';
            } else if (rightClosestL[i] == -1) {
                result[i] = 'R';
            } else if (leftClosestR[i] != -1 && rightClosestL[i] != -1) {
                int leftForce = i - leftClosestR[i];
                int rightForce = rightClosestL[i] - i;
                if (leftForce < rightForce) {
                    result[i] = 'R';
                } else if (leftForce > rightForce) {
                    result[i] = 'L';
                } else {
                    result[i] = '.';
                }
            }
        }
        return new String(result);
    }

    // main method
    public static void main(String[] args) {
        System.out.println(pushDominoesOptimizedTwoPointer("RR.L"));
    }
}
