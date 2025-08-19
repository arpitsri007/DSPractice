package org.codekart.bitOperation;

import java.util.HashSet;
import java.util.Set;

public class SubArray {
    // leetcode 2411
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int[] setBitIndex = new int[32];

        // setBitIndex[j] = i means that jth bit can be set via element at index i
        for (int i = 0; i < 32; i++) {
            setBitIndex[i] = -1;
        }

        for (int i = n - 1; i >= 0; i--) {
            int endIndex = i;
            for (int j = 0; j < 32; j++) {
                if ((nums[i] & (1 << j)) == 0) {
                    // if the jth bit is not set, we need to find if this bit can be set by taking
                    // element till index i;
                    if (setBitIndex[j] != -1) {
                        endIndex = Math.max(endIndex, setBitIndex[j]);
                    }
                } else {
                    setBitIndex[j] = i;
                }
            }
            result[i] = endIndex - i + 1;
        }
        return result;
    }

    // leetcode 898
    public int subarrayBitwiseORs(int[] arr) {
        int n = arr.length;
        Set<Integer> result = new HashSet<>();
        Set<Integer> prev = new HashSet<>();

        for (int i = 0; i < n; i++) {
            Set<Integer> curr = new HashSet<>();
            for (int num : prev) {
                curr.add(num | arr[i]);
            }
            curr.add(arr[i]);
            prev = curr;
            result.addAll(curr);
        }
        return result.size();
    }

    // leetcode 1611
    // https://www.youtube.com/watch?v=yU6DZSLW4_c&ab_channel=codestorywithMIK
    // hint: how many operations are needed if only ith bit is set. i.e only 1 bit
    // is set
    // let's sat 000 --> 100 takes x steps then 100-> 000 will take x steps.
    // 1000 ----(x steps)--> 1100 ----1----> 0100 ----(x steps)--> 0000
    // Formula is : F(i) = 2*F(i-1) + 1
    // however this formula is only valid if only 1 bit is set

    /**
     * input : 1100
     * target: 0000
     * 
     * 1000 - F(3) -> 0000
     * 
     * 1000 --x---> 1100 ----y---> 0000
     * 
     * x + y = F(3)
     * 
     * y = F(3) - x
     * 
     * now we need to find x
     * 
     * x is essentially from 1000 to 1100 or 000 to 100 hence
     * 
     * x = F(2)
     *
     * 
     * so now y = F(3) - F(2)
     * 
     * Example2:
     * 
     * F(110010) = F(100000) - F(10010)
     * 
     * = F(100000) - {F(10000) - F(00010)}
     * 
     * = F(100000) - F(10000) + F(00010)
     * 
     * = F(5) - F(4) + F(2)
     */
    public int minimumOneBitOperations(int n) {
        int[] dp = new int[32];
        dp[0] = 1;

        for (int i = 1; i < 32; i++) {
            dp[i] = 2 * dp[i - 1] + 1;
        }

        int sign = 1;
        int result = 0;

        for (int i = 31; i >= 0; i--) {
            if ((n & (1 << i)) != 0) {
                result += sign * dp[i];
                sign = -sign;
            }
        }
        return result;
    }
}
