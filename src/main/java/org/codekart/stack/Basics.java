package org.codekart.stack;

import java.util.Arrays;
import java.util.Stack;

public class Basics {

    public static void main(String[] args) {
        int[] arr = { 100, 80, 60, 70, 60, 75, 85 };
        int[] result = onlineStockSpan(arr);
        System.out.println(Arrays.toString(result));
    }

    // online stock span problem
    public static int[] onlineStockSpan(int[] arr) {
        int[] result = new int[arr.length];
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            int span = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= arr[i]) {
                span += stack.peek()[1];
                stack.pop();
            }
            result[i] = span;
            stack.push(new int[] { arr[i], span });
        }
        return result;
    }

    // leetcode 739 daily temperatures

    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];
        Stack<Integer> st = new Stack<>();

        for (int i = len - 1; i >= 0; i--) {
            while (!st.empty() && temperatures[st.peek()] <= temperatures[i]) {
                st.pop();
            }

            res[i] = st.empty() ? 0 : st.peek() - i;
            st.push(i);
        }

        return res;
    }

    // remove adjacent duplicates in string using brute force without stack
    // TC: O(n^2)
    public static String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == s.charAt(i)) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    // remove adjacent duplicates in string using stack
    // TC: O(n)
    public static String removeDuplicatesUsingStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    // basic calculator with proper explanation
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int sign = 1;
        int number = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                result *= stack.pop();
                result += stack.pop();
            }
        }
        return result + sign * number;
    }

    // sum of subarray minimums
    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // monotonic stack to find the next smaller element
        // TC: O(n)
        // SC: O(n)
        // Monotonic stack is a stack where the elements are in a specific order
        // In this case, the elements are in ascending order
        // We are using the stack to find the next smaller element
        // If the current element is smaller than the top of the stack, we pop the stack
        // and continue until we find the next smaller element
        // The next smaller element is the element that is smaller than the current
        // element
        // and is the closest to the current element
        // This is monotonic increasing stack
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i : stack.peek();
            stack.push(i);
        }
        stack.clear();

        // monotonic stack to find the previous smaller element
        // TC: O(n)
        // SC: O(n)
        // This is monotonic increasing stack
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            long leftCount = i - left[i];
            long rightCount = right[i] - i;
            sum += (leftCount * rightCount * arr[i]) % 1000000007;
        }
        return sum;
    }

    // implement queue using stack
    public static class QueueUsingStack {
        Stack<Integer> inputStack = new Stack<>();
        Stack<Integer> outputStack = new Stack<>();

        public void push(int x) {
            inputStack.push(x);
        }

        public int pop() {
            if (outputStack.isEmpty()) {
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.pop();
        }

        public int peek() {
            if (outputStack.isEmpty()) {
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.peek();
        }

        public boolean empty() {
            return inputStack.isEmpty() && outputStack.isEmpty();
        }
    }

    // reverse polish notation using lambda
    // can we use lambda to solve this problem?
    // yes, we can use lambda to solve this problem
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (token.equals("-")) {
                stack.push(stack.pop() - stack.pop());
            } else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (token.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public static int evalRPNUsingLambda(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Arrays.stream(tokens).forEach(token -> {
            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (token.equals("-")) {
                stack.push(stack.pop() - stack.pop());
            } else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (token.equals("/")) {
                stack.push(stack.pop() / stack.pop());
            } else {
                stack.push(Integer.parseInt(token));
            }
        });
        return stack.pop();
    }

}
