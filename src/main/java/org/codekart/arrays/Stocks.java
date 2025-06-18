package org.codekart.arrays;

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

    // leetcode 122 - best time to buy and sell stock II - optimized approach - O(n)

}
