/*
Given a root node reference of a BST and a key, delete the node with the given key 
in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
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
    public int smallestNodeRightMostSubtree(TreeNode right){
        while(right.left != null){
            right = right.left;
        }
        return right.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)
            return root;

        if(root.val > key){
            root.left = deleteNode(root.left, key);        
        }   
        else if(root.val < key){
            root.right = deleteNode(root.right, key);        
        }
        // Found the node to be deleted
        else{
            // only has left child
            if(root.right == null)
                return root.left;
            // only has right child
            else if(root.left == null)
                return root.right;
            
            // Replace node's value by the smallest node in the right subtree and delete the other node
            root.val = smallestNodeRightMostSubtree(root.right);
            root.right = deleteNode(root.right, root.val);   
        }
        return root;
    }
}
