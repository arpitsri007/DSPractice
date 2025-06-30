package org.codekart.arrays;

import java.util.Stack;

public class Stocks {
    // leetcode 121 - best time to buy and sell stock
    // brute force approach - O(n^2)
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }
        return maxProfit;
    }

    // leetcode 121 - best time to buy and sell stock - optimized approach - O(n)
    public static int maxProfitOptimized(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }

    // using auxillary array
    public static int maxProfitUsingAuxillaryArray(int[] prices) {
        int n = prices.length;
        int[] auxillary = new int[n];
        auxillary[n - 1] = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            auxillary[i] = Math.max(auxillary[i + 1], prices[i]);
        }
        int maxProfit = 0;
        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, auxillary[i] - prices[i]);
        }
        return maxProfit;
    }

    // leetcode 122 - best time to buy and sell stock II - optimized approach - O(n)
    // & space O(n)
    // using stack
    public static int maxProfitUsingStack(int[] prices) {
        int n = prices.length;
        Stack<Integer> stack = new Stack<>();
        int maxProfit = 0;
        for (int i = 0; i < n; i++) {
            if (!stack.isEmpty() && stack.peek() < prices[i]) {
                maxProfit += prices[i] - stack.pop();
            }
            stack.push(prices[i]);
        }
        return maxProfit;
    }

    // leetcode 122 - best time to buy and sell stock II - optimized approach - O(n)
    // & space O(1)
    public static int maxProfitUnlimitedTransactions(int[] prices) {
        int n = prices.length;
        int maxProfit = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    // TODO: Best time to buy and sell stock III and IV & Best Time to Buy and Sell
    // Stock with Cooldown & Best time to buy and sell stock with transaction fee

}
