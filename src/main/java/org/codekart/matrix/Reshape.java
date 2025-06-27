package org.codekart.matrix;

public class Reshape {
    // leetcode 566 - Reshape the Matrix
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }
        int[][] result = new int[r][c];
        // for (int i = 0; i < m * n; i++) {
        //     result[i / c][i % c] = mat[i / n][i % n];
        // }

        int resRow = 0;
        int resCol = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[resRow][resCol] = mat[i][j];
                resCol++;
                if (resCol == c) {
                    resRow++;
                    resCol = 0;
                }
            }
        }
        return result;
    }
}
