package org.codekart.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import util.Pair;

public class TreeView {

    public static void main(String[] args) {
        TreeView treeView = new TreeView();
        TreeNode root = treeView.buildTree();
        Map<Integer, Pair<Integer, Integer>> mp = new TreeMap<>();
        treeView.formBottomViewOfBinaryTree(root, 0, 0, mp);
        for (Map.Entry<Integer, Pair<Integer, Integer>> entry : mp.entrySet()) {
            System.out.println(entry.getValue().getFirst());
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*
     * 
     * 1
     * / \
     * 2 3
     * / \ / \
     * 4 5 6 7
     * 
     */

    // 4 2 1 3 7

    /*
     * 
     * 1
     * / \
     * 2 3
     * \
     * 4
     * \
     * 5
     * \
     * 6
     * 
     */
    // 2 1 3 6

    public TreeNode buildTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(5);
        root.left.right.right.right = new TreeNode(6);
        return root;
    }

    public void formTopViewOfBinaryTree(TreeNode node, int horizontalDistance, int level,
            Map<Integer, Pair<Integer, Integer>> mp) {
        if (node == null)
            return;

        if (!mp.containsKey(horizontalDistance) || level < mp.get(horizontalDistance).getSecond()) {
            mp.put(horizontalDistance, new Pair<>(node.val, level));
        }

        formTopViewOfBinaryTree(node.left, horizontalDistance - 1, level + 1, mp);
        formTopViewOfBinaryTree(node.right, horizontalDistance + 1, level + 1, mp);
    }

    public void formBottomViewOfBinaryTree(TreeNode node, int horizontalDistance, int level,
            Map<Integer, Pair<Integer, Integer>> mp) {
        if (node == null)
            return;

        if (!mp.containsKey(horizontalDistance) || level > mp.get(horizontalDistance).getSecond()) {
            mp.put(horizontalDistance, new Pair<>(node.val, level));
        }

        formBottomViewOfBinaryTree(node.left, horizontalDistance - 1, level + 1, mp);
        formBottomViewOfBinaryTree(node.right, horizontalDistance + 1, level + 1, mp);
    }

    public void formLeftViewOfBinaryTree(TreeNode node, int level, List<Integer> leftView) {
        if (node == null)
            return;

        if (leftView.size() == level) {
            leftView.add(node.val);
        }

        formLeftViewOfBinaryTree(node.left, level + 1, leftView);
        formLeftViewOfBinaryTree(node.right, level + 1, leftView);
    }

    public void formRightViewOfBinaryTree(TreeNode node, int level, List<Integer> rightView) {
        if (node == null)
            return;

        if (rightView.size() == level) {
            rightView.add(node.val);
        }

        formRightViewOfBinaryTree(node.right, level + 1, rightView);
        formRightViewOfBinaryTree(node.left, level + 1, rightView);
    }

    // All the above views using BFS
    // TC : O(n log n)
    // SC : O(n)
    public List<Integer> formTopViewOfBinaryTreeUsingBFS(TreeNode node) {
        if (node == null)
            return new ArrayList<>();

        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> topNodes = new TreeMap<>(); // horizontalDistance, node valuel - store the first node at
                                                          // each horizontal distance

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>(); // node, horizontalDistance
        queue.add(new Pair<>(node, 0));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> current = queue.poll();
            TreeNode currentNode = current.getFirst();
            int horizontalDistance = current.getSecond();

            if (!topNodes.containsKey(horizontalDistance)) {
                topNodes.put(horizontalDistance, currentNode.val);
            }

            if (currentNode.left != null) {
                queue.add(new Pair<>(currentNode.left, horizontalDistance - 1));
            }

            if (currentNode.right != null) {
                queue.add(new Pair<>(currentNode.right, horizontalDistance + 1));
            }
        }

        for (Map.Entry<Integer, Integer> entry : topNodes.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }

    // BFS for top view with TC : O(n) and SC : O(n)
    public List<Integer> formTopViewOfBinaryTreeUsingBFSOptimized(TreeNode node) {
        if (node == null)
            return new ArrayList<>();

        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> topNodes = new HashMap<>(); // horizontalDistance, node valuel - store the first node at

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(node, 0));

        int minHorizontalDistance = 0;
        int maxHorizontalDistance = 0;

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> current = queue.poll();
            TreeNode currentNode = current.getFirst();
            int horizontalDistance = current.getSecond();

            minHorizontalDistance = Math.min(minHorizontalDistance, horizontalDistance);
            maxHorizontalDistance = Math.max(maxHorizontalDistance, horizontalDistance);

            if (!topNodes.containsKey(horizontalDistance)) {
                topNodes.put(horizontalDistance, currentNode.val);
            }

            if (currentNode.left != null) {
                queue.add(new Pair<>(currentNode.left, horizontalDistance - 1));
            }

            if (currentNode.right != null) {
                queue.add(new Pair<>(currentNode.right, horizontalDistance + 1));
            }
        }

        for (int i = minHorizontalDistance; i <= maxHorizontalDistance; i++) {
            result.add(topNodes.get(i));
        }

        return result;
    }

    // Left View of Binary Tree using BFS
    public List<Integer> formLeftViewOfBinaryTreeUsingBFS(TreeNode node) {
        if (node == null)
            return new ArrayList<>();

        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (i == 0) {
                    result.add(currentNode.val);
                }

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }
        return result;
    }

    // Right View of Binary Tree using BFS
    public List<Integer> formRightViewOfBinaryTreeUsingBFS(TreeNode node) {
        if (node == null)
            return new ArrayList<>();

        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (i == levelSize - 1) {
                    result.add(currentNode.val);
                }

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }

        return result;
    }

    // Bottom View of Binary Tree using BFS
    public List<Integer> formBottomViewOfBinaryTreeUsingBFS(TreeNode node) {
        if (node == null)
            return new ArrayList<>();

        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> bottomNodes = new HashMap<>(); // horizontalDistance, node valuel - store the last node at
                                                             // each horizontal distance

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(node, 0));

        int minHorizontalDistance = 0;
        int maxHorizontalDistance = 0;

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> current = queue.poll();
            TreeNode currentNode = current.getFirst();
            int horizontalDistance = current.getSecond();

            minHorizontalDistance = Math.min(minHorizontalDistance, horizontalDistance);
            maxHorizontalDistance = Math.max(maxHorizontalDistance, horizontalDistance);

            bottomNodes.put(horizontalDistance, currentNode.val);


            if (currentNode.left != null) {
                queue.add(new Pair<>(currentNode.left, horizontalDistance - 1));
            }

            if (currentNode.right != null) {
                queue.add(new Pair<>(currentNode.right, horizontalDistance + 1));
            }
        }

        for (int i = minHorizontalDistance; i <= maxHorizontalDistance; i++) {
            result.add(bottomNodes.get(i));
        }

        return result;
    }
}
