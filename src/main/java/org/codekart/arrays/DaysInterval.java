package org.codekart.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class DaysInterval {
    // leetcode 1353 - Maximum Number of Events That Can Be Attended
    public int maxEvents(int[][] events) {
        // sort the events by the end time
        Arrays.sort(events, (a, b) -> a[1] - b[1]);

        // use a set to keep track of the days that are already attended
        Set<Integer> days = new HashSet<>();

        for (int[] event : events) {
            for (int day = event[0]; day <= event[1]; day++) {
                // if the day is not in the set, add it to the set and break the loop
                if (!days.contains(day)) {
                    days.add(day);
                    break;
                }
            }
        }

        return days.size();
    }

    // optimised solution
    public int maxEventsOptimised(int[][] events) {
        // sort the events by the start time
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int n = events.length;
        int i = 0;
        int eventAttended = 0;
        int currentDay = events[0][0];

        while (i < n || !pq.isEmpty()) {

            while (i < n && events[i][0] == currentDay) {
                pq.add(events[i]);
                i++;
            }

            if (!pq.isEmpty()) {
                pq.poll();
                eventAttended++;
            }

            currentDay++;

            // skip the days that are already attended
            while (!pq.isEmpty() && pq.peek()[1] < currentDay) {
                pq.poll();
            }
        }

        return eventAttended;
    }
}
