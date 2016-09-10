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
        TreeNode n;
        boolean expand;
        public Cmd(){
            n = null;
            expand = false;
        }
        public Cmd(TreeNode r, boolean e){
            n = r;
            expand = e;
        }
    };
    
    // postorder - left, right, root    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList<Integer>();
        if(root == null) return r;

        Stack<Cmd> s = new Stack<Cmd>();
        s.push(new Cmd(root, true));
        while(!s.isEmpty()){
            Cmd t = s.pop();
            if(t.expand == true){
                // reverse order insert
                s.push(new Cmd(t.n, false));
                if(t.n.right != null)
                    s.push(new Cmd(t.n.right, true));
                if(t.n.left != null)
                    s.push(new Cmd(t.n.left, true));
            }
            else{
                r.add(t.n.val);
            }
        }
        return r;
    }
}