package org.codekart.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

import util.Pair;

public class Maximise {

    // main method
    public static void main(String[] args) {
        int[] speed = { 2, 10, 3, 1, 5 };
        int[] efficiency = { 5, 4, 3, 9, 7 };
        int k = 2;
        System.out.println(maxPerformance(5, speed, efficiency, k));
    }

    // leetcode 1383 - using max heap
    public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int MOD = 1000000007;
        int[][] engineers = new int[n][2];
        for (int i = 0; i < n; i++) {
            engineers[i][0] = speed[i];
            engineers[i][1] = efficiency[i];
        }

        Arrays.sort(engineers, (a, b) -> b[1] - a[1]);

        // min heap to store the speed of the engineers
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long maxPerformance = 0;
        long totalSpeed = 0;

        for (int[] engineer : engineers) {
            minHeap.add(engineer[0]);
            totalSpeed += engineer[0];

            if (minHeap.size() > k) {
                totalSpeed -= minHeap.poll();
            }

            maxPerformance = Math.max(maxPerformance, totalSpeed * engineer[1]);
        }

        return (int) (maxPerformance % MOD);
    }
}
