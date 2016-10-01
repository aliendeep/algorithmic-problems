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
    public static int cur = 0;
    // inorder traversal : left, root, right
    public TreeNode kthSmallestNode(TreeNode root, int k) {
        if(root == null)
            return null;
            
        TreeNode l = kthSmallestNode(root.left, k);
        if(l != null)
            return l;

        cur++;
        if(cur == k){
            return root;
        }

        TreeNode r = kthSmallestNode(root.right, k);
        if(r != null)
            return r;
        return null;
    }

    public int kthSmallest(TreeNode root, int k) {
        cur = 0;
        TreeNode r = kthSmallestNode(root, k);
        if(r != null)   
            return r.val;
        return -1;
    }
}