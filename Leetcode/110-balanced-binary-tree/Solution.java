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
    class Pair{
        int height;
        boolean isBalancedFlag;
        public Pair(int height, boolean isBalancedFlag){
            this.height = height;
            this.isBalancedFlag = isBalancedFlag;
        }
    };
    
    Pair isBalancedHeight(TreeNode node) {
        if(node == null)    
            return new Pair(0, true);
        Pair l = isBalancedHeight(node.left);
        if(l.isBalancedFlag == false)
            return new Pair(0, false);
            
        Pair r = isBalancedHeight(node.right);
        if(r.isBalancedFlag == false)
            return new Pair(0, false);
        
        if(Math.abs(l.height - r.height) > 1)
            return new Pair(0, false);
            
        Pair p = new Pair(Math.max(l.height, r.height)+1, true);         
        return p;
    }
    
    public boolean isBalanced(TreeNode root) {
        if(root == null)    return true;
        return isBalancedHeight(root).isBalancedFlag;
    }
}