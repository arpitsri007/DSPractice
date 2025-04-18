package org.codekart.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import util.Pair;

public class NArray {

    public static class Node {
        int val;
        List<Node> children;

        Node(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }
    
    Node root;

    public NArray(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.children = new ArrayList<>();
        root.children.add(new Node(2));
        root.children.add(new Node(3));

        // caller of numOfMinutes
        NArray nArray = new NArray(root);
        System.out.println(nArray.numOfMinutes(3, 0, new int[] { -1, 0, 0 }, new int[] { 1, 2, 3 }));
        System.out.println(nArray.numOfMinutes(1, 0, new int[] { -1 }, new int[] { 0 }));

    }

    // time needed to inform all employees - leetcode 1376
    private int maxTime = 0;
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if(n == 0) return 0;

        // create a map of manager to their children
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            if(manager[i] != -1) {
                map.computeIfAbsent(manager[i], k -> new ArrayList<>()).add(i);
            }
        }

        dfs(map, headID, informTime, 0);
        bfs(map, headID, informTime);
        return maxTime;
    }

    private void dfs(Map<Integer, List<Integer>> map, int node, int[] informTime, int time) {
        maxTime = Math.max(maxTime, time);
        for(int child : map.getOrDefault(node, new ArrayList<>())) {
            dfs(map, child, informTime, time + informTime[node]);
        }

    }

    private void bfs(Map<Integer, List<Integer>> map, int headID, int[] informTime) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(headID, 0));

        while(!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.poll();
            int empId = pair.getFirst();
            int time = pair.getSecond();

            maxTime = Math.max(maxTime, time);

            for(int child : map.getOrDefault(empId, new ArrayList<>())) {
                queue.add(new Pair<>(child, time + informTime[empId]));
            }
        }

        return ;
    }
}
