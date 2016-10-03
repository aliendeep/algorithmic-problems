/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
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

// Time : O(1) Space
public class Solution {
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        
        TreeNode cur = root;
        while(cur != null){
            if(cur.left != null){
                // find the right most node of left child
                TreeNode prev = cur.left;
                while(prev.right != null){
                    prev = prev.right;
                }
                
                prev.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}