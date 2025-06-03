package org.codekart.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
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
                levelSum += node.data;
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);

            }
            ansList.add((double) levelSum / size);
        }
        return ansList;

    }

}
