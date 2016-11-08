/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
        if(root == null){
            root = node;
            return root;
        }
        
        if(root.val > node.val){
            root.left = insertNode(root.left, node);
        }
        
        else if(root.val < node.val){
            root.right = insertNode(root.right, node);
        }
        return root;
    }
}