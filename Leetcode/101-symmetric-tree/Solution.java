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
    public boolean isSymmetric(TreeNode l, TreeNode r) {
        if(l == null && r == null)
            return true;
        if(l == null || r == null)
            return false;
        return l.val == r.val && 
            isSymmetric(l.left, r.right) &&
            isSymmetric(l.right, r.left);
    }
    
    public boolean isSymmetric(TreeNode root) {
        if(root == null)    
            return true;
        return isSymmetric(root.left, root.right);
    }
}