package org.codekart.matrix;

public class LongestPathInMatrix {

    int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    long MOD = 1000000007;

    // leetcode 2328 - Number of Increasing Paths in a Grid
    public int countPaths(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result = (int) ((result + dfs(matrix, dp, i, j)) % MOD);
            }
        }
        return result;
    }

    private int dfs(int[][] matrix, int[][] dp, int x, int y) {

        int result = 1;

        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (isValid(newX, newY, matrix) && matrix[newX][newY] < matrix[x][y]) {
                result = (int) ((result + dfs(matrix, dp, newX, newY)) % MOD);
            }
        }
        return dp[x][y] = result;
    }

    private boolean isValid(int x, int y, int[][] matrix) {
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }

    // leetcode 329 - Longest Increasing Path in a Matrix
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, dfs2(matrix, dp, i, j));
            }
        }
        return result;
    }

    private int dfs2(int[][] matrix, int[][] dp, int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        int result = 1;
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (isValid(newX, newY, matrix) && matrix[newX][newY] < matrix[x][y]) {
                result = Math.max(result, 1 + dfs2(matrix, dp, newX, newY));
            }
        }
        return dp[x][y] = result;
    }

}
