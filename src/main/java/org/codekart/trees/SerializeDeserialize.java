package org.codekart.trees;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserialize {
    // leetcode 297
    // using level order traversal

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("#,");
            } else {
                sb.append(node.val + ",");
            }

            if (node != null) {
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }

        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        for (int i = 1; i < values.length; i++) {
            if (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (i < values.length && !values[i].equals("#")) {
                    node.left = new TreeNode(Integer.parseInt(values[i]));
                    queue.add(node.left);
                }
                i++;
                if (i < values.length && !values[i].equals("#")) {
                    node.right = new TreeNode(Integer.parseInt(values[i]));
                    queue.add(node.right);
                    
                }
            }
        }
        return root;
    }

    // main method
    public static void main(String[] args) {
        SerializeDeserialize serialize = new SerializeDeserialize();
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        System.out.println(serialize.serialize(root));
        System.out.println(serialize.deserialize(serialize.serialize(root)).val);
    }
}
