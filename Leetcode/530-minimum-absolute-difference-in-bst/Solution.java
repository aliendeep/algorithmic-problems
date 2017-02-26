/*
Given a binary search tree with non-negative values, find the minimum absolute 
difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
Note: There are at least two nodes in this BST.
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
    List<Integer> sorted;
    void inorder(TreeNode node){
        if(node == null)    return;
        
        inorder(node.left);
        sorted.add(node.val);
        inorder(node.right);
    }
    
    public int getMinimumDifference(TreeNode root) {
        sorted = new ArrayList<>();
        inorder(root);
        int n = sorted.size();
        int min = Integer.MAX_VALUE;
        int prev = sorted.get(0), cur;
        for(int i=1; i<n; ++i){
            cur = sorted.get(i);
            min = Math.min(min, cur - prev);
            prev = cur;
        }
        return min;
    }
}
