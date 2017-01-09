/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.
Sample Input:
[2,1,3]
[]
[1,1]
[2147483647]
Sample Output:
true
true
false
true
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
    public boolean isValidBST(TreeNode root, long minValue, long maxValue) {
        if(root == null)    
            return true;
        if(root.val <= minValue || root.val >= maxValue)
            return false;
        return isValidBST(root.left, minValue, root.val) &&
               isValidBST(root.right, root.val, maxValue);
    }
    
    public boolean isValidBST(TreeNode root) {
        if(root == null)    return true;
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}

class Solution2 {
    // Alternative: in order traversal 
    long prev;
    public boolean inorder(TreeNode node){
        if(node == null)    
            return true;
        
        if(!inorder(node.left))
            return false;
        
        if(node.val <= prev)
            return false;
        
        prev = node.val;
        
        if(!inorder(node.right))
            return false;
        return true;
        
    }
    public boolean isValidBST(TreeNode root) {
        prev = Long.MIN_VALUE;
        return inorder(root);
    }
}

// Queue (level order traversal)
class Solution3 {
    class Info{
        TreeNode node;
        long lower;
        long upper;
        public Info(TreeNode n, long l, long u){
            node = n;
            lower = l;
            upper = u;
        }
    }
    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;
        Queue<Info> Q = new LinkedList<>();
        Q.add(new Info(root, Long.MIN_VALUE, Long.MAX_VALUE));
        while(!Q.isEmpty()){
            Info f = Q.remove();
            if(f.node.val <= f.lower || f.node.val >= f.upper)
                return false;
            if(f.node.left != null)
                Q.add(new Info(f.node.left, f.lower, f.node.val));
            if(f.node.right != null)
                Q.add(new Info(f.node.right, f.node.val, f.upper));
        }
        return true;
    }
}
