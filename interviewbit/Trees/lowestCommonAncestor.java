/*
Find the lowest common ancestor in an unordered binary tree given two values in 
the tree.

 Lowest common ancestor : the lowest common ancestor (LCA) of two nodes v and w 
 in a tree or directed acyclic graph (DAG) is the lowest (i.e. deepest) node that 
 has both v and w as descendants. 
Example :


        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2_     0        8
         /   \
         7    4
For the above tree, the LCA of nodes 5 and 1 is 3.

 LCA = Lowest common ancestor 
Please note that LCA for nodes 5 and 4 is 5.

You are given 2 values. Find the lowest common ancestor of the two nodes represented 
by val1 and val2
No guarantee that val1 and val2 exist in the tree. If one value doesn’t exist in 
the tree then return -1.
There are no duplicate values.
You can use extra memory, helper functions, and can modify the node struct but, 
you can’t add a parent pointer.
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
  public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if(root == null || root.val == p || root.val == q)
            return root;
      TreeNode l = lowestCommonAncestor(root.left, p, q);
      TreeNode r = lowestCommonAncestor(root.right, p, q);
      if(l == null)
          return r;
      if(r == null)
          return l;
      return root;      
  }
  
  public boolean exist(TreeNode node, int val){
      if(node == null)    return false;
      if(node.val == val) return true;
      return exist(node.left, val) || exist(node.right, val);
  }
  
  public int lca(TreeNode root, int p, int q) {
      if(!exist(root, p) || !exist(root, q))  return -1;
      TreeNode r = lowestCommonAncestor(root, p, q);
      return r.val;
  }
}
