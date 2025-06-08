package org.codekart.arrays;

public class FrequencyBased {
    //leetcode 1512 - Number of Good Pairs
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] == nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    //leetcode 2363 - Merge Similar Items
    public int numIdenticalPairsOptimized(int[] nums) {
        int count = 0;
        int[] frequency = new int[1001];
        for(int num : nums) {
            frequency[num]++;
        }
        for(int i = 0; i < frequency.length; i++) {
            if(frequency[i] > 1) {
                count += (frequency[i] * (frequency[i] - 1)) / 2;
            }
        }
        return count;
    }

    /**
     *  whether it is possible to pair the shoes you found in such a way that each pair consists of a right and a left shoe of an equal size.
     *  shoes = [[0, 21],
     *  [1, 23],
     *  [1, 21],
     *  [0, 23]]

        the output should be pairOfShoes(shoes) = true;
     */

    public boolean canPair(int[][] shoes) {
        int[] frequency = new int[1001];
        for(int[] shoe : shoes) {
            frequency[shoe[1]] += shoe[0] == 0 ? 1 : -1;
        }
        for(int i = 0; i < frequency.length; i++) {
            if(frequency[i] != 0) {
                return false;
            }
        }   
        return true;
    }

    //TODO: leetcode 2363 - Merge Similar Items

   
}
