package org.codekart.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodLeaf {

    int goodLeafCount = 0;

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // leetcode 1530 - Number of Good Leaf Nodes Pairs - using DFS
    public int countPairs(TreeNode root, int distance) {
        dfs(root, distance);
        return goodLeafCount;
    }

    private List<Integer> dfs(TreeNode node, int distance) {
        if (node == null) {
            return new ArrayList<>();
        }
        if (node.left == null && node.right == null) {
            return new ArrayList<>(Arrays.asList(1));
        }
        List<Integer> left = dfs(node.left, distance);
        List<Integer> right = dfs(node.right, distance);

        // iterate over left and right and check if the sum of the two is less than or
        // equal to distance
        for (int i = 0; i < left.size(); i++) {
            for (int j = 0; j < right.size(); j++) {
                if (left.get(i) + right.get(j) <= distance) {
                    goodLeafCount++;
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < left.size(); i++) {
            result.add(left.get(i) + 1);
        }
        for (int i = 0; i < right.size(); i++) {
            result.add(right.get(i) + 1);
        }

        return result;
    }
}
