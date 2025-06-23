package org.codekart.recursion;

import java.util.ArrayList;
import java.util.List;

public class ValidIP {
    // leetcode 93 - Restore IP Addresses
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return result;
        }
        List<String> segments = new ArrayList<>();
        backtrack(s, 0, segments, result);
        return result;
    }

    private void backtrackV0(String s, int index, int parts, StringBuilder sb, List<String> result) {
        if (parts == 4) {
            if (index == s.length()) {
                // remove last dot
                result.add(sb.toString().substring(0, sb.length() - 1));
                return;
            }
            return;
        }

        for (int len = 1; len <= 3 && index + len <= s.length(); len++) {
            String segment = s.substring(index, index + len);
            if (isValid(segment)) {
                int originalLength = sb.length();
                sb.append(segment).append(".");
                backtrackV0(s, index + len, parts + 1, sb, result);
                sb.delete(originalLength, sb.length());
            }
        }
    }

    private void backtrack(String s, int index, List<String> segments, List<String> result) {
        if (segments.size() == 4) {
            if (index == s.length()) {
                result.add(String.join(".", segments));
            }
            return;
        }

        for (int len = 1; len <= 3 && index + len <= s.length(); len++) {
            String segment = s.substring(index, index + len);
            if (isValid(segment)) {
                segments.add(segment);
                backtrack(s, index + len, segments, result);
                segments.remove(segments.size() - 1);
            }
        }
    }

    private boolean isValid(String str) {
        if (str.length() > 3) {
            return false;
        }
        if (str.length() > 1 && str.charAt(0) == '0') {
            return false;
        }
        int num = Integer.parseInt(str);
        return num >= 0 && num <= 255;
    }

    // main method
    public static void main(String[] args) {
        ValidIP validIP = new ValidIP();
        System.out.println(validIP.restoreIpAddresses("25525511135"));
    }
}
