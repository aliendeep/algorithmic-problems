/*
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1
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
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;
        if(root.left == null && root.right == null)
            return root;
        
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        invertTree(root.left);
        invertTree(root.right);
        return root;
        
    }
}

class Solution2 {
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return root;
        TreeNode temp = root.left;            
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }
}

class Solution3 {
    // Iterative Solution : Using stack
    public TreeNode invertTree(TreeNode root) {
        if(root == null)    return root;
        Deque<TreeNode> stk = new LinkedList<>();
        stk.push(root);
        // reverse order push in stack
        while(!stk.isEmpty()){
            TreeNode top = stk.pop();
            TreeNode left = top.left;
            TreeNode right = top.right;
            top.left = right;
            top.right =  left;
            
            if(left != null)
                stk.push(left);
            if(right!= null)
                stk.push(right);
        }
        return root;
    }
}


class Solution4 {
    // Iterative Solution : BFS
    public TreeNode invertTree(TreeNode root) {
        if(root == null)    return root;
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        while(!Q.isEmpty()){
            TreeNode top = Q.remove();
            TreeNode left = top.left;
            TreeNode right = top.right;
            top.left = right;
            top.right =  left;
            
            if(left != null)
                Q.add(left);
            if(right!= null)
                Q.add(right);
        }
        return root;
    }
}