/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?
*
/
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        if(root == null)
            return r;
        Deque<TreeNode> stk = new LinkedList<>();
        TreeNode cur = root;
        // insert leftmost path in the stk
        while(cur != null){
            stk.push(cur);
            cur = cur.left;
        }
        while(!stk.isEmpty()){
            TreeNode t = stk.pop();
            r.add(t.val);
            if(t.right != null){
                cur = t.right;
                while(cur != null){
                    stk.push(cur);
                    cur = cur.left;
                }
            }
        }
        return r;
    }
}

// left, root, right
class Solution2 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        if(root == null)
            return r;
        Deque<TreeNode> stk = new LinkedList<>();
        TreeNode cur = root;
        while(cur != null || !stk.isEmpty()){
            while(cur != null){
                stk.push(cur);
                cur = cur.left;
            }     
            cur = stk.pop();
            r.add(cur.val);
            cur = cur.right;
        }
        return r;
    }
}

class Solution3 {
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
    
    // in order - left, root, right    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList<Integer>();
        if(root == null) return r;
        Stack<Cmd> s = new Stack<Cmd>();
        s.add(new Cmd(root, true));
        while(!s.isEmpty()){
            Cmd t = s.pop();
            if(t.expand == true){
                // reverse order insert
                if(t.n.right != null)
                    s.push(new Cmd(t.n.right, true));

                s.push(new Cmd(t.n, false));

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
