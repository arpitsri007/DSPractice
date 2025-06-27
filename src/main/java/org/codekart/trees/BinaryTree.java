package org.codekart.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
    public static class TreeNode {
        int val;    
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // leetcode 637
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ansList = new ArrayList<>();
        if (root == null)
            return ansList;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            double levelSum = 0;

            for (int i = 0; i < size; i++) {

                TreeNode node = q.poll();
                levelSum += node.val;
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);

            }
            ansList.add((double) levelSum / size);
        }
        return ansList;
    }

    // leetcode 1325 - Delete Leaves With a Given Value
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }
        return root;
    }

}
