package org.codekart.graph;

import java.util.Arrays;

public class BellmanFord {
    // Concept: https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/

    // what is bellman ford algorithm?
    // Bellman-Ford algorithm is used to find the shortest path from a source vertex to all other vertices in a weighted graph.
    // It is a single-source shortest path algorithm.
    // It is slower than Dijkstra's algorithm but can handle negative edge weights.

    // Difference between Bellman Ford and Dijkstra's algorithm:
    // 1. Bellman Ford can handle negative edge weights, but Dijkstra's algorithm cannot.
    // 2. Bellman Ford is slower than Dijkstra's algorithm.
    // 3. Bellman Ford can detect negative cycle, but Dijkstra's algorithm cannot.

    // steps:
    // 1. Initialize the distance to the source vertex to 0 and to all other vertices to infinity.
    // 2. Relax the edges |V| - 1 times.
        // what is relaxation?
        // relaxation is the process of updating the distance to the node.
        // if the distance to the node is less than the current distance + weight of the edge, then update the distance.
    // 3. If we can still relax the edges, then there is a negative cycle.
    // 4. no need of priority queue in Bellman Ford algorithm since reordering is not required and we are relaxing edges in given order.

    // why |V| - 1 times?
    // because the shortest path can have at most |V| - 1 edges
        // proof:
        // consider a path from source to destination
        // the shortest path can have at most |V| - 1 edges
        // because if there are |V| edges, then there is a cycle
        // and we can always remove the cycle to get a shorter path
        // and we can keep removing the cycle to get a shortest path

    // why O(V * E)?
    // because we are relaxing the edges |V| - 1 times.
    // and we are relaxing the edges in O(E) time.

    // Detecting negative cycle:
    // if we can still relax the edges after |V| - 1 times, then there is a negative cycle.
    // because if there is a negative cycle, then we can keep relaxing the edges to get a shorter path.

    // time complexity: O(V * E)
    // space complexity: O(V)

    // driver code
    public static void main(String[] args) {
        int[][] graph = { { 0, 1, 4 }, { 0, 2, 4 }, { 1, 2, 2 }, { 2, 3, 3 }, { 3, 1, -10 } };
        int[] dist = bellmanFord(graph, 0);
        System.out.println(Arrays.toString(dist));
    }

    public static int[] bellmanFord(int[][] graph, int source) {
        int n = graph.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // relax the edges |V| - 1 times
        for (int i = 0; i < n - 1; i++) {
            for (int[] edge : graph) {
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // check for negative cycle
        for (int[] edge : graph) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Negative cycle detected");
                return new int[] { -1 };
            }
        }
        return dist;

    }
    
    
    
    
    
    
    

}
