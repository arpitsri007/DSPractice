package org.codekart.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubSequence {
    // leetcode 491
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();

        createSubsequences(nums, curr, 0, res);

        return res;
    }

    private void createSubsequences(int[] nums, List<Integer> curr, int idx, List<List<Integer>> res) {

        if (curr.size() >= 2) {
            res.add(new ArrayList<>(curr));
        }

        Set<Integer> st = new HashSet<>();

        for (int i = idx; i < nums.length; i++) {
            int elem = nums[i];

            if (!st.contains(elem) && (curr.size() == 0 || elem >= curr.get(curr.size() - 1))) {
                curr.add(elem);
                st.add(elem);
                createSubsequences(nums, curr, i + 1, res);
                curr.remove(Integer.valueOf(elem));

            }
        }

    }

}
