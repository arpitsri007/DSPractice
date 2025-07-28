package org.codekart.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collectleaf {
    // leetcode 366
    /**
     * Given the root of a binary tree, collect a tree's nodes as if you were doing
     * this:
     * 
     * Collect all the leaf nodes.
     * Remove all the leaf nodes.
     * Repeat until the tree is empty.
     * 
     * @param root
     * @return
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        findLeaves(root, result);
        return result;
    }

    private int findLeaves(TreeNode node, List<List<Integer>> result) {
        if (node == null) {
            return 0;
        }
        int leftHeight = findLeaves(node.left, result);
        int rightHeight = findLeaves(node.right, result);

        int height = 1 + Math.max(leftHeight, rightHeight);
        if (result.size() < height) {
            result.add(new ArrayList<>());
        }
        result.get(height - 1).add(node.val);
        return height;
    }

  

   
}
