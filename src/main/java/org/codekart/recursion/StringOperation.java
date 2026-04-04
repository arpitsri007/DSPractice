package org.codekart.recursion;

public class StringOperation {
    // leetcode 1545 - Find Kth Bit in Nth Binary String
    public char findKthBit(int n, int k) {

        StringBuilder sb = new StringBuilder();
        sb.append("0");

        for (int i = 1; i < n; i++) {
            String s = sb.toString();
            sb.append("1");
            sb.append(reverseAndInvert(s));
        }

        return sb.charAt(k - 1);
    }

    private String reverseAndInvert(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i) == '0' ? '1' : '0');
        }
        return sb.toString();
    }

    // Approach2: Recursion
    public char findKthBitRec(int n, int k) {
        if (n == 1) {
            return '0';
        }

        int mid = (1 << (n - 1));
        if (k == mid) {
            return '1';
        } else if (k < mid) {
            return findKthBitRec(n - 1, k);
        } else {
            char c = findKthBitRec(n - 1, 2 * mid - k);
            return c == '0' ? '1' : '0';
        }
    }

    public static void main(String[] args) {
        StringOperation stringOperation = new StringOperation();
        System.out.println(stringOperation.findKthBitRec(4, 11));
    }
}
