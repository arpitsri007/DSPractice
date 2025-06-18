package org.codekart.heap;

import java.util.PriorityQueue;

public class CostMiniser {
    // leetcode 1167 - using min heap
    public static int connectSticks(int[] sticks) {
        if (sticks.length == 1) {
            return 0;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int stick : sticks) {
            minHeap.add(stick);
        }

        int cost = 0;
        while (minHeap.size() > 1) {
            int sum = minHeap.poll() + minHeap.poll();
            cost += sum;
            minHeap.add(sum);
        }

        return cost;
    }

    public static void main(String[] args) {
        int[] sticks = { 1, 2, 3, 4, 5 };
        System.out.println(connectSticks(sticks));
    }
}
