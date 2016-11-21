/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.
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

public class Solution {
    // Alternative: in order traversal 
    long prev;
    public boolean inorder(TreeNode node){
        if(node == null)    
            return true;
        
        if(!inorder(node.left))
            return false;
        
        if(node.val <= prev)
            return false;
        
        prev = node.val;
        
        if(!inorder(node.right))
            return false;
        return true;
        
    }
    public boolean isValidBST(TreeNode root) {
        prev = Long.MIN_VALUE;
        return inorder(root);
    }
}