/*
Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the 
nodes have the same value.

Return 0 / 1 ( 0 for false, 1 for true ) for this problem

Example :

Input : 

   1       1
  / \     / \
 2   3   2   3

Output : 
  1 or True
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
  public boolean isSame(TreeNode a, TreeNode b) {
      if(a == null && b == null)
          return true;
      if(a == null || b == null)
          return false;
     
      return ((a.val == b.val) && isSame(a.left, b.left) && isSame(a.right, b.right));
  }
  public int isSameTree(TreeNode a, TreeNode b) {
      return isSame(a, b) == true ? 1 : 0;
  } 
}
