package org.codekart.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Paranthesis {
    // leetcode 22
    public static void main(String[] args) {
        int n = 2;
        System.out.println(generateParenthesis(n));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        generateParenthesisHelper(result, sb, n);

        return result;
    }

    // TC - O(2^2n) * O(2n) -- since length of the string is 2n
    // SC - Depth of the recursion tree is 2n, so O(2n)
    // Space complexity is due to the recursion stack

    private static void generateParenthesisHelper(List<String> result, StringBuilder sb, int n) {
        // base case
        if (sb.length() == 2 * n) {
            if (isBalancedParenthesis(sb.toString())) {
                result.add(sb.toString());
            }
            return;
        }
        // recursive case
        // add "("
        sb.append("(");
        generateParenthesisHelper(result, sb, n);
        sb.deleteCharAt(sb.length() - 1); // undo the last operation

        // add ")"
        sb.append(")");
        generateParenthesisHelper(result, sb, n);
        sb.deleteCharAt(sb.length() - 1); // undo the last operation
    }

    // optimal solution for generating parenthesis
    public static List<String> generateParenthesisOptimal(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        generateParenthesisHelperOptimal(result, sb, n, 0, 0);
        return result;
    }

    private static void generateParenthesisHelperOptimal(List<String> result, StringBuilder sb, int n, int open,
            int close) {
        // base case
        if (sb.length() == 2 * n) {
            result.add(sb.toString());
            return;
        }
        // recursive case
        if (open < n) {
            sb.append("(");
            // add "("
            generateParenthesisHelperOptimal(result, sb, n, open + 1, close);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(")");
            // add ")"
            generateParenthesisHelperOptimal(result, sb, n, open, close + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // TC - O(2^2n) -- since length of the string is 2n
    // SC - Depth of the recursion tree is 2n, so O(2n)
    // Space complexity is due to the recursion stack
    private static boolean isBalancedParenthesis(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(c);
            else {
                if (stack.isEmpty() || stack.peek() != '(')
                    return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
