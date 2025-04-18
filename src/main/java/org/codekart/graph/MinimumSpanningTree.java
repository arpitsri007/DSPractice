package org.codekart.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

// Kruskal's algorithm:
// 1. Sort all the edges in non-decreasing order of their weight.
// 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If cycle is not formed, include this edge. Else, discard it.
// 3. Repeat step 2 until there are (V-1) edges in the spanning tree.

// What is Spanning Tree:
// Graph should be connected
// Graph should have V-1 edges
// Graph should not have any cycles
// Spanning Tree is a subset of the edges of a connected, edge-weighted undirected graph that connects all the vertices together, without any cycles.

// Minimum Spanning Tree is a spanning tree with the minimum possible total edge weight.

public class MinimumSpanningTree {

    // driver code:
    public static void main(String[] args) {
        int[][] graph = {
                { 0, 5, Integer.MAX_VALUE, 10 },
                { Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1 },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0 }
        };
        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree();
        int result = minimumSpanningTree.prim(graph);
        System.out.println(result);

        // Minimum cost to connect all points:
        int[][] points = { { 0, 0 }, { 2, 2 }, { 3, 10 }, { 5, 2 }, { 7, 0 } };
        result = minimumSpanningTree.minCostConnectPoints(points);
        System.out.println(result);
    }


    // Prim's algorithm:
    // 1. Start with any vertex in the graph.
    // 2. Pick the smallest edge from the current vertex to any other vertex.
    // 2.1. If the edge forms a cycle with the spanning tree formed so far, discard it.
    // 2.2. If the edge does not form a cycle with the spanning tree formed so far add it to the spanning tree.
    // 2.3 Put it in the priority queue. - Min heap - O(log V) - for each edge
    // 2.3.1. Structure of the priority queue:
    // 2.3.1.1. Key: Edge weight
    // 2.3.1.2. Value: Edge
    // 2.4 Pick the smallest edge from the priority queue.
    // 2.5 Repeat step 2 until there are (V-1) edges in the spanning tree.
    // 3. Repeat step 2 until there are (V-1) edges in the spanning tree.

    // Take Parent array to store the parent of each vertex.
    // Take InMst array to store the vertices in the spanning tree.
    public int prim(int[][] graph) {
        int n = graph.length;
        int[] parent = new int[n];
        boolean[] inMst = new boolean[n];

        // Initialize the parent array to -1 and the inMst array to false.
        Arrays.fill(parent, -1);
        Arrays.fill(inMst, false);

        int sum = 0;

        // Initialize the priority queue.
        // Priority queue will store the edges with the smallest weight. - {weight, source, parent
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Add the first vertex to the priority queue.
        pq.add(new int[] { 0, 0, -1 });

        // Process the priority queue until it is empty.
        while (!pq.isEmpty()) {  // E times
            int[] edge = pq.poll(); 
            int weight = edge[0];
            int source = edge[1];
            int pNode = edge[2];  // O(log V)

            // If the vertex is already in the spanning tree, skip it.
            if (inMst[source]) {
                continue;
            }

            // Add the vertex to the spanning tree.
            inMst[source] = true;
            parent[source] = pNode;
            sum += weight;
            // Add the edges of the vertex to the priority queue.
            for (int i = 0; i < n; i++) { // V times
                if (graph[source][i] != Integer.MAX_VALUE && !inMst[i]) {
                    pq.add(new int[] { graph[source][i], i, source }); // O(log V)
                }
            }
        }
        // Return the spanning tree.
        return sum;
    }
    // Overall TC: O(V log V + E log V)
    // Overall SC: O(V + E)
    // why TC is O(V log V + E log V)?

    // Minimum cost to connect all points:
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
            }
        }
        return prim(graph);
    }

    // Kruskal's algorithm:
    public int kruskal(int[][] graph) {
        int n = graph.length;
        int[] parent = new int[n];
        boolean[] inMst = new boolean[n];
        Arrays.fill(parent, -1);
        Arrays.fill(inMst, false);
    }
}
