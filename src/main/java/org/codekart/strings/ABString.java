package org.codekart.strings;

public class ABString {
    /**
     * 
     * Given two integers A and B, the task is to generate and print a string str
     * such that:
     * 1. str must only contain the characters 'a' and 'b'.
     * 2. str has length A + B and the occurrence of the character 'a' is equal to A
     * and the occurrence of character 'b' is equal to B
     * 3. The sub-strings "aaa" or "bbb" must not occur in str.
     */

    public String generateString(int A, int B) {
        StringBuilder sb = new StringBuilder();
        while (A > 0 || B > 0) {
            if (A > B) {
                if (sb.length() > 1 && sb.charAt(sb.length() - 1) == 'a' && sb.charAt(sb.length() - 2) == 'a') {
                    sb.append("b");
                    B--;
                } else {
                    sb.append("a");
                    A--;
                }
            } else if (B > A) {
                if (sb.length() > 1 && sb.charAt(sb.length() - 1) == 'b' && sb.charAt(sb.length() - 2) == 'b') {
                    sb.append("a");
                    A--;
                } else {
                    sb.append("b");
                    B--;
                }
            } else {
                if (sb.length() > 1 && sb.charAt(sb.length() - 1) == 'a' && sb.charAt(sb.length() - 2) == 'a') {
                    sb.append("b");
                    B--;
                } else {
                    sb.append("a");
                    A--;
                }
            }
        }
        return sb.toString();
    }
}
