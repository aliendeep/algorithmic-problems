/*

Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

Example :

Given the below binary tree,

       1
      / \
     2   3
Return 6.
*/

/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// O(n)
public class Solution {
    class Info{
        long pathIncludingRoot;
        long bestPath;
        public Info(long p, long b){
            pathIncludingRoot = p;
            bestPath = b;
        }
    }
  public Info maxSum(TreeNode node) {
      if(node == null)
            return new Info(Integer.MIN_VALUE, Integer.MIN_VALUE);

      Info left = maxSum(node.left);
      Info right = maxSum(node.right);
        long x = Math.max(Math.max(left.pathIncludingRoot, 0), 
                          Math.max(0, right.pathIncludingRoot)) 
                          + node.val;
        long y = Math.max(left.pathIncludingRoot, 0) +  
                 Math.max(0, right.pathIncludingRoot) + node.val;
        long z = Math.max(left.bestPath, right.bestPath);
        long bestPath = Math.max(y, z);
        return new Info(x, bestPath);
  }
  public int maxPathSum(TreeNode node) {
      if(node == null)    return 0;
      Info r = maxSum(node);
      return (int)r.bestPath;
  }
}
