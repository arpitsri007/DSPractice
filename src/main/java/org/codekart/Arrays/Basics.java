package org.codekart.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basics {

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printArray(int[] arr, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // reverse an array
    public static void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }       
    }

    public static void rotateArray(int[] arr, int k) {
        k = k % arr.length;
        reverseArray(arr, 0, arr.length - 1);
        reverseArray(arr, 0, k - 1);
        reverseArray(arr, k, arr.length - 1);
    }

    public static void reverseArray(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        reverseArray(arr, start + 1, end - 1);
    }

    // Two sum problem
    public static int[] twoSum(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] { -1, -1 };
    }

    // Two Sum with Map
    public static int[] twoSumWithMap(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(arr[i], i);
        }
        return new int[] { -1, -1 };
    }

    // two sum for sorted array
    public static int[] twoSumForSortedArray(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] == target) {
                return new int[] { left, right };
            } else if (arr[left] + arr[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] { -1, -1 };
    }

    // three sum problem with removing duplicates
    public static List<List<Integer>> threeSum(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == target) {
                    result.add(Arrays.asList(arr[i], arr[left], arr[right]));
                    left++;
                    right--;
                    while (left < right && arr[left] == arr[left - 1]) {
                        left++;
                    }
                    while (left < right && arr[right] == arr[right + 1]) {
                        right--;
                    }
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
    
    

    // leetcode 442
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            int index = Math.abs(nums[i]) - 1;
            if(nums[index] < 0){
                result.add(Math.abs(nums[i]));
            } else {
                nums[index] = -nums[index];
            }
        }
        return result;
    }

    // leetcode 41
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean containsOne = false;
        for(int i = 0; i < n; i++){
            if(nums[i] == 1){
                containsOne = true;
            }

            if(nums[i] <= 0 || nums[i] > n){
                nums[i] = 1;
            }
        }

        if(!containsOne){
            return 1;
        }

        for(int i = 0; i < n; i++){
            int index = Math.abs(nums[i]) - 1;
            if(nums[index] > 0){
                nums[index] = -nums[index];
            }

        }

        for(int i = 0; i < n; i++){
            if(nums[i] > 0){
                return i + 1;
            }
        }

        return n + 1;
    }


}
