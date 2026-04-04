package org.codekart.matrix;

public class MinimumOperation {
    // leetcode - 1536. Minimum Swaps to Arrange a Binary Grid
       public int minSwaps(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] trailingZeros = new int[n];

        for (int i = 0; i < n; i++) {
            int cZeros = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (grid[i][j] != 0) {
                    break;
                }
                cZeros++;
            }
            trailingZeros[i] = cZeros;
        }

        int swaps = 0;
        int j = 0;

        for (int i = 0; i < n; i++) {

            int reqZeros = n - i - 1;

            j = i;
            
            while (j < n && trailingZeros[j] < reqZeros) {
                j++;
            }

            if (j == n)
                return -1;

            swaps += j - i;

            while (j > i) {
                int temp = trailingZeros[j];
                trailingZeros[j] = trailingZeros[j - 1];
                trailingZeros[j - 1] = temp;
                j--;
            }
        }

        return swaps;
    }

    public static void main(String[] args) {
        MinimumOperation minimumOperation = new MinimumOperation();
        int[][] grid = {{0,0},{0,1}};
        System.out.println(minimumOperation.minSwaps(grid));
    }
}
