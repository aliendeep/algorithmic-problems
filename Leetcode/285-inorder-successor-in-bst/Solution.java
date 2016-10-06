/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.

*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode findMin(TreeNode node){
        if(node == null)
            return null;

        while(node.left != null)
            node = node.left;
        return node;
    }
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null)
            return root;
        TreeNode successor = null;
        while(root != null){
            // p exists in the right subtree
            if(root.val < p.val){
                root = root.right;
            }
            // p exists in the left subtree
            else if(root.val > p.val){
                // Only update successor value if we go the left side
                successor = root;
                root = root.left;
            }
            else{
                // found p
                if(root.right != null){
                    // find the minimum value of the left most part of the this right node
                    successor = findMin(root.right);
                }
                break;
            }
        }
        return successor;
    }
}