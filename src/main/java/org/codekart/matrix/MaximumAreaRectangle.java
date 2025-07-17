package org.codekart.matrix;

import java.util.Stack;

public class MaximumAreaRectangle {
    // leetcode 85 - Maximal Rectangle
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea = 0;
        // process row by row1
        for(int i = 0; i < n; i++) {
            heights[i] = matrix[0][i] - '0' == 0 ? 0 : 1;
        }
        maxArea = Math.max(maxArea, largestRectangleArea(heights));
        for(int i = 1; i < m; i++) {
            for(int j = 0; j < n; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] width = new int[n];

        int[] NSR = nextSmallerElementToRight(heights);
        int[] NSL = nextSmallerElementToLeft(heights);

        for (int i = 0; i < n; i++) {
            width[i] = NSR[i] - NSL[i] - 1;
        }

        int area = 0;
        for (int i = 0; i < n; i++) {
            area = Math.max(area, heights[i] * width[i]);
        }
        return area;

    }

    public int[] nextSmallerElementToRight(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
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
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }
            stack.push(i);
        }
        return result;
    }
}
