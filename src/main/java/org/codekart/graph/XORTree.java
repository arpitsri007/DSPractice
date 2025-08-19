package org.codekart.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XORTree {
    // leetcode 2322 - Minimize XOR after removing 2 edges to make 3 components

    private int time = 0;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        // intime and outtime of each node to know if a node1 is a ancestor of node2
        // and vice versa
        // xor array to find the xor of the subtree of a node

        // dfs to find the xor of the subtree of a node
        // dfs to find the inTime and outTime of each node
        // dfs to find the minimum score after removing 2 edges to make 3 components

        int[] xor = new int[n];
        int[] inTime = new int[n];
        int[] outTime = new int[n];
        dfs(0, -1, graph, nums, xor, inTime, outTime);

        int minScore = Integer.MAX_VALUE;
        for (int u = 1; u < n; u++) {
            for (int v = u + 1; v < n; v++) {
                int xor1;
                int xor2;
                int xor3;

                if (isAncestor(inTime, outTime, u, v)) {
                    xor1 = xor[v];
                    xor2 = xor[u] ^ xor1;
                    xor3 = xor[0] ^ xor1 ^ xor2;
                } else if (isAncestor(inTime, outTime, v, u)) {
                    xor1 = xor[u];
                    xor2 = xor[v] ^ xor1;
                    xor3 = xor[0] ^ xor1 ^ xor2;
                } else {
                    xor1 = xor[u];
                    xor2 = xor[v];
                    xor3 = xor[0] ^ xor1 ^ xor2;
                }

                minScore = Math.min(minScore, getScore(xor1, xor2, xor3));
            }
        }

        return minScore;
    }

    private int getScore(int xor1, int xor2, int xor3) {
        int maxXor = Math.max(xor1, Math.max(xor2, xor3));
        int minXor = Math.min(xor1, Math.min(xor2, xor3));
        return maxXor - minXor;
    }

    private boolean isAncestor(int[] inTime, int[] outTime, int u, int v) {
        return inTime[u] <= inTime[v] && outTime[u] >= outTime[v];
    }

    private void dfs(int node, int parent, Map<Integer, List<Integer>> graph, int[] nums, int[] xor, int[] inTime,
            int[] outTime) {
        xor[node] = nums[node];
        inTime[node] = time;
        time++;
        for (int neighbor : graph.get(node)) {
            if (neighbor == parent)
                continue;
            dfs(neighbor, node, graph, nums, xor, inTime, outTime);
            xor[node] ^= xor[neighbor];
        }
        outTime[node] = time;
        time++;
    }
}
