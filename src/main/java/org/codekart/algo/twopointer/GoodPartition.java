package org.codekart.algo.twopointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodPartition {
    // Count the Number of Good Partitions - leetcode 2963
    public int numberOfGoodPartitions(int[] nums) {
        int n = nums.length;
        int mod = 1000000007;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(nums[i], Math.max(map.getOrDefault(nums[i], 0), i));
        }

        int result = 1;

        int i = 0;
        int j = 0;

        while (i < n) {
            if (i > j) {
                result = (result * 2) % mod;
                i = j + 1;
                j = i;
            }
            j = Math.max(j, map.get(nums[i]));
            i++;
        }

        return result;
    }

    // leetcode 763 - Partition Labels
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        int[] last = new int[26];
        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        int end = 0;

        while (i < n) {
            end = Math.max(end, last[s.charAt(i) - 'a']);

            while (j < end) {
                end = Math.max(end, last[s.charAt(j) - 'a']);
                j++;
            }

            result.add(end - i + 1);
            i = end + 1;
        }

        return result;
    }
}
