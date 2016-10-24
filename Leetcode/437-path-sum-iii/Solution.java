/*
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
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
    int count;
    public int pathSumCnt(TreeNode root, int curSum, int sum) {
        if(root == null)                                return 0;
        curSum += root.val;
        int r = (sum == curSum ? 1 : 0);
        int left = pathSumCnt(root.left, curSum, sum);
        int right = pathSumCnt(root.right, curSum, sum);
        return left + right + r;
    }
    
    public void pathSumHelper(TreeNode root, int sum){
        if(root == null)    
            return;
        
        count += pathSumCnt(root, 0, sum);
        pathSumHelper(root.left,  sum);
        pathSumHelper(root.right, sum);
    }
    
    public int pathSum(TreeNode root, int sum){
        count  = 0;
        pathSumHelper(root, sum);
        return count;
    }
}