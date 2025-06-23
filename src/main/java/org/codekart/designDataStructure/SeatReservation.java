package org.codekart.designDataStructure;

import java.util.PriorityQueue;

public class SeatReservation {
    // leetcode 1845

    /**
     * 
     * Approach:
     * 1. Use a priority queue to store the available seats.
     * 2. When a seat is reserved, remove it from the priority queue.
     */
    public static class SeatManager {

        private PriorityQueue<Integer> pq;

        public SeatManager(int n) {
            pq = new PriorityQueue<>();
            for (int i = 1; i <= n; i++) {
                pq.add(i);
            }
        }

        public int reserve() {
            return pq.poll();
        }

        public void unreserve(int seatNumber) {
            pq.add(seatNumber);
        }
    }
}
