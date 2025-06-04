package org.codekart.algo.greedy;

import java.util.Arrays;

public class MaxUnits {
    //leetcode 1710
    public static void main(String[] args) {
        int[][] boxTypes = {{1, 3}, {2, 2}, {3, 1}};
        int truckSize = 4;
        System.out.println(maximumUnits(boxTypes, truckSize));
    }

    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int units = 0;
        for(int[] boxType : boxTypes) {
            if(truckSize >= boxType[0]) {
                units += boxType[0] * boxType[1];
                truckSize -= boxType[0];
            } else {
                units += truckSize * boxType[1];
                break;
            }
        }
        return units;
    }
}
