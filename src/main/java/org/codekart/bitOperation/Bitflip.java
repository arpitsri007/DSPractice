package org.codekart.bitOperation;

public class Bitflip {
    // code to remove 0th char and append at end. Example: 11100 -> 11001
    public String removeAppendBit(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < s.length(); i++) {
            sb.append(s.charAt(i));
        }

        sb.append(s.charAt(0));

        return sb.toString();
    }

    public int minFlips(String s) {
        int n = s.length();
        s = s + s;
        int result = Integer.MAX_VALUE;

        StringBuilder possS0 = new StringBuilder();
        StringBuilder possS1 = new StringBuilder();

        for (int i = 0; i < 2 * n; i++) {
            possS0.append(i % 2 == 0 ? "0" : "1");
            possS1.append(i % 2 == 0 ? "1" : "0");
        }

        int flip1 = 0;
        int flip2 = 0;
        int j = 0;
        int i = 0;

        while (j < (2 * n) - 1) {

            if (s.charAt(j) != possS0.charAt(j)) {
                flip1++;
            }

            if (s.charAt(j) != possS1.charAt(j)) {
                flip2++;
            }

            if (j - i + 1 > n) {
                if (s.charAt(i) != possS0.charAt(i)) {
                    flip1--;
                }
                if (s.charAt(i) != possS1.charAt(i)) {
                    flip2--;
                }
                i++;
            }

            if (j - i + 1 == n) {
                result = Math.min(result, Math.min(flip1, flip2));
            }

            j++;
        }

        return result;
    }

    public int minFlipsOptimised(String s) {
        int n = s.length();
        // s = s + s;
        int result = Integer.MAX_VALUE;

        StringBuilder possS0 = new StringBuilder();
        StringBuilder possS1 = new StringBuilder();

        for (int i = 0; i < 2 * n; i++) {
            possS0.append(i % 2 == 0 ? "0" : "1");
            possS1.append(i % 2 == 0 ? "1" : "0");
        }

        int flip1 = 0;
        int flip2 = 0;
        int j = 0;
        int i = 0;

        while (j < (2 * n) - 1) {
            if (s.charAt(j % n) != possS0.charAt(j)) {
                flip1++;
            }
            if (s.charAt(j % n) != possS1.charAt(j)) {
                flip2++;
            }

            if (j - i + 1 > n) {
                if (s.charAt(i % n) != possS0.charAt(i)) {
                    flip1--;
                }
                if (s.charAt(i % n) != possS1.charAt(i)) {
                    flip2--;
                }
                i++;
            }

            if (j - i + 1 == n) {
                result = Math.min(result, Math.min(flip1, flip2));
            }

            j++;
        }

        return result;
    }

    public int minFlipsOptimised2(String s) {
        int n = s.length();
        int result = Integer.MAX_VALUE;

        int flip1 = 0;
        int flip2 = 0;
        int j = 0;
        int i = 0;

        while (j < (2 * n) - 1) {
            if (s.charAt(j % n) != (j % 2 == 0 ? '0' : '1')) {
                flip1++;
            }
            if (s.charAt(j % n) != (j % 2 == 0 ? '1' : '0')) {
                flip2++;
            }

            if (j - i + 1 > n) {
                if (s.charAt(i % n) != (i % 2 == 0 ? '0' : '1')) {
                    flip1--;
                }
                if (s.charAt(i % n) != (i % 2 == 0 ? '1' : '0')) {
                    flip2--;
                }
                i++;
            }

            if (j - i + 1 == n) {
                result = Math.min(result, Math.min(flip1, flip2));
            }

            j++;
        }

        return result;
    }

    public static void main(String[] args) {
        Bitflip bitflip = new Bitflip();
        System.out.println(bitflip.removeAppendBit("11100"));
    }
}
