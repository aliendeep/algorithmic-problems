/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling 
(a left node that shares the same parent node) or 
empty, flip it upside down and turn it into a tree where the original right nodes 
turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  

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
    // Recursive, Similar to Linked List Reverse
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null)
            return root;
        // Checking root.right is not necessary as all the right nodes are either 
        // leaf nodes with a sibling or empty
        if(root == null || root.left == null)    
            return root;
        
        TreeNode rest = upsideDownBinaryTree(root.left);
        root.left.left = root.right;        
        root.left.right = root;
        
        root.left = null;
        root.right = null;
        return rest;
    }
}

// Recursive : draw picture
public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null)    return root;
        if(root.left == null && root.right == null)     return root;
        
        TreeNode prev = null, next = null, temp = null;
        TreeNode cur = root;
        while(cur != null){
            next = cur.left;

            cur.left = temp;
            temp = cur.right;
            cur.right = prev;
            
            if(prev != null)     System.out.println("Prev " + prev.val);
            if(cur != null)      System.out.println("Cur " + cur.val);
            if(temp != null)     System.out.println("temp " + temp.val);
            
            prev = cur;
            cur = next;            
        }
        return prev;
    }
}
