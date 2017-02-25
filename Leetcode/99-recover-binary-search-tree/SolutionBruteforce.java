/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
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
 
// Bruteforce: O(height) space solution
// Example: 1, 2, 3, 4, 5, 6, 7
// Swapped element: 4, 7
// Inorder traversal becomes: 1, 2, 3, 7, 5, 6, 4
// Here 7 > 5 and 6 > 4
class Solution {
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    TreeNode first = null;
    TreeNode second = null;
    
    public void inorder(TreeNode root){
        if(root == null)
            return;

        inorder(root.left);
        
        if(first == null &&  prev.val > root.val)
            first = prev;
        if(first != null &&  prev.val > root.val)
            second = root;
        prev = root;
        inorder(root.right);
    }
    
    public void recoverTree(TreeNode root) {
        inorder(root);
        // Exchange the values
        int t = first.val; 
        first.val = second.val;   
        second.val = t;
    }
}
