package org.codekart.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MultisourceBFS {

}

// create subclass for solving the walls and gates problem
class WallsAndGates {
    public static void main(String[] args) {
        int[][] rooms = { { 2147483647, -1, 0, 2147483647 },
                { 2147483647, 2147483647, 2147483647, -1 },
                { 2147483647, -1, 2147483647, -1 },
                { 0, -1, 2147483647, 2147483647 } };

        WallsAndGates wallsAndGates = new WallsAndGates();
        wallsAndGates.wallsAndGates(rooms);
        System.out.println(Arrays.deepToString(rooms));
    }

    public void wallsAndGates(int[][] rooms) {
        int rows = rooms.length;
        int cols = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[] { i, j });
                }
            }
        }
        int distance = 0;
        int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                for (int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols
                            && rooms[newX][newY] == Integer.MAX_VALUE) {
                        rooms[newX][newY] = distance;
                        queue.add(new int[] { newX, newY });
                    }
                }
            }
        }
    }
}

class UpdateMatrix {
    public static void main(String[] args) {
        int[][] matrix = { { 0, 1, 0 }, { 0, 1, 0 }, { 0, 1, 0 }, { 0, 1, 0 }, { 0, 1, 0 } };
        UpdateMatrix updateMatrix = new UpdateMatrix();
        int[][] result = updateMatrix.updateMatrix(matrix);
        System.out.println(Arrays.deepToString(result));
    }

    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        if (rows == 0)
            return mat; // Handle empty matrix
        int cols = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // Initialize the queue with all the 0 positions
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[] { i, j });
                } else {
                    mat[i][j] = Integer.MAX_VALUE; // Initialize 1s to a large value
                }
            }
        }

        int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;

            for (int i = 0; i < size; i++) {
                int[] currCell = queue.poll();
                int x = currCell[0];
                int y = currCell[1];

                for (int[] dir : dirs) {
                    int newx = x + dir[0];
                    int newy = y + dir[1];

                    // Check bounds and if the cell is a 1
                    if (newx >= 0 && newx < rows && newy >= 0 && newy < cols && mat[newx][newy] == Integer.MAX_VALUE) {
                        mat[newx][newy] = distance; // Update distance
                        queue.add(new int[] { newx, newy });
                    }
                }
            }
        }
        return mat;
    }
}

// shortest distance from all buildings

class ShortestDistanceFromAllBuildings {
    public static void main(String[] args) {
        int[][] grid = { { 1, 0, 2, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 } };
        ShortestDistanceFromAllBuildings shortestDistanceFromAllBuildings = new ShortestDistanceFromAllBuildings();
        int[] result = shortestDistanceFromAllBuildings.shortestDistance(grid);
        System.out.println(Arrays.toString(result));
    }

    // write a naive approach to solve the problem
    // 1. Identify all empty land cells (0s)
    // For each empty land cell:
    // Calculate the distance to each building
    // Sum up these distances
    // Return the empty land cell with the minimum total distance

    public int[] shortestDistance(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] distance = new int[rows][cols];
        int[][] reach = new int[rows][cols];
        int buildingCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    buildingCount++;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, distance, reach);
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;
        int[] minDistanceCell = new int[] { -1, -1 };
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0 && reach[i][j] == buildingCount) {
                    if (distance[i][j] < minDistance) {
                        minDistance = distance[i][j];
                        minDistanceCell = new int[] { i, j };
                    }
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? new int[] { -1, -1 } : minDistanceCell;
    }

    public void bfs(int[][] grid, int x, int y, int[][] distance, int[][] reach) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { x, y });
        visited[x][y] = true;
        int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int currentDistance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            currentDistance++;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int currentX = current[0];
                int currentY = current[1];

                for (int[] dir : dirs) {
                    int newX = currentX + dir[0];
                    int newY = currentY + dir[1];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        distance[newX][newY] += currentDistance;
                        reach[newX][newY]++;
                        queue.add(new int[] { newX, newY });
                    }
                }
            }
        }
    }
}

class WaterFlow {

    /*
     * This problem is asking us to determine if water can flow from the top row to
     * the bottom row of a grid, where:
     * 
     * 1s represent cells where water can flow
     * 0s represent blockers that water cannot pass through
     * Water starts at the first row and we need to check if it can reach the last
     * row
     */

    // DFS approach

    public boolean canWaterFlow(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return false;

        int rows = grid.length;
        int cols = grid[0].length;

        // Try starting from each cell in the first row
        for (int j = 0; j < cols; j++) {
            if (grid[0][j] == 1) { // TC: O(cols)
                // Create a new visited array for each starting point
                boolean[][] visited = new boolean[rows][cols];
                if (dfs(grid, 0, j, visited, rows, cols))
                    return true;
            }
        }

        return false;
    }

    // TC: For each starting point, we might explore up to all cells in the grid in
    // the worst case
    // SC: O(rows * cols) for the visited array

    private boolean dfs(int[][] grid, int i, int j, boolean[][] visited,
            int rows, int cols) {
        // Check bounds, blockers, and already visited cells
        if (i < 0 || i >= rows || j < 0 || j >= cols ||
                grid[i][j] == 0 || visited[i][j])
            return false;

        // If we reached the last row, water can flow through
        if (i == rows - 1)
            return true;

        visited[i][j] = true;

        // Try all four directions: up, down, left, right
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int[] dir : directions) {
            int newI = i + dir[0];
            int newJ = j + dir[1];

            if (dfs(grid, newI, newJ, visited, rows, cols))
                return true;
        }

        return false;
    }

    /*
     * Overall complexity:
     * TC: O(rows * cols * col)
     * SC: O(rows * cols) - visited array + recursion stack - maximum recursion
     * depth remains the same (potentially up to rows × cols in the worst case)
     */

    // BFS approach
    public boolean canWaterFlowBFS(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return false;

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] reachable = new boolean[rows][cols];

        // Initialize the queue with all cells in the first row
        for (int j = 0; j < cols; j++) {
            if (grid[0][j] == 1) {
                queue.add(new int[] { 0, j });
                reachable[0][j] = true;
            }
        }

        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 1 && !reachable[newX][newY]) {
                    queue.add(new int[] { newX, newY });
                    reachable[newX][newY] = true;
                }
            }
        }

        // Check if the last row is reachable
        for (int j = 0; j < cols; j++) {
            if (reachable[rows - 1][j])
                return true;
        }

        return false;
    }

    /*
     * Overall complexity:
     * TC: O(rows * cols)
     * SC: O(rows * cols) - queue
     */
}