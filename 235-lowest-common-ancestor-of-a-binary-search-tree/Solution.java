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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        while(cur != null){
            // Go to left subtree
            if(p.val < cur.val && q.val < cur.val)
                cur = cur.left;
            // Go to right subtree
            else if(p.val > cur.val && q.val > cur.val)
                cur = cur.right;
            else
                return cur;
        }
        return null;
    }
}