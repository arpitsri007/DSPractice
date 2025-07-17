package org.codekart.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Paranthesis {
    // leetcode 1249 - Minimum Remove to Make Valid Parentheses
    // Approach - 1: Using Stack
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> set = new HashSet<>();
        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    set.add(i);
                } else {
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()) {
            set.add(stack.pop());
        }

        String result = "";
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(i)) {
                result += s.charAt(i);
            }
        }

        return result;
    }

    // Approach - 2: using open and close count
    public String minRemoveToMakeValidOptimized(String s) {
        int open = 0;

        String temp = "";

        // iterate from left to right --> Eliminate invalid closing paranthesis

        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                temp += s.charAt(i);
            } else if (s.charAt(i) == '(') {
                open++;
                temp += s.charAt(i);
            } else if (s.charAt(i) == ')') {
                if (open > 0) {
                    open--;
                    temp += s.charAt(i);
                }
            }
        }

        int close = 0;
        String result = "";

        // iterate from right to left --> Eliminate invalid opening paranthesis
        for (int i = temp.length() - 1; i >= 0; i--) {
            if (Character.isLetter(temp.charAt(i))) {
                result = temp.charAt(i) + result;
            } else if (temp.charAt(i) == ')') {
                close++;
                result = temp.charAt(i) + result;
            } else if (temp.charAt(i) == '(') {
                if (close > 0) {
                    close--;
                    result = temp.charAt(i) + result;
                }
            }
        }

        return result;
    }

    // leetcode 1190 - Reverse Substrings Between Each Pair of Parentheses
    public String reverseParentheses(String s) {

        Stack<Integer> lastSkipLengthStack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                lastSkipLengthStack.push(result.length());
            } else if (s.charAt(i) == ')') {
                int lastSkipLength = lastSkipLengthStack.pop();
                // reverse the result string from lastSkipLength to result.length()
                // StringBuilder rev = new StringBuilder(result.substring(lastSkipLength, result.length())).reverse();
                // result.delete(lastSkipLength, result.length());
                // result.append(rev);
                String subStr = result.substring(lastSkipLength, result.length());
                result.setLength(lastSkipLength);
                result.append(new StringBuilder(subStr).reverse());
            } else {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }

    // main method
    public static void main(String[] args) {
        Paranthesis paranthesis = new Paranthesis();
        // System.out.println(paranthesis.minRemoveToMakeValid("a)b(c)d"));
        // System.out.println(paranthesis.minRemoveToMakeValidOptimized("lee(t(c)o)de)"));
        System.out.println(paranthesis.reverseParentheses("(u(love)i)"));
    }

}
