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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>();

        if(root == null)    return r;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int cntLevel = q.size();
        
        List<Integer> t = new ArrayList<Integer>();        
        while(!q.isEmpty()){
            TreeNode n = q.remove();
            t.add(n.val);

            cntLevel--;
            if(n.left != null)      q.add(n.left);
            if(n.right != null)     q.add(n.right);

            if(cntLevel == 0){
                cntLevel = q.size();                
                r.add(t);
                t = new ArrayList<Integer>();      
            }
        }
        return r;
    }
}