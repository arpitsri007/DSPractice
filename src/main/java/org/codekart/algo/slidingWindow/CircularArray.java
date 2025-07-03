package org.codekart.algo.slidingWindow;

public class CircularArray {
    // leetcode 2134 - using extra space
    /* 
     * Intuition:
     * 1. We can use a sliding window to find the maximum number of 1's in a window of size  totalones;
     * 2. Maximum window size is totalones;
     * 3. We can then subtract the maximum number of 1's from the total number of 1's to get the minimum number of swaps;
     */
    public static int minSwapsII(int[] nums) {
        int n = nums.length;
        int[] temp = new int[2 * n];

        for (int i = 0; i < 2 * n; i++) {
            temp[i] = nums[i % n];
        }

        // count total 1's
        int totalOnes = 0; // window size
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                totalOnes++;
            }
        }

        int i = 0;
        int j = 0;
        int maxOnes = 0;
        int currentOnes = 0;
        while (j < 2 * n) {
            if (temp[j] == 1) {
                currentOnes++;
            }

            if (j - i + 1 == totalOnes) {
                maxOnes = Math.max(maxOnes, currentOnes);
                if (temp[i] == 1) {
                    currentOnes--;
                }
                i++;
            }
            j++;
        }

        return totalOnes - maxOnes;
    }

    // optimise using constant space
    public static int minSwapsOptimised(int[] nums) {

        int n = nums.length;

        // count total 1's
        int totalOnes = 0; // window size
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                totalOnes++;
            }
        }

        int i = 0;
        int j = 0;
        int maxOnes = 0;
        int currentOnes = 0;
        while (j < 2 * n) {
            if (nums[j % n] == 1) {
                currentOnes++;
            }

            if (j - i + 1 == totalOnes) {
                maxOnes = Math.max(maxOnes, currentOnes);
                if (nums[i % n] == 1) {
                    currentOnes--;
                }
                i++;
            }
            j++;
        }

        return totalOnes - maxOnes;
    }

    public static void main(String[] args) {
        int[] nums = { 0, 1, 0, 1, 1, 0, 0 };
        System.out.println(minSwaps(nums));
    }

    // leetcode 1151
    // Given a binary array data, return the minimum number of swaps required to
    // group all 1’s present in the array together in any place in the array.

    public static int minSwaps(int[] data) {
        int n = data.length;
        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (data[i] == 1) {
                totalOnes++;
            }
        }

        int i = 0;
        int j = 0;
        int maxOnes = 0;
        int currentOnes = 0;
        while (j < n) {
            if (data[j] == 1) {
                currentOnes++;
            }

            if (j - i + 1 == totalOnes) {
                maxOnes = Math.max(maxOnes, currentOnes);
                if (data[i] == 1) {
                    currentOnes--;
                }
                i++;
            }
            j++;
        }

        return totalOnes - maxOnes;
    }

}
