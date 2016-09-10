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
    public BSTIterator(TreeNode root) {
        stk = new LinkedList<TreeNode>();
        if(root != null){
            // Push the leftmost path of the tree in the stack
            stk.addFirst(root);
            while(root.left != null){
                stk.addFirst(root.left);
                root = root.left;
            }
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stk.isEmpty() == false; 
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode t = stk.removeFirst();
        // push the leftmost sub path of the right child        
        TreeNode cur = t.right;
        while(cur != null){
            stk.addFirst(cur);
            cur = cur.left;
        }
        return t.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */