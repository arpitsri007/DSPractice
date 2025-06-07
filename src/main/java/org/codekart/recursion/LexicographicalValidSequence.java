package org.codekart.recursion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LexicographicalValidSequence {
    public int[] constructDistancedSequence(int n) {
        int[] result = new int[2 * n - 1];
        Arrays.fill(result, -1);
        Set<Integer> usedNumSet = new HashSet<>();
        solve(0, n, result, usedNumSet);
        return result;
    }

    private static boolean solve(int idx, int n, int[] result, Set<Integer> usedNumSet) {
        if (idx == result.length) {
            return true;
        }

        if (result[idx] != -1) {
            return solve(idx + 1, n, result, usedNumSet);
        }

        for (int num = n; num >= 1; num--) {
            if (usedNumSet.contains(num)) {
                continue;
            }

            result[idx] = num;
            usedNumSet.add(num);

            if (num == 1) {
                if (solve(idx + 1, n, result, usedNumSet)) {
                    return true;
                }
            } else {
                int j = idx + num;
                if (j < result.length && result[j] == -1) {
                    result[j] = num;
                    usedNumSet.add(num);
                    if (solve(idx + 1, n, result, usedNumSet)) {
                        return true;
                    }
                    result[j] = -1;
                    usedNumSet.remove(num);
                }
            }

            result[idx] = -1;
            usedNumSet.remove(num);
        }
        return false;
    }
}
