/*
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
*/
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
    public int sumOfLeftLeaves(TreeNode root, int dir) {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null && dir == -1)
            return root.val;
        return sumOfLeftLeaves(root.left, -1) + sumOfLeftLeaves(root.right, +1); 
    }

    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeaves(root, 0);
    }
}

// Input: [-8,-6,7,6,null,null,null,null,5]
// Stack in order traversal
class Solution2 {
    boolean isLeaf(TreeNode n){
        return (n.left == null && n.right == null);
    }    
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null)
            return 0;
        Deque<TreeNode> stk = new LinkedList<>();
        TreeNode cur = root;
        // insert leftmost path in the stk
        while(cur != null){
            stk.push(cur);
            cur = cur.left;
        }
        int sum = 0;
        // leftmost leaf
        if(stk.size() > 1 && isLeaf(stk.peekFirst())){
            sum += stk.pop().val;
        }
        while(!stk.isEmpty()){
            TreeNode t = stk.pop();
            if(t.right != null){
                cur = t.right;
                while(cur != null){
                    stk.push(cur);
                    if(cur.left != null && isLeaf(cur.left))
                        sum += cur.left.val;
                    cur = cur.left;
                }
            }
        }
        return sum;
    }
}

// Queue
class Solution3 {
    boolean isLeaf(TreeNode n){
        return (n.left == null && n.right == null);
    }
    // Queue
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null)    return 0;
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        
        int sum = 0;
        while(!Q.isEmpty()){
            TreeNode f = Q.remove();
            if(f.left != null){
                if(isLeaf(f.left))
                    sum += f.left.val;
                else
                    Q.add(f.left);
            }
            if(f.right != null)
                Q.add(f.right);
        }
        return sum;
    }
}
