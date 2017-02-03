/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    public Deque<TreeNode> stk;
    
    public void pushLeftSubTreeNodes(TreeNode node){
        while(node != null){
            stk.push(node);
            node = node.left;
        }
    }

    public BSTIterator(TreeNode root) {
        stk = new LinkedList<TreeNode>();
        if(root != null){
            // Push the leftmost path of the tree in the stack
            pushLeftSubTreeNodes(root);
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stk.isEmpty() == false; 
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode t = stk.pop();
        // push the leftmost sub path of the right child        
        pushLeftSubTreeNodes(t.right);
        return t.val;
    }
}
/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
