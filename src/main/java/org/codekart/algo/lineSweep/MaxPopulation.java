package org.codekart.algo.lineSweep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import util.Pair;

public class MaxPopulation {
    /**
     * You are given a 2D integer array logs where each logs[i] = [birthi, deathi]
     * indicates the birth and death years of the ith person.
     * 
     * The population of some year x is the number of people alive during that year.
     * The ith person is counted in year x's population if x is in the inclusive
     * range [birthi, deathi - 1]. Note that the person is not counted in the year
     * that they die.
     * 
     * Return the earliest year with the maximum population.
     */

    public int maximumPopulation(int[][] logs) {
        List<Pair<Integer, Integer>> events = new ArrayList<>();
        for (int[] log : logs) {
            int birth = log[0];
            int death = log[1];
            events.add(new Pair<>(birth, 1)); // Birth event
            events.add(new Pair<>(death, -1)); // Death event
        }
       Collections.sort(events, (a, b) -> {
            if (a.getFirst() == b.getFirst()) {
                return Integer.compare(b.getSecond(), a.getSecond()); // Birth events before death events
            }
            return Integer.compare(a.getFirst(), b.getFirst()); // Sort by year
        });

        int currentPopulation = 0;
        int maxPopulation = 0;
        int earliestYear = Integer.MAX_VALUE;

        for (Pair<Integer, Integer> event : events) {
            currentPopulation += event.getSecond();
            if (currentPopulation > maxPopulation) {
                maxPopulation = currentPopulation;
                earliestYear = event.getFirst();
            }
        }

        return earliestYear;

    }

    public static void main(String[] args) {
        MaxPopulation mp = new MaxPopulation();
        int[][] logs = { {2008,2026},{2004,2008},{2034,2035},{1999,2050},{2049,2050},{2011,2035},{1966,2033},{2044,2049} };
        System.out.println(mp.maximumPopulation(logs)); // Output: 1993
    }
}
