package org.codekart.designDataStructure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class FlattenNestedList {
    /**
     * Given a nested list of integers, implement an iterator to flatten it.
     * Each element is either an integer, or a list -- whose elements may also be
     * integers or other lists.
     * 
     * Example:
     * Input: [[1,1],2,[1,1]]
     * Output: [1,1,2,1,1]
     */

    /**
     * 
     * Approach:
     * 1. Generally either use recursion or stack to solve this problem.
     * Example: List<NestedInteger> list;
     * { {1,1}, 2, {1,1} }
     * { 1, 1, 2, 1, 1 }
     * 
     * Approach 1: Recursion
     * - Base case: if the current element is an integer, add it to the result list
     * and return.
     * - Recursive case: if the current element is a list, recursively flatten the
     * list and add the result to the current list.
     * - Return the result list.
     * 
     * Approach 2: Stack
     * - Push the elements of the list onto the stack.
     * - Pop the elements of the stack and add them to the result list.
     * - If the popped element is a list, push the elements of the list onto the
     * stack.
     * - Repeat the process until the stack is empty.
     * - Return the result list.
     */

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a
        // nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a
        // single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested
        // list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {

        Stack<NestedInteger> stack = new Stack<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                return null;
            }
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                NestedInteger top = stack.peek();
                if (top.isInteger()) {
                    return true;
                }
                List<NestedInteger> nestedIntegerList = stack.pop().getList();
                for (int i = nestedIntegerList.size() - 1; i >= 0; i--) {
                    stack.push(nestedIntegerList.get(i));
                }
            }
            return false;
        }
    }

    // using queue
    public class NestedIterator2 implements Iterator<Integer> {

        Queue<Integer> queue;

        public NestedIterator2(List<NestedInteger> nestedList) {
            queue = new LinkedList<>();
            flatten(nestedList);
        }

        private void flatten(List<NestedInteger> nestedList) {
            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()) {
                    queue.add(nestedInteger.getInteger());
                } else {
                    flatten(nestedInteger.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return queue.poll();
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }
    // leetcode 364 - nested list weight sum II
    /**
     * You are given a nested list of integers nestedList. Each element is either an
     * integer or a list whose elements may also be integers or other lists.
     * 
     * The depth of an integer is the number of lists that it is inside of. For
     * example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to
     * its depth. Let maxDepth be the maximum depth of any integer.
     * 
     * The weight of an integer is maxDepth - (the depth of the integer) + 1.
     * 
     * Return the sum of each integer in nestedList multiplied by its weight.
     * 
     * Example:
     * Input: nestedList = [[1,1],2,[1,1]]
     * Output: 8
     * Explanation: Four 1's with a weight of 1, one 2 with a weight of 2.
     * 1*1 + 1*1 + 2*2 + 1*1 + 1*1 = 8
     */

    public interface NestedInteger2 {
        // Constructor initializes an empty nested list.
        //public NestedInteger2();

        // Constructor initializes a single integer.
        //public NestedInteger2(int value);

        // @return true if this NestedInteger holds a single integer, rather than a
        // nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a
        // single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger2 ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested
        // list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger2> getList();
    }

    public int depthSumInverse(List<NestedInteger2> nestedList) {
        int maxDepth = getMaxDepth(nestedList, 1);
        return depthSumInverse(nestedList, maxDepth, 1);
    }

    private int depthSumInverse(List<NestedInteger2> nestedList, int maxDepth, int depth) {
        int sum = 0;
        for (NestedInteger2 nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                sum += nestedInteger.getInteger() * (maxDepth - depth + 1);
            } else {
                sum += depthSumInverse(nestedInteger.getList(), maxDepth, depth + 1);
            }
        }
        return sum;
    }

    private int getMaxDepth(List<NestedInteger2> nestedList, int depth ) {
        int maxDepth = depth;
        for (NestedInteger2 nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                maxDepth = Math.max(maxDepth, depth);
            } else {
                maxDepth = Math.max(maxDepth, getMaxDepth(nestedInteger.getList(), depth + 1));
            }
        }
        return maxDepth;
    }
}

