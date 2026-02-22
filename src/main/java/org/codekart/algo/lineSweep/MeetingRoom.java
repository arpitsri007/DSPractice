package org.codekart.algo.lineSweep;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class MeetingRoom {
    // Meeting Rooms II
     public int minMeetingRooms(int[] start, int[] end) {
            int n = start.length;
            int[][] meetings = new int[n][2];
            for (int i = 0; i < n; i++) {
                meetings[i] = new int[]{start[i], end[i]};
            }
            Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
    
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int minRooms = 0;
    
            for (int[] meeting : meetings) {
                while (!pq.isEmpty() && pq.peek() <= meeting[0]) {
                    pq.poll();
                }
                pq.offer(meeting[1]);
                minRooms = Math.max(minRooms, pq.size());
            }
    
            return minRooms;
        }

        // Approach 2: Using line sweep algorithm
        public int minMeetingRoomsLineSweep(int[] start, int[] end) {
            int n = start.length;
            Map<Integer, Integer> events = new TreeMap();
            for (int i = 0; i < n; i++) {
                events.put(start[i], events.getOrDefault(start[i], 0) + 1); // Meeting starts
                events.put(end[i], events.getOrDefault(end[i], 0) - 1); // Meeting ends
            }

            int ongoingMeetings = 0;
            int maxRooms = 0;

            for (int count : events.values()) {
                ongoingMeetings += count;
                maxRooms = Math.max(maxRooms, ongoingMeetings);
            }
            return maxRooms;
        }

}
