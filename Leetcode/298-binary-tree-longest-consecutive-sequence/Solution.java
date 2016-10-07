/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
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
// Main Idea: Reset length
public class Solution {
    public int longestConsecutive(TreeNode root) {
        if(root == null)
            return 0;
        return Math.max(helper(root.left, 1, root.val), helper(root.right, 1, root.val));
    }
    
    public int helper(TreeNode node, int len, int parentVal){
        if(node == null)
            return len;
        // increasing
        if(node.val - parentVal == 1)
            len++;
        // reset the length
        else
            len = 1;
        
        int left = helper(node.left, len, node.val);
        int right = helper(node.right, len, node.val);
        return Math.max(Math.max(left, right), len);
    }
}