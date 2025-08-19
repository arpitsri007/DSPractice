package org.codekart.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyBased {
    // leetcode 1512 - Number of Good Pairs
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // leetcode 2363 - Merge Similar Items
    public int numIdenticalPairsOptimized(int[] nums) {
        int count = 0;
        int[] frequency = new int[1001];
        for (int num : nums) {
            frequency[num]++;
        }
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > 1) {
                count += (frequency[i] * (frequency[i] - 1)) / 2;
            }
        }
        return count;
    }

    /**
     * whether it is possible to pair the shoes you found in such a way that each
     * pair consists of a right and a left shoe of an equal size.
     * shoes = [[0, 21],
     * [1, 23],
     * [1, 21],
     * [0, 23]]
     * 
     * the output should be pairOfShoes(shoes) = true;
     */

    public boolean canPair(int[][] shoes) {
        int[] frequency = new int[1001];
        for (int[] shoe : shoes) {
            frequency[shoe[1]] += shoe[0] == 0 ? 1 : -1;
        }
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] != 0) {
                return false;
            }
        }
        return true;
    }

    // leetcode 2561
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> frequency = new HashMap<>();
        int minCost = Integer.MAX_VALUE;
        long result = 0;    
        for (int num : basket1) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
            minCost = Math.min(minCost, num);
        }
        for (int num : basket2) {
            frequency.put(num, frequency.getOrDefault(num, 0) - 1);
            minCost = Math.min(minCost, num);
        }

        List<Integer> swapElements = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                return -1;
            }

            int count = Math.abs(entry.getValue());
            for (int i = 0; i < count / 2; i++) {
                swapElements.add(entry.getKey());
            }
        }

        Collections.sort(swapElements);
        for (int i = 0; i < swapElements.size() / 2; i++) {
            int cost = Math.min(swapElements.get(i), 2 * minCost);
            result += cost;
        }
        return result;
    }

    // TODO: leetcode 2363 - Merge Similar Items

}
