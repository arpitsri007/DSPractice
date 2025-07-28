package org.codekart.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {

    // leetcode 210 - Course Schedule
    /*
     * There are a total of numCourses courses you have to take, labeled from 0 to
     * numCourses - 1. You are given an array prerequisites where prerequisites[i] =
     * [ai, bi] indicates that you must take course bi first if you want to take
     * course ai.
     * 
     * For example, the pair [0, 1], indicates that to take course 0 you have to
     * first take course 1.
     * 
     * Return the ordering of courses you should take to finish all courses. If
     * there are many valid answers, return any of them. If it is impossible to
     * finish all courses, return an empty array.
     */

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        int[] indegree = new int[numCourses];
        for (int i = 0; i < graph.size(); i++) {
            for (int neighbor : graph.get(i)) {
                indegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);
            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (result.size() == numCourses) {
            return result.stream().mapToInt(i -> i).toArray();
        }
        return new int[0];
    }

    // using DFS
    public int[] findOrderUsingDFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && isCycleInDirectedGraphUsingDFS(graph, i, visited, recStack)) {
                return new int[0];
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private boolean isCycleInDirectedGraphUsingDFS(List<List<Integer>> graph, int current, boolean[] visited,
            boolean[] recStack) {
        visited[current] = true;
        recStack[current] = true;
        for (int neighbor : graph.get(current)) {
            if (!visited[neighbor] && isCycleInDirectedGraphUsingDFS(graph, neighbor, visited, recStack)) {
                return true;
            } else if (recStack[neighbor]) {
                return true;
            }
        }
        recStack[current] = false;
        return false;
    }
}
