package org.codekart.graph;

import java.util.Arrays;
import java.util.Stack;

public class StrongConnectedComponent {
    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0}
        };
        System.out.println(findStrongConnectedComponent(graph));
    }

    // Kosaraju's algorithm:
    // 0. Store in stack the nodes in the order of their finishing time.
    // 1. Do a DFS on the graph and store the nodes in the order of their finishing time.
    // 2. Reverse the graph.
    // 3. Do a DFS on the reversed graph and store the nodes in the order of their finishing time.
    // 4. The number of SCCs is the number of times we can do a DFS on the reversed graph.

    public static int findStrongConnectedComponent(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited, stack);
            }
        }
        int[][] reversedGraph = reverseGraph(graph);
        int count = 0;
        Arrays.fill(visited, false);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                dfs(reversedGraph, node, visited);
                count++;
            }
        }
        return count;
    }

    private static int[][] reverseGraph(int[][] graph) {
        int n = graph.length;
        int[][] reversedGraph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                reversedGraph[i][j] = graph[j][i];
            }
        }
        return reversedGraph;
    }

    private static void dfs(int[][] graph, int node, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited);
            }
        }
    }

    private static void dfs(int[][] graph, int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited, stack);
            }
        }
        stack.push(node);
    }
    
}
