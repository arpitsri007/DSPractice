package org.codekart.bitOperation;

import java.util.Arrays;

public class CountBits {
    // leetcode 2749 - Minimum Operations to Make the Integer Zero
    public int makeTheIntegerZero(int num1, int num2) {
        long t = 1;

        while (true) {
            long value = num1 - t * num2;
            if (value < 0) {
                return -1;
            }
            int bits = countBits(value);

            if (bits <= t && t <= value) {
                return (int)t;
            }

            t++;
        }
    }

    private int countBits(long value) {
        int count = 0;
        while (value > 0) {
            count += value & 1;
            value >>= 1;
        }
        return count;
    }

    // Leetcode 1356 - sort integers by the number of 1 bits
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        int[][] bits = new int[n][2];
        for (int i = 0; i < n; i++) {
            bits[i][0] = arr[i];
            bits[i][1] = countBits(arr[i]);
        }

        // Sort by number of 1 bits, then by value
        Arrays.sort(bits, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        for (int i = 0; i < n; i++) {
            arr[i] = bits[i][0];
        }
        return arr;
    }

}
