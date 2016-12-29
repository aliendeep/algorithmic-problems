/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

Example :

    1
   / \
  2   2
 / \ / \
3  4 4  3
The above binary tree is symmetric. 
But the following is not:

    1
   / \
  2   2
   \   \
   3    3
Return 0 / 1 ( 0 for false, 1 for true ) for this problem
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
public class Solution {
  public boolean isSym(TreeNode a, TreeNode b) {
      if(a == null && b == null)
          return true;
      if(a == null || b == null)
          return false;
     
      return ((a.val == b.val) && isSym(a.left, b.right) && isSym(a.right, b.left));
  }    
  public int isSymmetric(TreeNode a) {
      return isSym(a, a) == true ? 1 : 0;
  }
}
