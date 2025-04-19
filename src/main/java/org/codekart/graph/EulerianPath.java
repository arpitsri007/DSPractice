package org.codekart.graph;

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
        System.out.println(findEulerianPathOrCircuit(graph));
    }

    // Check if all vertices with non-zero degree belong to a single connected
    // if all vertices with even degree in a connected graph, then Eulerian circuit
    private static String findEulerianPathOrCircuit(int[][] graph) {
        int n = graph.length;
        int[] degree = calculateDegree(graph);

        if (!isConnected(graph, degree)) {
            return "Not connected";
        }

        int oddDegreeCount = 0;
        for (int i = 0; i < n; i++) {
            if (degree[i] % 2 != 0) {
                oddDegreeCount++;
            }
        }

        if (oddDegreeCount == 0) {
            return "Eulerian Circuit";
        } else if (oddDegreeCount == 2) {
            return "Eulerian Path";
        }
        return "Not Eulerian";

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
}
