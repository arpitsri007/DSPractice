package org.codekart.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutation {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        System.out.println(permute(nums));
    }

    // leetcode 46 - Permutations
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        Set<Integer> used = new HashSet<>();
        permuteHelper(nums, current, used, result);
        return result;
    }

    private static void permuteHelper(int[] nums, List<Integer> current, Set<Integer> used,
            List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // TC: O(n!)
        // SC: O(n) - for the set
        for (int i = 0; i < nums.length; i++) {
            if (used.contains(nums[i])) {
                continue;
            }
            // try
            used.add(nums[i]);
            current.add(nums[i]);

            // recursive call
            permuteHelper(nums, current, used, result);

            // backtrack
            used.remove(nums[i]);
            current.remove(current.size() - 1);
        }
    }

    // leetcode 78 - Subsets
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        subsetsHelper(nums, 0, current, result);
        return result;
    }

    private static void subsetsHelper(int[] nums, int idx, List<Integer> current, List<List<Integer>> result) {
        if (idx == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        current.add(nums[idx]);
        subsetsHelper(nums, idx + 1, current, result);

        // backtrack
        current.remove(current.size() - 1);
        subsetsHelper(nums, idx + 1, current, result);

    }

}
