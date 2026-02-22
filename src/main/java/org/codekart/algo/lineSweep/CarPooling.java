package org.codekart.algo.lineSweep;

import java.util.Map;
import java.util.TreeMap;

public class CarPooling {

    // 1094. Car Pooling

    /*
     * You are driving a vehicle that has capacity empty seats initially available
     * for passengers. The vehicle only drives east (i.e., it cannot turn around
     * and drive west.)
     * 
     * You are given an array trips where trips[i] = [numPassengersi,
     * fromi, toi] indicates that the ith trip has numPassengersi passengers and the
     * locations to pick them up and drop them off are fromi and toi respectively.
     * 
     * Return true if it is possible to pick up and drop off all passengers for all
     * the given trips, or false otherwise.
     */

     public boolean carPooling(int[][] trips, int capacity) {
       
        Map<Integer, Integer> passengerChanges = new TreeMap<>();   
        int currentPassengers = 0;
        for (int[] trip : trips) {
            int numPassengers = trip[0];
            int from = trip[1];
            int to = trip[2];
            passengerChanges.put(from, passengerChanges.getOrDefault(from, 0) + numPassengers); // Passengers get in
            passengerChanges.put(to, passengerChanges.getOrDefault(to, 0) - numPassengers);
        }

        for (int change : passengerChanges.values()) {
            currentPassengers += change;
            if (currentPassengers > capacity) {
                return false; // Capacity exceeded
            }
        }
        return true; // All trips can be accommodated
       
    }

    // Approach 2: Using Diff Array

      public boolean carPoolingDiffArray(int[][] trips, int capacity) {
        int[] diff = new int[1001];
        for (int[] trip : trips) {
            int numPassengers = trip[0];
            int from = trip[1];
            int to = trip[2];
            diff[from] += numPassengers; // Passengers get in
            diff[to] -= numPassengers; // Passengers get out
        }

        int currentPassengers = 0;
        for (int change : diff) {
            currentPassengers += change;
            if (currentPassengers > capacity) {
                return false; // Capacity exceeded
            }
        }
        return true; // All trips can be accommodated
    }

}