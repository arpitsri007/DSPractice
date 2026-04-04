package org.codekart.bitOperation;

public class ConcatenateBinary {
    // leetcode 1680 - Concatenation of Consecutive Binary Numbers
    public int concatenatedBinary(int n) {
        long result = 0;
        int mod = 1000000007;
        int len = 0;

        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) { // TIP: check if i is a power of 2, if it is then we need to increase the length of binary representation
                len++;
            }
            // alternate way using log
            // len = (int) (Math.log(i) / Math.log(2)) + 1;
            
            result = ((result << len) + i) % mod;
        }
        return (int) result;
    }
}
