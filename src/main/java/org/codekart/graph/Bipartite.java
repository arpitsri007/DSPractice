package org.codekart.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class BipartiteGraph {          
    public static void main(String[] args) {
        int[][] graph = {{1,2,3},{0,2},{0,1,3},{0,2}};
        System.out.println(isBipartite(graph));
    }

    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);
        for(int i=0;i<n;i++) {
            if(color[i] == -1) {
                if(!dfs(graph, i, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean dfs(int[][] graph, int node, int[] color) {
        color[node] = 0;
        for(int neighbor : graph[node]) {
            if(color[neighbor] == -1) {
                color[neighbor] = 1 - color[node];
                if(!dfs(graph, neighbor, color)) {
                    return false;
                }
            }
            else if(color[neighbor] == color[node]) {
                return false;
            }
        }
        return true;
    }

    // Bipartite Graph using BFS
    public static boolean isBipartiteBFS(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);
        for(int i=0;i<n;i++) {
            if(color[i] == -1) {
                if(!bfs(graph, i, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean bfs(int[][] graph, int node, int[] color) {
        color[node] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(int neighbor : graph[current]) {
                if(color[neighbor] == -1) {
                    color[neighbor] = 1 - color[current];
                    queue.add(neighbor);
                } 
                else if(color[neighbor] == color[current]) {
                    return false;
                }
            }
        }
        return true;
    }
   
}
 