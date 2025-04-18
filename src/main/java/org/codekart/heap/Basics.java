package org.codekart.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Basics {
    public static void main(String[] args) {

    }

    public static class SortCharacterByFrequency {
        public static void main(String[] args) {
            String str = "cccaaaa";
            SortCharacterByFrequency sortCharacterByFrequency = new SortCharacterByFrequency();
            String result = sortCharacterByFrequency.sortCharacterByFrequency(str);
            System.out.println(result);

        }

        public String sortCharacterByFrequency(String str) {
            Map<Character, Integer> frequencyMap = new HashMap<>();

            for (char c : str.toCharArray()) {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }

            PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> frequencyMap.get(b) - frequencyMap.get(a));

            for (char c : frequencyMap.keySet()) {
                maxHeap.offer(c);
            }

            List<Character> result = new ArrayList<>();
            while (!maxHeap.isEmpty()) {
                char c = maxHeap.poll();
                result.add(c);
            }

            StringBuilder sb = new StringBuilder();
            for (char c : result) {
                int freq = frequencyMap.get(c);
                for (int i = 0; i < freq; i++) {
                    sb.append(c);
                }
            }

            return sb.toString();

        }
    }

    public static class TrappingRainWater2 {
        public static void main(String[] args) {
            int[][] heightMap = { { 1, 4, 3, 1, 3, 2 }, { 3, 2, 1, 3, 2, 4 }, { 2, 3, 3, 2, 3, 1 } };
            TrappingRainWater2 trappingRainWater2 = new TrappingRainWater2();
            int result = trappingRainWater2.trapRainWater2(heightMap);
            System.out.println(result);
        }

        // Time Complexity: O(n*m*log(n*m))
        // Space Complexity: O(n*m)
        public int trapRainWater2(int[][] heightMap) {
            int rows = heightMap.length;
            int cols = heightMap[0].length;

            // min heap to store the cell with the lowest height - {height, row, col}
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

            // visited array to avoid revisiting the cell
            boolean[][] visited = new boolean[rows][cols];

            // add all the cells on the boundary to the min heap
            for (int i = 0; i < rows; i++) {
                minHeap.offer(new int[] { heightMap[i][0], i, 0 });
                minHeap.offer(new int[] { heightMap[i][cols - 1], i, cols - 1 });
                visited[i][0] = true;
                visited[i][cols - 1] = true;
            }

            // add all the cells on the boundary to the min heap, skipping corners
            for (int j = 1; j < cols - 1; j++) {
                minHeap.offer(new int[] { heightMap[0][j], 0, j });
                minHeap.offer(new int[] { heightMap[rows - 1][j], rows - 1, j });
                visited[0][j] = true;
                visited[rows - 1][j] = true;
            }

            int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
            int waterLevel = 0;

            while (!minHeap.isEmpty()) {
                int[] cell = minHeap.poll();
                int height = cell[0];
                int row = cell[1];
                int col = cell[2];

                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && !visited[newRow][newCol]) {
                        visited[newRow][newCol] = true;
                        waterLevel += Math.max(height - heightMap[newRow][newCol], 0); // water level at the new cell is
                                                                                       // max of adjacent cell height
                                                                                       // and the height of the cell
                                                                                       // diff.

                        minHeap.offer(new int[] { Math.max(height, heightMap[newRow][newCol]), newRow, newCol });
                    }
                }
            }

            return waterLevel;
        }

    }

    // 3066. Minimum Operations to Exceed Threshold Value II

    public static class MinimumOperationsToExceedTarget {
        public static void main(String[] args) {
            int[] nums = { 999999999, 999999999, 999999999 };
            int target = 1000000000;
            MinimumOperationsToExceedTarget minimumOperationsToExceedTarget = new MinimumOperationsToExceedTarget();
            int result = minimumOperationsToExceedTarget.minimumOperationsToExceedTarget(nums, target);
            System.out.println(result);
        }

        public int minimumOperationsToExceedTarget(int[] nums, int target) {
            // min heap to store the numbers in descending order
            PriorityQueue<Long> minHeap = new PriorityQueue<>((a, b) -> Long.compare(a, b));
            for (int num : nums) {
                minHeap.offer((long) num);
            }

            int operations = 0;
            while (!minHeap.isEmpty()) {
                long num1 = minHeap.poll();
                if (num1 >= target) {
                    return operations;
                }

                if (minHeap.isEmpty()) {
                    return -1;
                }

                long num2 = minHeap.poll();

                // Insert (min(x, y) * 2 + max(x, y)) at any position in the array.
                long newNum = Math.min(num1, num2) * 2 + Math.max(num1, num2);
                minHeap.offer(newNum);
                operations++;
            }

            return -1;
        }
    }

    // Choose K Elements With Maximum Sum | Brute Force | Heap

    public static class ChooseKElementsWithMaximumSum {
        public static void main(String[] args) {
            int[] nums1 = { 4, 2, 1, 5, 3 };
            int[] nums2 = { 10, 20, 30, 40, 50 };
            int k = 2;
            ChooseKElementsWithMaximumSum chooseKElementsWithMaximumSum = new ChooseKElementsWithMaximumSum();
            long[] result = chooseKElementsWithMaximumSum.chooseKElementsWithMaximumSumOptimized(nums1, nums2, k);
            System.out.println(Arrays.toString(result));
        }

        // brute force approach
        public long[] chooseKElementsWithMaximumSum(int[] nums1, int[] nums2, int k) {

            long[] result = new long[nums1.length];

            for (int i = 0; i < nums1.length; i++) { // O(n)
                PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);

                for (int j = 0; j < nums1.length; j++) { // O(n)
                    if (nums1[j] < nums1[i]) {
                        minHeap.offer(nums2[j]); // O(log k)
                        if (minHeap.size() > k) {
                            minHeap.poll(); // O(log k)
                        }
                    }
                }

                long sum = 0;
                while (!minHeap.isEmpty()) { // O(k)
                    sum += minHeap.poll(); // O(log k)
                }
                result[i] = sum;
            }
            // Time Complexity: O(n * ((n * log k) + k * log k))
            // Space Complexity: O(n)

            return result;
        }

        // optimized approach
        public long[] chooseKElementsWithMaximumSumOptimized(int[] nums1, int[] nums2, int k) {
            return null;
            // TODO: Implement the optimized approach
        }
        // Time Complexity: O(n log n)
        // Space Complexity: O(n)
    }

}
