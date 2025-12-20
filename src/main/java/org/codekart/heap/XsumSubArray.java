package org.codekart.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import util.Pair;

public class XsumSubArray {

    // TC: O(n * k * log k)
    // SC: O(K) or O(m)
    public int[] xSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Map<Integer, Integer> frequency = new HashMap<>();
        int i = 0;
        int j = 0;
        int index = 0;
        while (j < n) {
            frequency.put(nums[j], frequency.getOrDefault(nums[j], 0) + 1);
            if (j - i + 1 == k) {
                result[index] = calculateXSum(frequency, x);

                frequency.put(nums[i], frequency.get(nums[i]) - 1);

                if (frequency.get(nums[i]) == 0) {
                    frequency.remove(nums[i]);
                }
                index++;
                i++;
            }
            j++;

        }
        return result;
    }

    private int calculateXSum(Map<Integer, Integer> frequency, int x) {
        PriorityQueue<Pair<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> {
            if (a.getSecond() == b.getSecond()) {
                return b.getFirst() - a.getFirst();
            }
            return b.getSecond() - a.getSecond();
        });
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) { // TC : O(k log k) - k distinct elements
            maxHeap.offer(new Pair<Integer, Integer>(entry.getKey(), entry.getValue()));
        }
        int sum = 0;
        while (x > 0 && !maxHeap.isEmpty()) { // TC : O(x * log k) - x is the number of elements to be removed
            Pair<Integer, Integer> pair = maxHeap.poll();
            int currElem = pair.getFirst();
            int currElemFreq = pair.getSecond();
            sum += currElem * currElemFreq;
            x--;
        }
        return sum;
    } // TC : O(k log k + x * log k)

    // leetcode 3321. Find X-Sum of All K-Long Subarrays II - using sliding window

    TreeSet<Pair<Integer, Integer>> topX;
    TreeSet<Pair<Integer, Integer>> waitList;
    long currentXSum = 0;

    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;

        // Initialise the two sorted sets and the current x-sum
        Comparator<Pair<Integer, Integer>> comparator = (a, b) -> {
            if (a.getSecond() == b.getSecond()) {
                return b.getFirst() - a.getFirst(); // value descending order
            }
            return b.getSecond() - a.getSecond(); // frequency descending order
        };

        topX = new TreeSet<>(comparator);
        waitList = new TreeSet<>(comparator);
        currentXSum = 0;

        List<Long> result = new ArrayList<>();

        // Frequency Map
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        int i = 0;
        int j = 0;

        while (j < n) {
            if (frequencyMap.containsKey(nums[j])) {
                // remove the element from the set first either it would be in topX or waitList
                int oldFreq = frequencyMap.get(nums[j]);
                Pair<Integer, Integer> oldPair = new Pair<>(nums[j], oldFreq);
                removeFromSet(oldPair);
            }

            // now update the new frequency in frequency map
            frequencyMap.put(nums[j], frequencyMap.getOrDefault(nums[j], 0) + 1);

            // Insert the new element in the set
            Pair<Integer, Integer> newPair = new Pair<>(nums[j], frequencyMap.get(nums[j]));

            // Insert the new element in the set
            insertInSet(newPair);

            // if window is valid, calculate the x-sum and add to result
            if (j - i + 1 == k) {
                // Rebalance sets before calculating sum
                rebalance(x);
                result.add(currentXSum);

                // remove the ith element from the set
                int oldFreq = frequencyMap.get(nums[i]);
                Pair<Integer, Integer> oldPair = new Pair<>(nums[i], oldFreq);
                removeFromSet(oldPair);

                // Update frequency map for nums[i]
                if (oldFreq == 1) {
                    frequencyMap.remove(nums[i]);
                } else {
                    frequencyMap.put(nums[i], oldFreq - 1);

                    // re-insert the element in the set
                    Pair<Integer, Integer> reInsertPair = new Pair<>(nums[i], oldFreq - 1);
                    insertInSet(reInsertPair);
                }
                // increment i
                i++;
            }
            j++;
        }

        return result.stream().mapToLong(Long::longValue).toArray();
    }

    private void insertInSet(Pair<Integer, Integer> pair) {
        // Always insert into waitList first, rebalance will handle moving to topX
        waitList.add(pair);
    }

    private void removeFromSet(Pair<Integer, Integer> pair) {
        // Remove from whichever set contains it
        if (topX.contains(pair)) {
            topX.remove(pair);
            // Update sum when removing from topX
            currentXSum -= (long) pair.getFirst() * pair.getSecond();
        } else {
            waitList.remove(pair);
        }
    }

    private void rebalance(int x) {
        // Move elements from waitList to topX until topX has x elements
        while (topX.size() < x && !waitList.isEmpty()) {
            Pair<Integer, Integer> toMove = waitList.first();
            waitList.remove(toMove);
            topX.add(toMove);
            currentXSum += (long) toMove.getFirst() * toMove.getSecond();
        }

        // If topX has more than x elements, move excess to waitList
        while (topX.size() > x) {
            Pair<Integer, Integer> toMove = topX.last();
            topX.remove(toMove);
            currentXSum -= (long) toMove.getFirst() * toMove.getSecond();
            waitList.add(toMove);
        }

        // Swap elements if waitList has higher priority elements than topX
        while (!topX.isEmpty() && !waitList.isEmpty()) {
            Pair<Integer, Integer> topMin = topX.last(); // Lowest priority in topX
            Pair<Integer, Integer> waitMax = waitList.first(); // Highest priority in waitList

            // Check if we need to swap
            if (shouldBeInTopX(waitMax, topMin)) {
                // Remove from current positions
                topX.remove(topMin);
                waitList.remove(waitMax);

                // Update sum
                currentXSum -= (long) topMin.getFirst() * topMin.getSecond();
                currentXSum += (long) waitMax.getFirst() * waitMax.getSecond();

                // Add to new positions
                topX.add(waitMax);
                waitList.add(topMin);
            } else {
                break; // Sets are properly balanced
            }
        }
    }

    private boolean shouldBeInTopX(Pair<Integer, Integer> waitMax, Pair<Integer, Integer> topMin) {
        // waitMax should be in topX if it has higher priority than topMin
        if (waitMax.getSecond() > topMin.getSecond()) {
            return true; // Higher frequency
        }
        if (waitMax.getSecond().equals(topMin.getSecond()) &&
                waitMax.getFirst() > topMin.getFirst()) {
            return true; // Same frequency, higher value
        }
        return false;
    }

}
