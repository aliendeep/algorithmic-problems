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
// Example: 1, 2, 3, 4, 5, 6, 7
// Swapped element: 4, 7
// Inorder traversal becomes: 1, 2, 3, 7, 5, 6, 4
/*
Morris Traverse
1. Initialize current as root 
2. While current is not NULL
   If current does not have left child
      a) Print current’s data
      b) Go to the right, i.e., current = current->right
   Else
      a) Make current as right child of the rightmost node in current's left subtree
      b) Go to this left child, i.e., current = current->left
*/ 
// http://liacs.leidenuniv.nl/~deutzah/DS/september28.pdf
// Figure: 6-20
// O(1) space
public class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode prev = new TreeNode(Integer.MIN_VALUE);
        TreeNode first = null;
        TreeNode second = null;
        TreeNode cur = root;
        while(cur != null){
            // Cur doesn't have any left child
            if(cur.left == null){
                // visit cur
                if(first == null && prev.val > cur.val)
                    first = prev;
                if(first != null && prev.val > cur.val)
                    second = cur;
                // Go to the right subtree
                prev = cur;
                cur = cur.right;
            }
            else{
                TreeNode temp = cur.left;
                // Important: temp.right != parent
                while(temp.right != null && temp.right != cur){
                    temp = temp.right;
                }
                // Make current as right child of the rightmost node in current's left subtree
                if(temp.right == null){
                    temp.right = cur;   
                    // Go to the left subtree
                    cur = cur.left;
                }
                // Reached a temporary parent pointer as cur.right == cur
                else{
                    // visit cur
                    if(first == null && prev.val > cur.val)
                        first = prev;
                    if(first != null && prev.val > cur.val)
                        second = cur;
                    // Revert changes
                    temp.right = null;
                    prev = cur;
                    cur = cur.right;
                }
            }
        }
        // Swap the values of the two nodes
        int t = first.val; 
        first.val = second.val;   
        second.val = t;
    }
}