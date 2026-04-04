package org.codekart.matrix;

import java.util.HashSet;
import java.util.Set;

public class EqualSumGrid {
    // leetcode 3546: Equal Sum Grid Partition 1

    public boolean canPartitionGrid(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        long[] rowSum = new long[n];
        long[] colSum = new long[m];
        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rowSum[i] += grid[i][j];
                colSum[j] += grid[i][j];
                totalSum += grid[i][j];
            }
        }

        int upperSum = 0;
        for(int i = 0; i < n; i++) {
            upperSum += rowSum[i];
            if(upperSum == totalSum - upperSum) {
                return true;
            }
        }



        int leftSum = 0;
        for(int j = 0; j < m; j++) {
            leftSum += colSum[j];
            if(leftSum == totalSum - leftSum) {
                return true;
            }
        }
     
        return false;
    }

    public static void main(String[] args) {
        EqualSumGrid equalSumGrid = new EqualSumGrid();
        int[][] grid = {{1,3}, {2,4}};
        System.out.println(equalSumGrid.canPartitionGrid(grid));
    }

    // leetcode 3548 - Equal Sum Grid Partition 2
    

    
}
