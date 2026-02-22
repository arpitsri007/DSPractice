package org.codekart.algo.lineSweep;

public class CorporateBooking {

    // 1109. Corporate Flight Bookings
    /*
     * There are n flights that are labeled from 1 to n.
     * 
     * You are given an array of flight bookings bookings, where bookings[i] =
     * [firsti, lasti, seatsi] represents a booking for flights firsti through
     * lasti (inclusive) with seatsi seats reserved for each flight in the range.
     * 
     * Return an array answer of length n, where answer[i] is the total number of
     * seats reserved for flight i.
     */

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] seats = new int[n];
        for (int[] booking : bookings) {
            int first = booking[0] - 1; // Convert to 0-based index
            int last = booking[1] - 1; // Convert to 0-based index
            int numSeats = booking[2];
            seats[first] += numSeats; // Add seats at the start of the range
            if (last + 1 < n) {
                seats[last + 1] -= numSeats; // Subtract seats after the end of the range
            }
        }

        // Compute prefix sum to get the total seats for each flight
        for (int i = 1; i < n; i++) {
            seats[i] += seats[i - 1];
        }
        return seats;
    }
}
