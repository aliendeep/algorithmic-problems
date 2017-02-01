/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could 
represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
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
