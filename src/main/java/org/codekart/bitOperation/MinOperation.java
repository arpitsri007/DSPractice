package org.codekart.bitOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class MinOperation {
    // leetcode 2571 - Minimum Operations to Reduce an Integer to 0
    // using recursion

    public int minOperations(int n) {
        return minOperationsHelper(n);
    }

    // Add or substract a power of 2 from n
    private int minOperationsHelper(int n) {
        if (n == 0) {
            return 0;
        }

        int result = 0;

        /**
         * Idea:
         * if there is group of 1s in the binary representation of n, then we can add 1
         * to the group to make it 0 and it will add 1 to the MSB
         * if there is single 1 in the binary representation of n, then we can substract
         * 1 from the n to make it 0 shift to right;
         */

        while (n > 0) {
            if ((n & 1) == 1) {
                result++;

                n = n >> 1;

                if ((n & 1) == 0) {
                    continue;
                }

                while ((n & 1) == 1) {
                    n = n >> 1;
                }

                n = (n | 1);

            } else {
                n = n >> 1;
            }
        }

        return result;
    }

    // leetocde 1404 - Number of Steps to Reduce a Number in Binary Representation
    // to One
    public int numSteps(String s) {
        int steps = 0;
        int n = s.length();

        while (n > 1) {
            if (s.charAt(n - 1) == '0') {
                s = s.substring(0, n - 1);
            } else {
                s = addOne(s);
            }
            steps++;
            n = s.length();
        }

        return steps;
    }

    private String addOne(String s) {
        StringBuilder sb = new StringBuilder(s);
        int carry = 1;
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == '1') {
                sb.setCharAt(i, '0');
            } else {
                sb.setCharAt(i, '1'); // TIP : find first 0 and change it to 1 and all the 1s to the right of
                                      // it to 0
                carry = 0;
                break;
            }
        }
        if (carry == 1) {
            sb.insert(0, '1');
        }
        return sb.toString();
    }

    /* Equaluse Binary String */
    // Leetcode 3666 - Minimum Operations to equalise the Binary String
    /**
     * BFS & Graph Approach:
     * 1. We can treat each binary string as a node in a graph, and there is an edge
     * between two nodes if we can transform one string into the other by performing
     * the allowed operation (flipping k consecutive bits).
     * 2. We can use a breadth-first search (BFS) algorithm to explore
     * 3. Start with initial number of zeros, create a operations array to keep
     * track of the number of operations needed to reach each node, and a queue to
     * perform BFS.
     */

    public int minOperations(String s, int k) {
        int n = s.length();

        int startZeros = 0;

        for (char c : s.toCharArray()) {
            if (c == '0') {
                startZeros++;
            }
        }

        if (startZeros == 0) {
            return 0;
        }

        // 1. Upgrade to TreeSet to unlock O(log N) range queries
        TreeSet<Integer> evenSet = new TreeSet<>();
        TreeSet<Integer> oddSet = new TreeSet<>();

        for (int count = 0; count <= n; count++) {
            if (count % 2 == 0) {
                evenSet.add(count);
            } else {
                oddSet.add(count);
            }
        }

        int[] operations = new int[n + 1];
        Arrays.fill(operations, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startZeros);
        operations[startZeros] = 0;

        if (startZeros % 2 == 0) {
            evenSet.remove(startZeros);
        } else {
            oddSet.remove(startZeros);
        }

        while (!queue.isEmpty()) {
            int currentZeros = queue.poll();
            int currentOperations = operations[currentZeros];

            // Generate next states by flipping k bits
            // Assume we flip f zeros and (k - f) ones, where f can range from [Max(0, k -
            // (n - currentZeros)), Min(k, currentZeros)]

            int minF = Math.max(0, k - (n - currentZeros));
            int maxF = Math.min(k, currentZeros);

            // for (int f = minF; f <= maxF; f++) {
            // int nextZeros = currentZeros - f + (k - f); // next zeros = current zeros -
            // flipped zeros + flipped ones

            // if (operations[nextZeros] == -1) {
            // operations[nextZeros] = currentOperations + 1;

            // if (nextZeros == 0) {
            // return operations[nextZeros];
            // }
            // queue.offer(nextZeros);
            // }
            // }

            int minNextZeros = currentZeros - maxF + (k - maxF);
            int maxNextZeros = currentZeros - minF + (k - minF);

            // 3. Select the correct tree based on the parity of our boundaries
            TreeSet<Integer> targetSet = (minNextZeros % 2 == 0) ? evenSet : oddSet;

            // 4. Get a view of only the unvisited states in our range
            NavigableSet<Integer> unvisitedRange = targetSet.subSet(minNextZeros, true, maxNextZeros, true);
            // Copy to list to avoid ConcurrentModificationException
            List<Integer> statesToProcess = new ArrayList<>(unvisitedRange);

            for (int nextZeros : statesToProcess) {
                operations[nextZeros] = currentOperations + 1;

                if (nextZeros == 0) {
                    return operations[nextZeros];
                }

                queue.offer(nextZeros);
                targetSet.remove(nextZeros); // Remove from set after processing
            }
        }

        return -1;

    }

    // TC: BFS: O( V + E) where V is the number of vertices (possible states of
    // zeros) and E is the number of edges (possible transitions between states). In
    // the worst case, there can be O(n) vertices and O(n^2) edges, leading to a
    // time complexity of O(n^2).
    // V = O(n) because the number of possible states of zeros can range from 0 to
    // n.
    // E = O(n^K) because for each state, we can flip k bits in various
    // combinations, leading to a large number of possible transitions.

    

}
