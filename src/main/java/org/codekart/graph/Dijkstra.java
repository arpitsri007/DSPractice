package org.codekart.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

// theory: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/

// what is dijkstra's algorithm?
// it is a shortest path algorithm that finds the shortest path between a starting node and all other nodes in a graph.
// it is a greedy algorithm.
// it is a single source shortest path algorithm.
// it is a weighted graph.
// it is a directed graph.

public class Dijkstra {

    // part 1: using priority queue
    // part 2: using set

    // main function
    public static void main(String[] args) {
        // Create adjacency list representation
        // Each node has a list of {destination, weight} pairs
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(new int[]{1, 4});
        graph.get(0).add(new int[]{2, 1});
        graph.get(2).add(new int[]{1, 2});
        graph.get(1).add(new int[]{3, 1});
        graph.get(2).add(new int[]{3, 5});

        System.out.println(Arrays.toString(shortestPath(graph, 0)));
        System.out.println(Arrays.toString(shortestPathUsingSet(graph, 0)));
    }

    // part 1: using priority queue
    public static int[] shortestPath(List<List<int[]>> graph, int src) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{src, 0});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int distance = curr[1];

            if (distance > dist[node]) {
                continue;
            }

            for (int[] neighbor : graph.get(node)) {
                int neighborNode = neighbor[0];
                int neighborDistance = neighbor[1];

                if (distance + neighborDistance < dist[neighborNode]) {
                    dist[neighborNode] = distance + neighborDistance;
                    pq.add(new int[]{neighborNode, dist[neighborNode]});
                }
            }
        }

        return dist;
    }

    // part 2: using set
    public static int[] shortestPathUsingSet(List<List<int[]>> graph, int src) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        Set<Integer> set = new HashSet<>();
        set.add(src);

        while (!set.isEmpty()) {
            int node = set.iterator().next();
            set.remove(node);

            for (int[] neighbor : graph.get(node)) {
                int neighborNode = neighbor[0];
                int neighborDistance = neighbor[1];

                if (dist[node] + neighborDistance < dist[neighborNode]) {
                    dist[neighborNode] = dist[node] + neighborDistance;
                    set.add(neighborNode);
                }
            }
        }

        return dist;
    }

    // shortest distance from source to destination and print the path
    public static void shortestPath(List<List<int[]>> graph, int src, int dest) {
        int n = graph.size();
        int[] dist = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        Arrays.fill(parent, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int distance = curr[1];

            if (distance > dist[node]) {
                continue;
            }

            for (int[] neighbor : graph.get(node)) {
                int neighborNode = neighbor[0];
                int neighborDistance = neighbor[1];

                if(distance + neighborDistance < dist[neighborNode]) {
                    dist[neighborNode] = distance + neighborDistance;
                    pq.add(new int[]{neighborNode, dist[neighborNode]});
                    parent[neighborNode] = node;
                }
            }
        }

        System.out.println("Shortest distance from " + src + " to " + dest + " is " + dist[dest]);
        
        // print the path
        int temp = dest;
        while(temp != -1) {
            System.out.print(temp + " ");
            temp = parent[temp];
        }
        
    }

    

}
