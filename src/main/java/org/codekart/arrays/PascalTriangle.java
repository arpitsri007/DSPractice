package org.codekart.arrays;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    // leetcode 118
    // Useful for nCr calculation - nCr = n! / (r! * (n-r)!) - go to n row and rth
    // column to get nCr
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(i + 1);
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(row);
        }
        return result;
    }
}
