package org.codekart.graph;

public class Island {
    // leetcode 463
    int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    public int islandPerimeter(int[][] grid) {
 
        int m = grid.length;
        int n = grid[0].length;
        int[] count = new int[1];
        count[0] = 0;


        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, i, j, m, n, visited, count);
                }
            }
        }

        return count[0];
    }

    private void dfs(int[][] grid, int i , int j, int m, int n, boolean[][] visited, int []count) {
        visited[i][j] = true;

        for(int[] dir: dirs) {
            int newx = i + dir[0];
            int newy = j + dir[1];

            if(newx < 0 || newy < 0 || newx >= m || newy >=n || grid[newx][newy] == 0) {
                count[0]++;
                return;
            }  
            
            if(!visited[newx][newy] && grid[newx][newy] == 1) {
                dfs(grid, newx, newy, m,n, visited, count);
            }
            
        }
    }
}
