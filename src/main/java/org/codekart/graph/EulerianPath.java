package org.codekart.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EulerianPath {
    // Eulerian Path is a path in a graph that visits all the edges and every edge
    // exactly once.
    // Not all graphs have Eulerian Path.
    // A graph has Eulerian Path if and only if it is connected and has exactly two
    // vertices with odd degree.

    // Eulerian path which starts and ends at the same vertex is called Eulerian
    // Circuit or Eulerian Cycle.

    // One has to choose the starting vertex wisely.
    // If there are two vertices with odd degree, then one has to start from one of
    // them and if there are no vertices with odd degree, then one has to start from
    // any vertex.

    // If a graph doesn't have Eulerian Path,
    // either we won't be able to come back to the starting vertex or we will miss
    // some edges.

    // All vertices with non-zero degree must belong to a single connected
    // component.

    // Semi-Eulerian Graph: A graph with Eulerian Path but not Eulerian Circuit.
    // Eulerian Circuit: A graph with Eulerian Path and Eulerian Circuit.

    // Start vertex of Eulerian Path:
    // 1. If there are two vertices with odd degree, then one has to start from one
    // of them.

    // End vertex of Eulerian Path:
    // 1. If there are two vertices with odd degree, then one has to end at one of
    // them.

    // Non zero degree vertices must belong to a single connected component.

    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)

    public static void main(String[] args) {
        int[][] graph = { { 0, 1, 0, 1 }, { 1, 0, 1, 0 }, { 0, 1, 0, 1 }, { 1, 0, 1, 0 } };
        System.out.println(findIsEulerianPathOrCircuit(graph));
    }

    // Check if all vertices with non-zero degree belong to a single connected
    // if all vertices with even degree in a connected graph, then Eulerian circuit
    private static boolean findIsEulerianPathOrCircuit(int[][] graph) {
        int n = graph.length;
        int[] degree = calculateDegree(graph);

        if (!isConnected(graph, degree)) {
            return false;
        }

        int oddDegreeCount = 0;
        for (int i = 0; i < n; i++) {
            if (degree[i] % 2 != 0) {
                oddDegreeCount++;
            }
        }

        if (oddDegreeCount == 0) {
            return true;
        } else if (oddDegreeCount == 2) {
            return true;
        }
        return false;
    }

    private static int[] calculateDegree(int[][] graph) {
        int n = graph.length;
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] != 0) {
                    degree[i]++;
                }
            }
        }
        return degree;
    }

    // Check if all vertices with non-zero degree belong to a single connected
    // component.
    private static boolean isConnected(int[][] graph, int[] degree) {
        int n = graph.length;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i] && degree[i] != 0) {
                dfs(graph, i, visited);
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && degree[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(int[][] graph, int node, boolean[] visited) {
        visited[node] = true;
        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] != 0 && !visited[i]) {
                dfs(graph, i, visited);
            }
        }
    }

    // Find Eulerian Path or Circuit in  directed graph
    private static void findEulerianPathOrCircuit(int[][] graph) {
        int n = graph.length;
        int[] inDegree = new int[n];
        int[] outDegree = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] != 0) {
                    outDegree[i]++;
                    inDegree[j]++;
                }
            }
        }

        // source vertex => i inDegree[i] == outDegree[i] + 1
        // sink vertex => i outDegree[i] == inDegree[i] + 1
        // other vertices => inDegree[i] == outDegree[i]

        int source = -1;
        int sink = -1;
        int oddDegreeCount = 0;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == outDegree[i] + 1) {
                sink = i;
                oddDegreeCount++;
            } else if (inDegree[i] == outDegree[i] - 1) {
                source = i;
                oddDegreeCount++;
            } 
        }

        if (oddDegreeCount == 0) {
            source = 0;
            sink = 0;
            System.out.println("Eulerian Circuit");
        } else if (oddDegreeCount == 2) {
            System.out.println("Eulerian Path");
        }

        if (source == -1 || sink == -1) {
            System.out.println("Not Eulerian");
        }

        // find eulerian path
        List<Integer> eulerPath = getEulerianPath(graph, source, sink);

        System.out.println(eulerPath);
    }

    private static List<Integer> getEulerianPath(int[][] graph, int source, int sink) {
        List<Integer> eulerPath = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(source);

        while (!stack.isEmpty()) {
            int node = stack.peek();
            if (graph[node][sink] != 0) {
                stack.push(sink);
            } else {
                eulerPath.add(node);
                stack.pop();
            }
            
            
        }
        return eulerPath;
    }

    // TODO: Implement Hierholzer's algorithm for finding Eulerian Path or Circuit
}
