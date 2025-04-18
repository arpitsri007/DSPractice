package org.codekart.graph;

import java.util.Arrays;

// Concept: https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/

// What is Floyd Warshall algorithm?
// Floyd Warshall algorithm is used to find the shortest path between all pairs of vertices in a weighted graph.
// It is a dynamic programming algorithm.
// It is a multi-source shortest path algorithm.
// It says that iterate through all vertices as intermediate vertices and check if the distance to the other vertices can be reduced by going through the vertex.

// How to implement Floyd Warshall algorithm?
// 1. Initialize the distance matrix with the weight of the edges.
// 2. For each vertex, check if the distance to the other vertices can be reduced by going through the vertex.
// 3. If the distance can be reduced, then update the distance matrix.

// TC: O(V^3)
// SC: O(V^2)

// Why O(V^3)?
// Because we are checking all pairs of vertices and for each pair, we are checking all other vertices to see if the distance can be reduced.

// Why O(V^2)?
// Because we are using a matrix to store the distance between all pairs of vertices.



public class FloydWarshall {
    // steps:
    // 1. Initialize the distance matrix with the weight of the edges.
    // 2. For each vertex, check if the distance to the other vertices can be reduced by going through the vertex.
    // 3. If the distance can be reduced, then update the distance matrix.

   
    // graph is a 2D array where graph[i][j] is the weight of the edge between vertex i and vertex j.
    // if there is no edge between vertex i and vertex j, then graph[i][j] is Integer.MAX_VALUE.
    // if i == j, then graph[i][j] is 0.

    // dist[i][j] is the shortest distance between vertex i and vertex j.
    // dist[i][j] is initialized with the weight of the edges.
    // dist[i][j] is updated with the shortest distance between vertex i and vertex j.
    // dist[i][j] is the minimum of the distance between vertex i and vertex j and the distance between vertex i and vertex k plus the distance between vertex k and vertex j.
    // dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);


    // driver code:
    public static void main(String[] args) {
        int[][] graph = {
            {0, 5, Integer.MAX_VALUE, 10},
            {Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        FloydWarshall floydWarshall = new FloydWarshall();
        int[][] dist = floydWarshall.floydWarshall(graph);
        System.out.println(Arrays.deepToString(dist));
    }

    // detect negative cycle in the graph:
    // if dist[i][i] is negative for any vertex i, then there is a negative cycle in the graph.
    public int[][] floydWarshall(int[][] graph) {
        int n = graph.length;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = graph[i][j];
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                // negative cycle detection:
                if (dist[i][k] != Integer.MAX_VALUE && dist[k][k] < 0) {
                    System.out.println("Negative cycle detected");
                    return null;
                }
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }

    // Negative cycle detection:
    // if dist[i][i] is negative for any vertex i, then there is a negative cycle in the graph.
    // because if there is a negative cycle, then we can keep relaxing the edges to get a shorter path.
    // so if we can still relax the edges after |V| - 1 times, then there is a negative cycle.

    public boolean isNegativeCycle(int[][] dist) {
        int n = dist.length;
        for (int i = 0; i < n; i++) {
            if (dist[i][i] < 0) {
                return true;
            }
        }
        return false;
    }
}
