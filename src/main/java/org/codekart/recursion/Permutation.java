package org.codekart.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Permutation {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 2 };
        System.out.println(subsetsWithDup(nums));
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
    // TC - O(2^n)
    // SC - O(n) Auxiliary space + O(n) for the system stack
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

    // leetcode 90 - Subsets II
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        subsetsWithDupHelper(nums, 0, current, result);
        return result;
    }

    private static void subsetsWithDupHelper(int[] nums, int idx, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));

        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i] == nums[i - 1]) {
                continue;
            }

            current.add(nums[i]);
            subsetsWithDupHelper(nums, i + 1, current, result);

            // backtrack
            current.remove(current.size() - 1);
        }

    }

    // leetcode 47 - Permutations II
    // approach 1 - using map
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        permuteUniqueHelper(current, map, result, nums.length);
        return result;
    }

    private static void permuteUniqueHelper(List<Integer> current, Map<Integer, Integer> map,
            List<List<Integer>> result, int n) {
        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();

            if (freq == 0) {
                continue;
            }

            current.add(num);
            map.put(num, freq - 1);

            permuteUniqueHelper(current, map, result, n);

            current.remove(current.size() - 1);
            map.put(num, freq);
        }
    }

    // TODO: attempt permutation 2 using another swap approach


}
