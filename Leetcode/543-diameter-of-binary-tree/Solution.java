/*
Given a binary tree, you need to compute the length of the diameter of the tree. 
The diameter of a binary tree is the length of the longest path between any two 
nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges 
between them.
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
    class Info {
        int height;
        int bestAns;
        public Info(int r, int b) {
            height = r;
            bestAns = b;
        }
    }
    
    Info dia(TreeNode node) {
        if(node == null)                                
            return new Info(0, 0);    
        Info l = dia(node.left);
        Info r = dia(node.right);
        int h = Math.max(l.height, r.height) + 1;
        int d = Math.max(l.height + r.height, Math.max(l.bestAns, r.bestAns));
        return new Info(h, d);
    }
    
    public int diameterOfBinaryTree(TreeNode root) {
        return dia(root).bestAns;
    }
}
