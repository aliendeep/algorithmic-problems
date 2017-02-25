/*
Given a binary tree, find the largest subtree which is a Binary Search Tree 
(BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.
Hint:

You can recursively use algorithm similar to 98. Validate Binary Search Tree at 
each node of the tree, which will result in O(nlogn) time complexity.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?

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
// Time: O(n)
// Space: O(h)
public class Solution {
    class Info{
        // size of the BST
        int cnt;
        boolean isBST;
        int min;
        int max;
        public Info(int num, int mn, int mx, boolean valid){
            cnt = num;
            min = mn;
            max = mx;
            isBST = valid;
        }
    }
    
    public Info largestBST(TreeNode root){
        if(root == null)            
            return new Info(0, Integer.MAX_VALUE, Integer.MIN_VALUE, true);

        Info l = largestBST(root.left);            
        Info r = largestBST(root.right);
        if(!l.isBST || !r.isBST || root.val < l.max || root.val > r.min){
            return new Info(Math.max(l.cnt, r.cnt), 0, 0, false);
        }        
        else{
            return new Info(l.cnt + r.cnt + 1, Math.min(root.val, l.min),  Math.max(root.val, r.max), true);
        }
   }
    public int largestBSTSubtree(TreeNode root) {
        if(root == null)
            return 0;
        Info t = largestBST(root);
        return t.cnt;
    }
}
