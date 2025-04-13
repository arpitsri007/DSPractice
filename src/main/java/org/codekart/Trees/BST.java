package org.codekart.Trees;

import java.util.LinkedList;
import java.util.Queue;

public class BST {
    private Node root;

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    private Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        }

        if(data < root.data) {
            root.left = insert(root.left, data);
        } else if(data > root.data) {
            root.right = insert(root.right, data);
        }

        return root;
    }
    
    public void inorder() {
        inorder(root);
    }

    private void inorder(Node root) {
        if(root == null) {
            return;
        }

        inorder(root.left);
        System.out.println(root.data);
        inorder(root.right);
    }

    public void preorder() {
        preorder(root);
    }

    private void preorder(Node root) {
        if(root == null) {
            return;
        }

        System.out.println(root.data);
        preorder(root.left);
        preorder(root.right);
    }

    public void postorder() {
        postorder(root);
    }

    private void postorder(Node root) {
        if(root == null) {
            return;
        }

        postorder(root.left);
        postorder(root.right);
        System.out.println(root.data);
    }

    public void levelorder() {  
        levelorder(root);
    }

    private void levelorder(Node root) {
        if(root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.data);

            if(node.left != null) {
                queue.add(node.left);
            }

            if(node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        if(root == null) {
            return 0;
        }

        return 1 + Math.max(height(root.left), height(root.right));
    }

    public int size() {
        return size(root);
    }

    private int size(Node root) {
        if(root == null) {
            return 0;
        }

        return 1 + size(root.left) + size(root.right);
    }

    public boolean search(int data) {
        return search(root, data);
    }

    private boolean search(Node root, int data) {
        if(root == null) {
            return false;
        }

        if(root.data == data) {
            return true;
        }

        if(data < root.data) {
            return search(root.left, data);
        } else {
            return search(root.right, data);
        }
    }

    public void delete(int data) {
        root = delete(root, data);
    }

    private Node delete(Node root, int data) {
        if(root == null) {
            return null;
        }

        if(data < root.data) {
            root.left = delete(root.left, data);
        } else if(data > root.data) {
            root.right = delete(root.right, data);
        } else {
            if(root.left == null && root.right == null) {
                return null;
            }
        }

        return root;
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(Node root) {
        if(root == null) {
            return;
        }

        System.out.println(root.data);
        printTree(root.left);
        printTree(root.right);
    }
    
    // Range sum of BST
    public int rangeSum(int low, int high) {
        return rangeSum(root, low, high);
    }

    private int rangeSum(Node root, int low, int high) {
        if(root == null) {
            return 0;
        }

        if(root.data < low) {
            return rangeSum(root.right, low, high);
        } else if(root.data > high) {
            return rangeSum(root.left, low, high);
        }

        return root.data + rangeSum(root.left, low, high) + rangeSum(root.right, low, high);
    }


    // Check if a tree is a BST
    public boolean isBST() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(Node root, int min, int max) {
        if(root == null) {
            return true;
        }

        if(root.data <= min || root.data >= max) {
            return false;
        }

        return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
    }

    public static class MinDiff {
        int minDiff = Integer.MAX_VALUE;
        Node prev = null;

        void inorder(Node root) {
            if(root == null) {
                return;
            }
    
            inorder(root.left);
            if(prev != null) {
                minDiff = Math.min(minDiff, root.data - prev.data);
            }
            prev = root;
            inorder(root.right);
        }
    
        // minimum absolute difference in BST
        public int getMinimumDifference(Node root) {
            inorder(root);
            return minDiff;
        }
    }

 

    
    
    
}
