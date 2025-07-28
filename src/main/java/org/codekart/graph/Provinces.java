package org.codekart.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Provinces {
    // leetcode 547 - Number of Provinces
    /*
     * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
     * 
     * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
     * 
     * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                dfs(isConnected, visited, i);
                provinces++;
            }
        }
        return provinces;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i) {
        visited[i] = true;
        for(int j=0;j<isConnected.length;j++){
            if(isConnected[i][j] == 1 && !visited[j]){
                dfs(isConnected, visited, j);
            }
        }
    }

    // using BFS
    public int findCircleNumUsingBFS(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                bfs(isConnected, visited, i);
                provinces++;
            }
        }
        return provinces;
    }

    private void bfs(int[][] isConnected, boolean[] visited, int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visited[i] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for(int j=0;j<isConnected.length;j++){
                if(isConnected[node][j] == 1 && !visited[j]){
                    queue.add(j);
                    visited[j] = true;
                }
            }
        }
    }

    // main method
    public static void main(String[] args) {
        Provinces provinces = new Provinces();
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(provinces.findCircleNum(isConnected));
    }
}
