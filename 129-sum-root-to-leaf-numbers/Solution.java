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
    public int getSumNum(TreeNode root, int curSum){
        if(root == null)    
            return 0;
        
        curSum = curSum*10 + root.val;
        // if leaf
        if(root.left == null && root.right == null)
            return curSum;

        return getSumNum(root.left, curSum) + getSumNum(root.right, curSum);
    }
    public int sumNumbers(TreeNode root) {
        if(root == null)
            return 0;
        return getSumNum(root, 0);
    }
}