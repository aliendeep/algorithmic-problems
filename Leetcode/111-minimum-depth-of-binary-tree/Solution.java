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
    public int minDepth(TreeNode root) {
        if(root == null)    return 0;
        int l = minDepth(root.left);
        int r = minDepth(root.right);
        // consider the case when one subtree is empty
        if(l == 0 || r == 0)    return Math.max(l, r) + 1;
        return Math.min(l, r) + 1;    
    }
}