/*
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely 
filled, and all nodes in the last level are as far left as possible. 
It can have between 1 and 2h nodes inclusive at the last level h.
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
//O(log(n)^2).
public class Solution {
    // A tree consisting of only a root node has a height of 0. In a full binary tree, number of nodes = 2^(h+1) - 1
    // Here a tree consisting of only a root node returns 1
    public int depth(TreeNode root, int dir){
        int h = 0;
        TreeNode cur = root;
        while(cur != null){
            if(dir == -1)
                cur = cur.left;
            else if(dir == 1)
                cur = cur.right;
            h++;
        }
        return h;
    }
    
    public int countNodes(TreeNode root) {
        // In a complete binary tree every level, except possibly the last, is completely filled
        if(root == null)
            return 0;
        int lDepth = depth(root, -1);
        int rDepth = depth(root, 1);
        // full binary tree
        if(lDepth == rDepth){
            return (1<<lDepth) - 1;
        }
        return countNodes(root.left) + 1 + countNodes(root.right);
    }
}   
