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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        if(root == null)
            return r;
        Deque<TreeNode> stk = new LinkedList<>();
        stk.push(root);
        while(!stk.isEmpty()){
            TreeNode t = stk.pop();
            r.add(t.val);
            if(t.right != null)
                stk.push(t.right);
            if(t.left != null)
                stk.push(t.left);
        }
        return r;
   }
}

class Solution2 {
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
    
    // preorder - root, left, right    
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList<Integer>();
        if(root == null) return r;

        Stack<Cmd> s = new Stack<Cmd>();
        s.push(new Cmd(root, true));
        while(!s.isEmpty()){
            Cmd t = s.pop();
            if(t.expand == true){
                // reverse order insert
                if(t.n.right != null)
                    s.push(new Cmd(t.n.right, true));
                if(t.n.left != null)
                    s.push(new Cmd(t.n.left, true));
                s.push(new Cmd(t.n, false));
            }
            else{
                r.add(t.n.val);
            }
        }
        return r;
    }
}