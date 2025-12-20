package org.codekart.bitOperation;

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

}
