/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.

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
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}

public class Solution{
    /*
        All values are unique in the BST
        Successor of a node is the smallest node that is larger than the 
        given node
        Approach: Similar to binary search in a sorted list
    */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        TreeNode cur = root;
        while(cur != null){
            if(p.val < cur.val){
                successor = cur;
                cur = cur.left;
            }    
            else
                cur = cur.right;
        }
        return successor;
    }    
}

class Solution2 {
    public TreeNode findMin(TreeNode node){
        if(node == null)
            return null;

        while(node.left != null)
            node = node.left;
        return node;
    }
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null)
            return root;
        TreeNode successor = null;
        while(root != null){
            // p exists in the right subtree
            if(root.val < p.val){
                root = root.right;
            }
            // p exists in the left subtree
            else if(root.val > p.val){
                // Only update successor value if we go the left side
                successor = root;
                root = root.left;
            }
            else{
                // found p
                if(root.right != null){
                    // find the minimum value of the left most part of the this right node
                    successor = findMin(root.right);
                }
                break;
            }
        }
        return successor;
    }
}

class Predecessor{
    /*
        Predecessor of a node is the largest node that is smaller than the given 
        node
        Approach: Similar to binary search in a sorted list
    */
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        TreeNode predecessor = null;
        TreeNode cur = root;
        while(cur != null){
            if(p.val > cur.val){
                predecessor = cur;
                cur = cur.right;
            }    
            else
                cur = cur.left;
        }
        return predecessor;
    }    
}
