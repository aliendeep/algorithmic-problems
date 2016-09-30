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
    public boolean isValidBST(TreeNode root, long minValue, long maxValue) {
        if(root == null)    
            return true;
        if(root.val <= minValue || root.val >= maxValue)
            return false;
        return isValidBST(root.left, minValue, root.val) &&
               isValidBST(root.right, root.val, maxValue);
    }
    
    public boolean isValidBST(TreeNode root) {
        if(root == null)    return true;
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}