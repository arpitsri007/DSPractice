package org.codekart.trees;

import java.util.LinkedList;
import java.util.Queue;

public class BST {
    private TreeNode root;

    static int sum = 0;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    private TreeNode insert(TreeNode root, int data) {
        if (root == null) {
            return new TreeNode(data);
        }

        if (data < root.val) {
            root.left = insert(root.left, data);
        } else if (data > root.val) {
            root.right = insert(root.right, data);
        }

        return root;
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }

    public void preorder() {
        preorder(root);
    }

    private void preorder(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val);
        preorder(root.left);
        preorder(root.right);
    }

    public void postorder() {
        postorder(root);
    }

    private void postorder(TreeNode root) {
        if (root == null) {
            return;
        }

        postorder(root.left);
        postorder(root.right);
        System.out.println(root.val);
    }

    public void levelorder() {
        levelorder(root);
    }

    private void levelorder(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.val);

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public int height() {
        return height(root);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(height(root.left), height(root.right));
    }

    public int size() {
        return size(root);
    }

    private int size(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + size(root.left) + size(root.right);
    }

    public boolean search(int data) {
        return search(root, data);
    }

    private boolean search(TreeNode root, int data) {
        if (root == null) {
            return false;
        }

        if (root.val == data) {
            return true;
        }

        if (data < root.val) {
            return search(root.left, data);
        } else {
            return search(root.right, data);
        }
    }

    public void delete(int data) {
        root = delete(root, data);
    }

    private TreeNode delete(TreeNode root, int data) {
        if (root == null) {
            return null;
        }

        if (data < root.val) {
            root.left = delete(root.left, data);
        } else if (data > root.val) {
            root.right = delete(root.right, data);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            }
        }

        return root;
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val);
        printTree(root.left);
        printTree(root.right);
    }

    // Range sum of BST
    public int rangeSum(int low, int high) {
        return rangeSum(root, low, high);
    }

    private int rangeSum(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        if (root.val < low) {
            return rangeSum(root.right, low, high);
        } else if (root.val > high) {
            return rangeSum(root.left, low, high);
        }

        return root.val + rangeSum(root.left, low, high) + rangeSum(root.right, low, high);
    }

    // range sum using inorder traversal
    public int rangeSumUsingInorderTraversal(TreeNode root, int low, int high) {
        sum = 0;
        inorder(root, low, high);
        return sum;
    }

    private static void inorder(TreeNode root, int low, int high) {
        if (root == null) {
            return;
        }
        inorder(root.left, low, high);
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }
        inorder(root.right, low, high);
    }

    // Check if a tree is a BST
    public boolean isBST() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }

        if (root.val <= min || root.val >= max) {
            return false;
        }

        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    // leetcode 530 - minimum absolute difference in BST
    // Intuition : Inorder traversal of BST gives us a sorted array.
    // So, we can find the minimum absolute difference by comparing the current node
    // with the previous node.
    public static class MinDiff {
        static int minDiff = Integer.MAX_VALUE;
        static TreeNode prev = null;

        // Using instance variable
        public static int getMinimumDifference(TreeNode root) {
            inorder(root);
            return minDiff;
        }

        static void inorder(TreeNode root) {
            if (root == null) {
                return;
            }

            inorder(root.left);
            if (prev != null) {
                minDiff = Math.min(minDiff, root.val - prev.val);
            }
            prev = root;
            inorder(root.right);
        }

        // Alternative approach :
        // this won't work because java is pass by value and not pass by reference
        // either use instance variable or wrapper class or Use an array (common trick)

        // private static void inorder(TreeNode root, TreeNode prevNode) {
        // if (root == null) {
        // return;
        // }

        // inorder(root.left, prevNode);
        // if (prevNode != null) {
        // minDiff = Math.min(minDiff, root.val - prevNode.val);
        // }
        // prevNode = root;
        // inorder(root.right, prevNode);
        // }

        // Approach 2 : Using an array to store the previous node
        public static int getMinimumDifferenceUsingReference(TreeNode root) {
            minDiff = Integer.MAX_VALUE;
            inorder(root, new TreeNode[] { null });
            return minDiff;
        }

        private static void inorder(TreeNode root, TreeNode[] prevNode) {
            if (root == null) {
                return;
            }
            inorder(root.left, prevNode);
            if (prevNode[0] != null) {
                minDiff = Math.min(minDiff, root.val - prevNode[0].val);
            }
            prevNode[0] = root;
            inorder(root.right, prevNode);
        }

        // Approach 3 : Using a wrapper class
        static class Wrapper {
            TreeNode prev;

            Wrapper(TreeNode prev) {
                this.prev = prev;
            }
        }

        public static int getMinimumDifferenceUsingWrapperClass(TreeNode root) {
            minDiff = Integer.MAX_VALUE;
            inorder(root, new Wrapper(null));
            return minDiff;
        }

        private static void inorder(TreeNode root, Wrapper prevNode) {
            if (root == null) {
                return;
            }
            inorder(root.left, prevNode);
            if (prevNode.prev != null) {
                minDiff = Math.min(minDiff, root.val - prevNode.prev.val);
            }
            prevNode.prev = root;
            inorder(root.right, prevNode);
        }

    }

    // leetcode 669 - trim a binary search tree
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }

        if (root.val < low) {
            return trimBST(root.right, low, high);
        }

        if (root.val > high) {
            return trimBST(root.left, low, high);
        }

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
    }

}
