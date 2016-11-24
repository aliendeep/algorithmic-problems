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
    // Alternative: Iterative Version
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);
        
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        while(!parent.containsKey(p) || !parent.containsKey(q)){
            TreeNode t = Q.remove();
            if(t.left != null){
                parent.put(t.left, t);
                Q.add(t.left);
            }
            if(t.right != null){
                parent.put(t.right, t);
                Q.add(t.right);
            }
        }
        
        Set<TreeNode> ancestors = new HashSet<>();
        while(p != null){
            ancestors.add(p);
            p = parent.get(p);
        }
        while(!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }
}

// Stack
public class Solution2 {
    // Alternative: Iterative Version
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);
        
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        stk.push(root);
        while(!parent.containsKey(p) || !parent.containsKey(q)){
            TreeNode t = stk.pop();
            if(t.left != null){
                parent.put(t.left, t);
                stk.push(t.left);
            }
            if(t.right != null){
                parent.put(t.right, t);
                stk.push(t.right);
            }
        }
        
        Set<TreeNode> ancestors = new HashSet<>();
        while(p != null){
            ancestors.add(p);
            p = parent.get(p);
        }
        while(!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }
}