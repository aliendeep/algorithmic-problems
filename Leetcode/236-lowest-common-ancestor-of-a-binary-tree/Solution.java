/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the 
lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, 
since a node can be a descendant of itself according to the LCA definition.
*/
public class Solution {
    class Pair{
        public int numNodes;
        public TreeNode ancestor;
        public Pair(int n, TreeNode a){
            numNodes = n;
            ancestor = a;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorHelper(root, p, q).ancestor;
    }
    
    public Pair lowestCommonAncestorHelper(TreeNode node, TreeNode p, TreeNode q){
        if(node == null)
            return new Pair(0, null);
        Pair l = lowestCommonAncestorHelper(node.left, p, q);
        if(l.numNodes == 2){
            return l;
        } 
        Pair r = lowestCommonAncestorHelper(node.right, p, q);
        if(r.numNodes == 2){
            return r;
        } 
        int n = l.numNodes + r.numNodes + (node == p ? 1 : 0) + (node == q ? 1 : 0);
        return n == 2 ? new Pair(2, node) : new Pair(n, null);
    }
}