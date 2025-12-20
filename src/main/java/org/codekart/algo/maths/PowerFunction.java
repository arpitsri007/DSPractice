package org.codekart.algo.maths;

public class PowerFunction {
    // leetcode 326
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    // approach 2 - recursive
    public boolean isPowerOfThreeRecursive(int n) {
        if (n <= 0) {
            return false;
        }

        if (n == 1) {
            return true;
        }
        if (n % 3 != 0) {
            return false;
        }
        return isPowerOfThreeRecursive(n / 3);
    }

    // approach 3 - log
    public boolean isPowerOfThreeLog(int n) {
        if (n <= 0) {
            return false;
        }
        double x = Math.log10(n) / Math.log10(3);
        return x == (int) x;
    }
}
