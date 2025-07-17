package org.codekart.stack;

import java.util.Stack;

public class Monotonic {
    // leetcode 402 - Remove K Digits
    // Approach - 1: Using Stack
    public String removeKDigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while(i < num.length()) {
            while(!stack.isEmpty() && k > 0 && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }

            if(stack.isEmpty() && num.charAt(i) == '0') {
                i++;
                continue;
            }

            stack.push(num.charAt(i));
            i++;
        }

        while(k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        String result = sb.reverse().toString();

        if(result.isEmpty() ) {
            return "0";
        }

        // remove leading zeros
        int j = 0;
        while(j < result.length() && result.charAt(j) == '0') {
            j++;
        }

        return result.substring(j);
    }

    // Next smaller element to right
    public int[] nextSmallerElementToRight(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            if(stack.isEmpty()) {
                result[i] = n;
            } else {
                result[i] = stack.peek();
            }
            stack.push(i);
        }

        return result;
    }

    // next smaller element to left
    public int[] nextSmallerElementToLeft(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }
            stack.push(i);
        }
        return result;
    }

    // leetcode 84 - Largest Rectangle in Histogram
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] nextSmallerElementToRight = nextSmallerElementToRight(heights);
        int[] nextSmallerElementToLeft = nextSmallerElementToLeft(heights);
        int maxArea = 0;
        for(int i = 0; i < n; i++) {
            int width = nextSmallerElementToRight[i] - nextSmallerElementToLeft[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }


    // main method
    public static void main(String[] args) {
            Monotonic monotonic = new Monotonic();
            System.out.println(monotonic.largestRectangleArea(new int[]{1,1}));
    }
}
