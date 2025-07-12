package org.codekart.designDataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class NumberContainer {
    // leetcode 2349 -
    // Design a data structure that supports adding new numbers to the container.
    /*
     * Insert or Replace a number at the given index in the system.
     * Return the smallest index for the given number in the system.
     * Implement the NumberContainers class:
     * 
     * NumberContainers() Initializes the number container system.
     * void change(int index, int number) Fills the container at index with the
     * number. If there is already a number at that index, replace it.
     * int find(int number) Returns the smallest index for the given number, or -1
     * if there is no index that is filled by number in the system.
     */

    private Map<Integer, Integer> indexToNumber;
    private Map<Integer, TreeSet<Integer>> numberToIndexes;

    // Approach 1: Using MinHeap
    private Map<Integer, PriorityQueue<Integer>> numberToMinHeap;

    public NumberContainer() {
        indexToNumber = new HashMap<>();
        numberToIndexes = new HashMap<>();
        numberToMinHeap = new HashMap<>();
    }
    
    public void change(int index, int number) {
        if (indexToNumber.containsKey(index)) {
            int oldNumber = indexToNumber.get(index);
            numberToIndexes.get(oldNumber).remove(index); // O(logn)
            if (numberToIndexes.get(oldNumber).isEmpty()) {
                numberToIndexes.remove(oldNumber);
            }
        }
        indexToNumber.put(index, number);
        numberToIndexes.computeIfAbsent(number, k -> new TreeSet<>()).add(index); // O(logn)
    }

    public int find(int number) {
        if (!numberToIndexes.containsKey(number)) {
            return -1;
        }
        return numberToIndexes.get(number).first(); // O(1)
    }

    // Approach 2: Using MinHeap

    public void change2(int index, int number) {
        indexToNumber.put(index, number);
        numberToMinHeap.computeIfAbsent(number, k -> new PriorityQueue<>()).add(index);
    }

    public int find2(int number) {
        if (!numberToMinHeap.containsKey(number)) {
            return -1;
        }
        while (!numberToMinHeap.get(number).isEmpty()) {
            int index = numberToMinHeap.get(number).peek();
            if (indexToNumber.get(index) == number) {
                return index;
            }
            numberToMinHeap.get(number).poll();
        }
        return -1;
    }
}
