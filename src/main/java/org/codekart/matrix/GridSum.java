package org.codekart.matrix;

import java.util.TreeSet;

public class GridSum {
    // leetcode 1878: Get Biggest Three Rhombus Sums in a Grid
    public int[] getBiggestThreeRhombusSums(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

       

        TreeSet<Integer> set = new TreeSet<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                addInSet(set, grid[r][c]);

                for (int side = 1; r - side >= 0 && r + side < m && c - side >= 0 && c + side < n; side++) {
                    int sum = 0;
                    for (int k = 0; k <= side - 1; k++) {
                        sum += grid[r - side + k][c + k];
                        sum += grid[r + k][c + side - k];
                        sum += grid[r + side - k][c - k];
                        sum += grid[r - k][c - side + k];
                    }
                    addInSet(set, sum);
                }
            }
        }

        int i = 0;
        int[] res = new int[Math.min(3, set.size())];
        java.util.Iterator<Integer> it = set.descendingIterator();
        while (it.hasNext() && i < 3) {
            res[i++] = it.next();
        }

        return res;

    }

    static void addInSet(TreeSet<Integer> set, int sum) {
        set.add(sum);
        if (set.size() > 3) {
            set.pollFirst();
        }
    }

    // Approach 2: Prefix Sum
    public int[] getBiggestThreeRhombusSumsApproach2(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Right diagonal
        int[][] rightDiag = new int[m][n];
        int[][] leftDiag = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rightDiag[i][j] = grid[i][j];
                if (i - 1 >= 0 && j - 1 >= 0) {
                    rightDiag[i][j] += rightDiag[i - 1][j - 1];
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                leftDiag[i][j] = grid[i][j];
                if (i - 1 >= 0 && j + 1 < n) {
                    leftDiag[i][j] += leftDiag[i - 1][j + 1];
                }
            }
        }

        int[] res = new int[3];

        TreeSet<Integer> set = new TreeSet<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                addInSet(set, grid[r][c]);

                for (int side = 1; r - side >= 0 && r + side < m && c - side >= 0 && c + side < n; side++) {
                    int sum = 0;

                    int top_r = r - side;
                    int top_c = c;

                    int right_r = r;
                    int right_c = c + side;

                    int bottom_r = r + side;
                    int bottom_c = c;

                    int left_r = r;
                    int left_c = c - side;

                    // Top to Right (↘ diagonal)
                    sum += getRightDiagSum(rightDiag, top_r, top_c, right_r, right_c);

                    // Right to Bottom (↙ diagonal)
                    sum += getLeftDiagSum(leftDiag, right_r, right_c, bottom_r, bottom_c);

                    // Bottom to Left (↗ diagonal, but we use rightDiag in reverse)
                    sum += getRightDiagSum(rightDiag, left_r, left_c, bottom_r, bottom_c);

                    // Left to Top (↖ diagonal, but we use leftDiag in reverse)
                    sum += getLeftDiagSum(leftDiag, top_r, top_c, left_r, left_c);

                    // Remove vertices counted twice (each vertex is counted in 2 edges)
                    sum -= grid[top_r][top_c];
                    sum -= grid[right_r][right_c];
                    sum -= grid[bottom_r][bottom_c];
                    sum -= grid[left_r][left_c];

                    addInSet(set, sum);
                }
            }
        }

        int i = 0;
        java.util.Iterator<Integer> it = set.descendingIterator();
        while (it.hasNext() && i < 3) {
            res[i++] = it.next();
        }

        return res;

    }

    private int getRightDiagSum(int[][] rightDiag, int r1, int c1, int r2, int c2) {
        int sum = rightDiag[r2][c2];
        if (r1 > 0 && c1 > 0) {
            sum -= rightDiag[r1 - 1][c1 - 1];
        }
        return sum;
    }

    private int getLeftDiagSum(int[][] leftDiag, int r1, int c1, int r2, int c2) {
        int sum = leftDiag[r2][c2];
        if (r1 > 0 && c1 < leftDiag[0].length - 1) {
            sum -= leftDiag[r1 - 1][c1 + 1];
        }
        return sum;
    }

}
