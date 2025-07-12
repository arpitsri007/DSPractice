package org.codekart.trees;

public class ConstructTree {
    public static void main(String[] args) {
        ConstructTree constructTree = new ConstructTree();
        TreeNode root = constructTree.buildTree();
        System.out.println(root);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        return root;
    }

    // TODO: Construct Tree from Preorder and Inorder 
}
