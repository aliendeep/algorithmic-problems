/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
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
class Solution {
    // Time: O(logn)
    // Space: O(logh) where h is the height
    // Binary Search
    double minDiff = Double.MAX_VALUE;
    int r;

    public void closestValueRecursive(TreeNode root, double target) {
        if(root == null)
            return;
        double diff = Math.abs((double)root.val - target);
        if(minDiff > diff){
            minDiff = diff;
            r = root.val;
        }
        // All nodes in the right subtree will have larger value than root, So diff will be greater if we traverse the right subtree and we can prune
        if((double)root.val > target)
            closestValueRecursive(root.left, target);
        else
            closestValueRecursive(root.right, target);
    }
    
    public int closestValue(TreeNode root, double target) {
        r = -1;
        minDiff = Double.MAX_VALUE;
        closestValueRecursive(root, target);
        return r;
    }
}