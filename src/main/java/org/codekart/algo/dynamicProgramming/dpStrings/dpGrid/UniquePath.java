package org.codekart.algo.dynamicProgramming.dpStrings.dpGrid;

public class UniquePath {
    
    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(uniquePaths(grid));
    }

    public static int uniquePaths(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        return UniquePathHelper(0, 0, grid.length, grid[0].length, dp);
    }

    public static int UniquePathHelper(int i, int j, int m, int n, int[][] dp) {
        if(i == m-1 && j == n-1) {
            return 1;
        }

        if(dp[i][j] != 0) {
            return dp[i][j];
        }

        if(i >= m || j >= n || i < 0 || j < 0) {
            return 0;
        }
        dp[i][j] = UniquePathHelper(i+1, j, m, n, dp) + UniquePathHelper(i, j+1, m, n, dp);
        return dp[i][j];
    }

    public static int UniquePathTabulation(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }

    public int uniquePathsWithObstacles(int[][] grid) {
        int[][] dp = new int[grid.length+1][grid[0].length+1];
        return UniquePathHelper2(0, 0, grid.length, grid[0].length, grid, dp);
    }


    public static int UniquePathHelper2(int i, int j, int m, int n, int[][] grid, int[][] dp) {
        if(i == m-1 && j == n-1) {
            return 1;
        }

        if(dp[i][j] != 0) {
            return dp[i][j];
        }

        if(i >= m || j >= n || i < 0 || j < 0 || grid[i][j] == 1) {
            return 0;
        }

        int right = UniquePathHelper2(i, j+1, m, n, grid, dp);
        int down = UniquePathHelper2(i+1, j, m, n, grid, dp);
        dp[i][j] = right + down;
        return dp[i][j];
    }

    public static int UniquePath2Tabulation(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];

        // dp[i][i] = # of ways to reach (i,j) from (0,0)
 
        for(int i = 0; i < grid.length; i++) {
           if(grid[i][0] == 1) {    
            break;
           }
           dp[i][0] = 1;
        }

        for(int i = 0; i < grid[0].length; i++) {
            if(grid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }

        for(int i = 1; i < grid.length; i++) {
            for(int j = 1; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }
        
        




    
        
}
