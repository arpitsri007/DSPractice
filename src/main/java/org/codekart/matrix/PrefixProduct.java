package org.codekart.matrix;

public class PrefixProduct {
    // leetcode 2906: Product of Array Except Self

    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] res = new int[n][m];

        long[][] suffixProd = new long[n][m];
        long[][] prefixProd = new long[n][m];

        int MOD = 12345;

        long currentProd = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
               prefixProd[i][j] = currentProd % MOD;
               currentProd = (currentProd * grid[i][j]) % MOD;
            }
        }

        currentProd = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
              suffixProd[i][j] = currentProd % MOD;
              currentProd = (currentProd * grid[i][j]) % MOD;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = (int) (prefixProd[i][j] * suffixProd[i][j] % MOD);
            }
        }

        return res;

    }

}
