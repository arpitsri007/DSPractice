package org.codekart.algo.lineSweep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Painting {
    /**
     * Leetcode 1943. Describe the Painting
     */
     public List<List<Long>> splitPainting(int[][] segments) {
        // Step 1: Create a list of events (start and end of segments)
        Map<Long, Long> events = new TreeMap<>();
        for (int[] segment : segments) {
            long start = segment[0];
            long end = segment[1];
            long color = segment[2];
            events.put(start, events.getOrDefault(start, 0L) + color); // Start of a segment adds color
            events.put(end, events.getOrDefault(end, 0L) - color); // End of a segment subtracts color
        }

        List<List<Long>> result = new ArrayList<>();
        long currentColorSum = 0;
        long lastPosition = 0;
        for (Map.Entry<Long, Long> entry : events.entrySet()) {
            long position = entry.getKey();
            long colorChange = entry.getValue();

            if (currentColorSum > 0 && position > lastPosition) {
                result.add(Arrays.asList(lastPosition, position, currentColorSum)); // Add the painted segment to the result
            }
            currentColorSum += colorChange; // Update the current color based on the event
            lastPosition = position; // Update the last position
        }
        return result;
    }
}
