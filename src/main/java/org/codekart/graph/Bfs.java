package org.codekart.graph;

import java.util.*;

public class Bfs {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        TreeNode temp = null;

        while(!q.isEmpty()) {
            temp = q.poll();
            if(temp.right != null) q.offer(temp.right);
            if(temp.left != null) q.offer(temp.left);

        }

        return temp.val;
    }


    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        // Step 1: Build the adjacency list (tree)
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            int parent = ppid.get(i);
            int child = pid.get(i);
            tree.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
        }

        // Step 2: Start DFS from the process to be killed
        List<Integer> result = new ArrayList<>();
        dfs(tree, kill, result);
        return result;
    }

    private void dfs(Map<Integer, List<Integer>> tree, int kill, List<Integer> result) {
        // Add the current process to the result list
        result.add(kill);

        // Get all the children of the process and perform DFS on them
        if (tree.containsKey(kill)) {
            for (int child : tree.get(kill)) {
                dfs(tree, child, result);
            }
        }
    }
}
