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
    class Cmd{
        TreeNode node;
        boolean expand;
        public Cmd(TreeNode t, boolean ex){
            node = t;
            expand = ex;
        }
    }
    // Alternative: in order traversal (Stack version) 
    public boolean isValidBST(TreeNode root) {
        if(root == null)    
            return true;
        Deque<Cmd> stk = new LinkedList<>();
        stk.push(new Cmd(root, true));
        long prev = Long.MIN_VALUE;
        while(!stk.isEmpty()){
            Cmd top = stk.pop();
            if(top.expand == true){
                if(top.node.right != null)
                    stk.push(new Cmd(top.node.right, true));
                
                stk.push(new Cmd(top.node, false));
                
                if(top.node.left != null)
                    stk.push(new Cmd(top.node.left, true));
            }
            else{
                if(top.node.val <= prev)
                    return false;
                prev = top.node.val;
            }
        }
        return true;
    }
}