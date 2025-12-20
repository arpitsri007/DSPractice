package org.codekart.matrix;

public class VDiagonal {
    // leetcode 3459 - Length of the Longest V-shaped Diagonal Segment
    public static void main(String[] args) {
        int[][] grid = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        VDiagonal vDiagonal = new VDiagonal();
        System.out.println(vDiagonal.lenOfVDiagonal(grid));
    }

    int[][] directions = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

    public int lenOfVDiagonal(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        int[][][][] dp = new int[m][n][directions.length][2];

        // initialize dp array
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < directions.length; d++) {
                    for (int canTurn = 0; canTurn < 2; canTurn++) {
                        dp[i][j][d][canTurn] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int d = 0; d < directions.length; d++) {
                        int canTurn = 1;
                        int nextTargetValue = 2;
                        result = Math.max(result, 1 + lenOfVDiagonalHelper(grid, i, j, d, canTurn, nextTargetValue, dp));
                    }
                }
            }
        }

        return result;
    }

    public int lenOfVDiagonalHelper(int[][] grid, int i, int j, int d, int canTurn, int nextTargetValue, int[][][][] dp ) {

        int i_next = i + directions[d][0];
        int j_next = j + directions[d][1];

        // out of bounds
        if (i_next < 0 || i_next >= grid.length || j_next < 0 || j_next >= grid[0].length
                || grid[i_next][j_next] != nextTargetValue) {
            return 0;
        }

        if (dp[i_next][j_next][d][canTurn] != -1) {
            return dp[i_next][j_next][d][canTurn];
        }

        // target value found
        int result = 0;
        int keepGoing = 1 + lenOfVDiagonalHelper(grid, i_next, j_next, d, canTurn, nextTargetValue == 2 ? 0 : 2, dp);
        result = Math.max(result, keepGoing);
        if (canTurn == 1) {
            int turnAndGo = Math.max(keepGoing,
                    1 + lenOfVDiagonalHelper(grid, i_next, j_next, (d + 1) % 4, 0, nextTargetValue == 2 ? 0 : 2, dp));
            result = Math.max(result, turnAndGo);
        }
        return dp[i_next][j_next][d][canTurn] = result;

    }
}
