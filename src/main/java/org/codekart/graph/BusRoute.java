package org.codekart.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BusRoute {
    
    public static void main(String[] args) {
        int[][] routes = {{1, 2, 7}, {3, 6, 7}};
        int source = 1;
        int target = 6;
        System.out.println(numBusesToDestination(routes, source, target));
    }

    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0; // If the source and target are the same, no buses are needed.

        // Create a map to store which bus routes pass through each stop
        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.putIfAbsent(stop, new ArrayList<>());
                stopToRoutes.get(stop).add(i); // Add the route index to the stop
            }
        }

        // Use a queue for BFS and a set to track visited stops and routes
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>(); // Single visited set

        queue.offer(source);
        visited.add(source); // Mark the source stop as visited
        int buses = 0;

        // Perform BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            buses++; // Increment the bus count for each level of BFS

            for (int i = 0; i < size; i++) {
                int currentStop = queue.poll();

                // Check all routes that pass through the current stop
                for (int routeIndex : stopToRoutes.getOrDefault(currentStop, new ArrayList<>())) {
                    if (visited.contains(routeIndex)) continue; // Skip if the route has already been visited

                    visited.add(routeIndex); // Mark this route as visited

                    // Check all stops in this route
                    for (int stop : routes[routeIndex]) {
                        if (stop == target) return buses; // If we reach the target, return the bus count
                        if (!visited.contains(stop)) {
                            visited.add(stop); // Mark the stop as visited
                            queue.offer(stop); // Add the stop to the queue for further exploration
                        }
                    }
                }
            }
        }

        return -1; // If we exhaust all options and don't reach the target, return -1
    }


}
