package org.codekart.recursion;

public class UniquePath {

    static int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    // leetcode 980
    public static void main(String[] args) {
        int[][] grid = { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 2, -1 } };
        System.out.println(uniquePathsIII(grid));
    }

    public static int uniquePathsIII(int[][] grid) {

        int[] nonObstacleCellsCount = new int[1];
        nonObstacleCellsCount[0] = 0;
        int startX = 0, startY = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 || grid[i][j] == 1)
                    nonObstacleCellsCount[0]++;
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                }
            }
        }
        return uniquePathsIIIHelper(grid, startX, startY, nonObstacleCellsCount, 0);
    }

    private static int uniquePathsIIIHelper(int[][] grid, int x, int y, int[] nonObstacleCellsCount, int count) {

        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == -1) {
            return 0;
        }

        if (grid[x][y] == 2) {
            if (nonObstacleCellsCount[0] == count) {
                return 1;
            }
            return 0;
        }

        grid[x][y] = -1;
        int result = 0;

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            result += uniquePathsIIIHelper(grid, newX, newY, nonObstacleCellsCount, count + 1);

        }
        grid[x][y] = 0;
        return result;
    } // T(n) = 4T(n-1) + O(1) = O(4^n)

}
