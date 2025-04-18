package org.codekart.graph;

import java.util.*;

import util.Pair;

// theory: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/

// what is dijkstra's algorithm?
// it is a shortest path algorithm that finds the shortest path between a starting node and all other nodes in a graph.
// it is a greedy algorithm.
// it is a single source shortest path algorithm.
// it is a weighted graph.
// it is a directed graph.

// time complexity: O(V * log(V) + E * log(V))
// space complexity: O(V)

// why O(V * log(V) + E * log(V))?
// because we are using a priority queue to get the minimum distance node in O(log(V)) time.
// and we are relaxing the edges in O(E) time.

// steps for Dijkstra's algorithm:
// 1. Initialize the distance to the source vertex to 0 and to all other vertices to infinity.
// 2. Add the source vertex to the priority queue.
// 3. While the priority queue is not empty:
// 4. Get the node with the minimum distance from the priority queue.
// 5. Relax the edges of the node.
    // what is relaxation?
    // relaxation is the process of updating the distance to the node.
    // if the distance to the node is less than the current distance + weight of the edge, then update the distance and add the node to the priority queue.




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
        graph.get(0).add(new int[] { 1, 4 });
        graph.get(0).add(new int[] { 2, 1 });
        graph.get(2).add(new int[] { 1, 2 });
        graph.get(1).add(new int[] { 3, 1 });
        graph.get(2).add(new int[] { 3, 5 });

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
        pq.add(new int[] { src, 0 });

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
                    pq.add(new int[] { neighborNode, dist[neighborNode] });
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
        pq.add(new int[] { src, 0 });

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
                    pq.add(new int[] { neighborNode, dist[neighborNode] });
                    parent[neighborNode] = node;
                }
            }
        }

        System.out.println("Shortest distance from " + src + " to " + dest + " is " + dist[dest]);

        // print the path
        int temp = dest;
        while (temp != -1) {
            System.out.print(temp + " ");
            temp = parent[temp];
        }

    }

    // network time delay - leetcode 743
    // https://leetcode.com/problems/network-delay-time/

    public static class NetworkDelayTime {
        int n;
        int start;
        int[][] times;

        public NetworkDelayTime(int n, int[][] times, int start) {
            this.n = n;
            this.start = start;
            this.times = times;
        }

        // driver code
        public static void main(String[] args) {
            int[][] times = { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 }, { 4, 5, 1 } };
            int n = 5;
            int start = 2;
            NetworkDelayTime networkDelayTime = new NetworkDelayTime(n, times, start);
            System.out.println(networkDelayTime.networkDelayTime());
        }

        public int networkDelayTime() {
            // create adjacency list
            List<List<int[]>> graph = new ArrayList<>();

            // Here, nodes are 1-indexed, so we need to convert it to 0-indexed
            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int[] time : times) {
                graph.get(time[0]).add(new int[] { time[1], time[2] });
            }

            int[] dist = shortestPath(graph, start);

            int max = 0;
            for (int i = 1; i < n + 1; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    return -1;
                }
                max = Math.max(max, dist[i]);
            }
            return max;
        }

    }

    public static class ShortestPathInBinaryMatrix {
        // driver code
        public static void main(String[] args) {
            int[][] grid = { { 0, 0, 0 }, { 1, 1, 0 }, { 1, 1, 0 } };
            System.out.println(shortestPathBinaryMatrix(grid));
            // recreate the grid
            grid = new int[][] { { 0, 0, 0 }, { 1, 1, 0 }, { 1, 1, 0 } };
            System.out.println(shortestPathBinaryMatrixUsingDijkstra(grid));
        }

        // using normal BFS
        public static int shortestPathBinaryMatrix(int[][] grid) {

            int n = grid.length;
            int m = grid[0].length;

            if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1) {
                return -1;
            }

            int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
                    { -1, -1 } };
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[] { 0, 0 });
            grid[0][0] = 1;
            int steps = 1;

            while (!queue.isEmpty()) {
                int size = queue.size();

                // process all nodes at current level
                for (int i = 0; i < size; i++) {
                    int[] curr = queue.poll();
                    int x = curr[0];
                    int y = curr[1];

                    if (x == n - 1 && y == m - 1) {
                        return steps;
                    }

                    for (int[] direction : directions) {
                        int newX = x + direction[0];
                        int newY = y + direction[1];

                        if (newX >= 0 && newX < n && newY >= 0 && newY < m && grid[newX][newY] == 0) {
                            queue.add(new int[] { newX, newY });
                            grid[newX][newY] = 1; // mark as visited
                        }
                    }
                }
                steps++;
            }
            return -1;
        }

        // using Dijkstra's algorithm
        public static int shortestPathBinaryMatrixUsingDijkstra(int[][] grid) {

            int n = grid.length;
            int m = grid[0].length;

            if(grid[0][0] == 1 || grid[n-1][m-1] == 1) {
                return -1;
            }

            int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
                    { -1, -1 } };

            int[][] dist = new int[n][m];
            for(int i = 0; i < n; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            dist[0][0] = 1;

            // use pair class to store the node and the distance
            PriorityQueue<Pair<Pair<Integer, Integer>, Integer>> pq = new PriorityQueue<>((a, b) -> a.getSecond() - b.getSecond());
            pq.add(new Pair<>(new Pair<>(0, 0), 1));

            while(!pq.isEmpty()) {
                Pair<Pair<Integer, Integer>, Integer> curr = pq.poll();
                int x = curr.getFirst().getFirst();
                int y = curr.getFirst().getSecond();
                int distance = curr.getSecond();

                if(distance > dist[x][y]) {
                    continue;
                }

                if(x == n - 1 && y == m - 1) {
                    return distance;
                }

                for(int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];

                    if(newX >= 0 && newX < n && newY >= 0 && newY < m && grid[newX][newY] == 0) {
                        if(distance + 1 < dist[newX][newY]) {
                            dist[newX][newY] = distance + 1;
                            pq.add(new Pair<>(new Pair<>(newX, newY), distance + 1));
                        }   
                    }
                }
            }
            return dist[n-1][m-1];
        }
    }


    public static class PathWithMinimumEffort {
        // driver code
        public static void main(String[] args) {
            int[][] grid = { { 1, 2, 2 }, { 3, 8, 2 }, { 5, 3, 5 } };
            System.out.println(minimumEffortPath(grid));
        }

        public static int minimumEffortPath(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;

            int[][] dist = new int[n][m];
            for(int i = 0; i < n; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            dist[0][0] = 0;

          
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
            pq.add(new int[] { 0, 0, 0 });

            int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

            while(!pq.isEmpty()) {
                int[] curr = pq.poll();
                int x = curr[0];
                int y = curr[1];
                int effort = curr[2];

                if(effort > dist[x][y]) {
                    continue;
                }

                if(x == n - 1 && y == m - 1) {
                    return effort;
                }

                for(int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];

                    if(newX >= 0 && newX < n && newY >= 0 && newY < m) {
                        int newEffort = Math.max(effort, Math.abs(grid[newX][newY] - grid[x][y]));

                        if(newEffort < dist[newX][newY]) {
                            dist[newX][newY] = newEffort;
                            pq.add(new int[] { newX, newY, newEffort });
                        }
                    }
                }
            }
            return dist[n-1][m-1] == Integer.MAX_VALUE ? -1 : dist[n-1][m-1];
        }

    }

}
