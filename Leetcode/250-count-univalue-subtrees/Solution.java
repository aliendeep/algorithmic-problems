/*
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
*/

// [7,82,82,-79,98,98,-79,-79,null,-28,-24,-28,-24,null,-79,null,97,65,-4,null,3,-4,65,3,null,97]
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
    class Info{
        public int val;
        public Info(){
            val = 0;
        }
    }
    public int countUnivalSubtrees(TreeNode root) {
        Info n = new Info();
        countUnivalSubtreesHelper(root, n);
        return n.val;
    }
    public boolean countUnivalSubtreesHelper(TreeNode root, Info n){        
        if(root == null)                                return true;
        boolean l = countUnivalSubtreesHelper(root.left, n);
        boolean r = countUnivalSubtreesHelper(root.right, n);

        // Both subtrees are valid
        if(l && r){
            if(root.left != null && root.val != root.left.val)
                return false;
            if(root.right != null && root.val != root.right.val)
                return false;
            n.val += 1;
            return true;
        }
        else
            return false;
    }
}

class Solution {
    int cntUniVal;
    public int countUnivalSubtrees(TreeNode root) {
        cntUniVal = 0;
        helper(root);
        return cntUniVal;
    }

    public boolean helper(TreeNode node){        
        if(node == null)                                
            return true;

        boolean left = helper(node.left);
        boolean right = helper(node.right);
        if(left && right){
            if(node.left != null && node.val != node.left.val)
                return false;
            if(node.right != null && node.val != node.right.val)
                return false;
            cntUniVal++;
            return true;
        }
        return false;
    }
}