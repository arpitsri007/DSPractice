package org.codekart.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Dfs {
    
}

// Pacific Atlantic Water Flow
// https://leetcode.com/problems/pacific-atlantic-water-flow/

class Solution {
    // main function
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(solution.pacificAtlantic(heights));
    }


    // directions array to move in all 4 directions
    private final int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if(heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacificOcean = new boolean[rows][cols];
        boolean[][] atlanticOcean = new boolean[rows][cols];

        for(int i=0;i<rows;i++) {
            dfs(heights, i, 0, pacificOcean, rows, cols);
            dfs(heights, i, cols-1, atlanticOcean, rows, cols);
        }

        for(int i=0;i<cols;i++) {
            dfs(heights, 0, i, pacificOcean, rows, cols);
            dfs(heights, rows-1, i, atlanticOcean, rows, cols);
        }

        // print the pacificOcean and atlanticOcean
        System.out.println("Pacific Ocean: ");
        for(int i=0;i<rows;i++) {
            System.out.println(Arrays.toString(pacificOcean[i]));
        }
        System.out.println("Atlantic Ocean: ");
        for(int i=0;i<rows;i++) {
            System.out.println(Arrays.toString(atlanticOcean[i]));
        }
        

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacificOcean[i][j] && atlanticOcean[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, int row, int col, boolean[][] visited, int rows, int cols) {
        visited[row][col] = true;

        for(int[] dir : dirs) {
            int nrow = row + dir[0];
            int ncol = col + dir[1];

            if(nrow >=0 && nrow < rows && ncol >=0 && ncol < cols 
                && !visited[nrow][ncol] && heights[nrow][ncol] >= heights[row][col]) {
                    dfs(heights, nrow, ncol, visited, rows, cols);
                }
        }

    }


    private static class NumberOfIslands {

        public static void main(String[] args) {
            char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
            System.out.println(numIslands(grid));
        }

        private static final int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};

        private static int numIslands(char[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            boolean[][] visited = new boolean[rows][cols];
            int islands = 0;
            for(int i=0;i<rows;i++) {
                for(int j=0;j<cols;j++) {
                    if(grid[i][j] == '1' && !visited[i][j]) {
                        dfs(grid, i, j, visited, rows, cols);
                        islands++;
                    }
                }
            }
            return islands;
        }

        private static void dfs(char[][] grid, int row, int col, boolean[][] visited, int rows, int cols) {
            visited[row][col] = true;

            for(int[] dir : dirs) {
                int nrow = row + dir[0];
                int ncol = col + dir[1];

                if(nrow >=0 && nrow < rows && ncol >=0 && ncol < cols 
                    && !visited[nrow][ncol] && grid[nrow][ncol] == '1') {
                        dfs(grid, nrow, ncol, visited, rows, cols);
                    }
            }
        }
    }

    // review this again....
    private static class UnreachablePairsOfNodesInAnUndirectedGraph {

        private static int componentSize = 0;

        public static void main(String[] args) {
            int n = 7;
            int[][] edges = {{0,2},{0,5},{5,4}, {2,4}, {1,6}};
            System.out.println(countPairs(n, edges));
        }

        private static int countPairs(int n, int[][] edges) {
            List<List<Integer>> graph = new ArrayList<>();
            for(int i=0;i<n;i++) {
                graph.add(new ArrayList<>());
            }

            for(int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }

            boolean[] visited = new boolean[n];
            
            int totalUnreachablePairs = 0;
            for(int i=0;i<n;i++) {
                if(!visited[i]) {
                    componentSize = 0; // Reset componentSize before each DFS
                    dfs(graph, i, visited);
                    totalUnreachablePairs += componentSize * (n - componentSize);
                    n = n - componentSize;
                }
            }
            return totalUnreachablePairs;
        }

        private static void dfs(List<List<Integer>> graph, int node, boolean[] visited) {
            visited[node] = true;
            componentSize++;

            for(int neighbor : graph.get(node)) {
                if(!visited[neighbor]) {
                    dfs(graph, neighbor, visited);
                }
            }
        }   
        
    }

   
}
