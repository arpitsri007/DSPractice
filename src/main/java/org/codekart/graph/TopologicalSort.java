package org.codekart.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


// Kahn's Algorithm
public class TopologicalSort {

    // create indegree array
            // Example: u --> v <--- x  :: indegree of v is 2 and indegree of x and u is 0 so correct topological sort is u, x, v / x, u, v
    // create queue
    // add all nodes with indegree 0 to queue
    // while queue is not empty
    // poll from queue
    // add to result
    // for all neighbors of current node
    // decrease indegree of neighbor
    // if indegree of neighbor is 0, add to queue

    // Time Complexity: O(V + E)
    // Space Complexity: O(V)
    // Topological Sort is possible only for DAG (Directed Acyclic Graph)
    // Topological Sort is not possible for graphs with cycles
    // Topological Sort is not possible for graphs with multiple components


    public static List<Integer> topologicalSort(List<List<Integer>> graph) {
        // if cycle is present, return empty list
        if (isCycleInDirectedGraphUsingBFS(graph)) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        int[] indegree = calculateIndegree(graph);
        Queue<Integer> queue = initializeQueue(indegree);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);
            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }

    private static List<Integer> topologicalSortUsingDFS(List<List<Integer>> graph) {
       // implement using stack
       Stack<Integer> stack = new Stack<>();
       boolean[] visited = new boolean[graph.size()];

        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                dfs(graph, i, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static void dfs(List<List<Integer>> graph, int current, boolean[] visited, Stack<Integer> stack) {
        visited[current] = true;
        for (int neighbor : graph.get(current)) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited, stack);
            }
        }
        stack.push(current);
    }

    private static int[] calculateIndegree(List<List<Integer>> graph) {
        int[] indegree = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            for (int neighbor : graph.get(i)) {
                indegree[neighbor]++;
            }
        }
        return indegree;
    }

    private static Queue<Integer> initializeQueue(int[] indegree) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        return queue;
    }

    public static boolean isCycleInDirectedGraphUsingBFS(List<List<Integer>> graph) {
        // create indegree
        int[] indegree = calculateIndegree(graph);
        Queue<Integer> queue = initializeQueue(indegree);

        int nodeCount = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            nodeCount++;

            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return nodeCount != graph.size();
    }

    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1, 2));
        graph.add(Arrays.asList(3));
        graph.add(Arrays.asList(3));

        graph.add(Arrays.asList());

        List<Integer> result = topologicalSort(graph);
        System.out.println(result);

        List<Integer> resultUsingDFS = topologicalSortUsingDFS(graph);
        System.out.println(resultUsingDFS);
        

        System.out.println("Is Cycle in Directed Graph Using BFS: " + isCycleInDirectedGraphUsingBFS(graph));
    }



}