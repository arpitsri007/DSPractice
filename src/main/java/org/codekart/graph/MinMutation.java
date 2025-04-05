package org.codekart.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinMutation {

    public static final char[] MUTATIONS = {'A', 'C', 'G', 'T'};

    public static void main(String[] args) {
        String start = "AACCGGTT";
        String end = "AACCGGTA";
        String[] bank = {"AACCGGTA"};
        System.out.println(minMutation(start, end, bank));
    }

    public static int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> bankSet = new HashSet<>();
        for (String s : bank) bankSet.add(s);

        Queue<String> queue = new LinkedList<>();
        queue.add(startGene);
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(endGene)) return steps;

                for (char c : MUTATIONS) {
                    for (int j = 0; j < current.length(); j++) {
                        String neighbor = current.substring(0, j) + c + current.substring(j + 1);
                        if (bankSet.contains(neighbor)) {
                            queue.add(neighbor);
                            bankSet.remove(neighbor);
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }





}
