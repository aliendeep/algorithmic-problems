// Alternative: Assuming both nodes are present in the tree
public class Solution2 {
    class Pair{
        public boolean isAncestor;
        public TreeNode node;
        public Pair(TreeNode a, boolean n) {
            isAncestor = n;
            node = a;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Pair r = lowestCommonAncestorHelper(root, p, q);
        return r.isAncestor ? r.node : null;
    }
    
    public Pair lowestCommonAncestorHelper(TreeNode root, TreeNode p, TreeNode q){
        if(root == null)                return new Pair(null, false);
        
        if(root == p && root == q)      return new Pair(root, true);
        
        Pair l = lowestCommonAncestorHelper(root.left, p, q);
        // Found common ancestor
        if(l.isAncestor)        return l;

        Pair r = lowestCommonAncestorHelper(root.right, p, q);
        // Found common ancestor
        if(r.isAncestor)        return r;
        
        if(l.node != null && r.node != null)
            return new Pair(root, true);
        if(root == p || root == q){
            // At p or q and found the other node in one of the subtrees
            return new Pair(root, l.node != null || r.node != null);
        }
        else{
            return new Pair(l.node != null ? l.node : r.node, false);
        }
    }
}