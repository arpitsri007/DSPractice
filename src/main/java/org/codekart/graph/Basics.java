package org.codekart.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import util.Pair;

import java.util.LinkedList;
import java.lang.*;;

public class Basics {

    public static void main(String[] args) {
        List<List<Integer>> graph = createGraph(5);
        // addEdgeInUndirectedGraph(graph, 0, 1);
        // addEdgeInUndirectedGraph(graph, 0, 2);
        // addEdgeInUndirectedGraph(graph, 1, 3);
        // addEdgeInUndirectedGraph(graph, 2, 3);
        // addEdgeInUndirectedGraph(graph, 3, 4);

        addEdgeInDirectedGraph(graph, 0, 1);
        addEdgeInDirectedGraph(graph, 1, 2);
        addEdgeInDirectedGraph(graph, 2, 3);
        addEdgeInDirectedGraph(graph, 3, 4);
        addEdgeInDirectedGraph(graph, 4, 1);
      

        // boolean[] visited = new boolean[5];
        // List<Integer> result = new ArrayList<>();
        // dfs(graph, 0, visited, result);
        // System.out.println(result);

        // result.clear();
        // visited = new boolean[5];
        // bfs(graph, 0, visited, result);
        // System.out.println(result);

        // result.clear();
        // visited = new boolean[5];
        // dfsUsingStack(graph, 0, visited, result);
        // System.out.println(result);

        // result.clear();
        // visited = new boolean[5];
        // boolean isCycle = isCycleInUndirectedGraphUsingDFS(graph, 0, visited, -1);
        // System.out.println(isCycle);

    }

    public static List<List<Integer>> createGraph(int V) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    public static void addEdgeInUndirectedGraph(List<List<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    public static void addEdgeInDirectedGraph(List<List<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
    }

    public static void printGraph(List<List<Integer>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.println(graph.get(i));
        }
    }

    public static void dfs(List<List<Integer>> graph, int u, boolean[] visited, List<Integer> result) {
        // recursive dfs
        if (visited[u]) {
            return;
        }
        visited[u] = true;
        result.add(u);
        for (int v : graph.get(u)) {
            if (!visited[v]) {
                dfs(graph, v, visited, result);
            }
        }
    }

    public static void bfs(List<List<Integer>> graph, int u, boolean[] visited, List<Integer> result) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        visited[u] = true; 
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int v : graph.get(node)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
    }

    public static void dfsUsingStack(List<List<Integer>> graph, int u, boolean[] visited, List<Integer> result) {
        Stack<Integer> stack = new Stack<>();
        stack.push(u);
        visited[u] = true;

        while (!stack.isEmpty()) {
            int node = stack.pop(); 
            result.add(node);
            for (int v : graph.get(node)) {
                if (!visited[v]) {
                    visited[v] = true;
                    stack.push(v);
                }
            }
        }
    }

    public static boolean isCycleInUndirectedGraphUsingDFS(List<List<Integer>> graph, int u, boolean[] visited,
            int parent) {

        visited[u] = true;

        for (int v : graph.get(u)) {
            if (v == parent) {
                continue;
            }
            if (visited[v]) {
                return true;
            }
            if (isCycleInUndirectedGraphUsingDFS(graph, v, visited, u)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCycleInUndirectedGraphUsingBFS(List<List<Integer>> graph, int u, boolean[] visited) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(u, -1));
        visited[u] = true;
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.poll();
            int node = pair.getFirst();
            int parent = pair.getSecond();
            for (int v : graph.get(node)) {
                if (v == parent) {
                    continue;
                }
                if (visited[v]) {
                    return true;
                }
                visited[v] = true;
                queue.add(new Pair<>(v, node));
            }
        }
        return false;
    }

    // revisit this again
    public static boolean isCycleInDirectedGraphUsingDFS(List<List<Integer>> graph, int u, boolean[] visited, boolean[] recStack) {
        visited[u] = true;
        recStack[u] = true;
        for(int v : graph.get(u)) {
            if(!visited[v]) {
                if(isCycleInDirectedGraphUsingDFS(graph, v, visited, recStack)) {
                    return true;
                }
            } else if(recStack[v]) {
                return true;
            }
        }
        recStack[u] = false;
        return false;
    }
    
}

// 1. Topological Sorting
// 2. Shortest Path in a DAG
// 3. Articulation Points
// 4. Bridges in a Graph
// 5. Strongly Connected Components

// 1. Topological Sorting