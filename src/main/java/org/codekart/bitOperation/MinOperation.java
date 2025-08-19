package org.codekart.bitOperation;

public class MinOperation {
    // leetcode 2571 - Minimum Operations to Reduce an Integer to 0
    // using recursion

    public int minOperations(int n) {
        return minOperationsHelper(n);
    }

    // Add or substract a power of 2 from n
    private int minOperationsHelper(int n) {
        if (n == 0) {
            return 0;
        }

        int result = 0;

        /**
         * Idea:
         * if there is group of 1s in the binary representation of n, then we can add 1
         * to the group to make it 0 and it will add 1 to the MSB
         * if there is single 1 in the binary representation of n, then we can substract
         * 1 from the n to make it 0 shift to right;
         */

        while (n > 0) {
            if ((n & 1) == 1) {
                result++;

                n = n >> 1;

                if ((n & 1) == 0) {
                    continue;
                }

                while ((n & 1) == 1) {
                    n = n >> 1;
                }

                n = (n | 1);

            } else {
                n = n >> 1;
            }
        }

        return result;
    }

}
