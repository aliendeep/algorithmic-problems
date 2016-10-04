/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
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
 
/* TestCase:
[6, 2, 7, 1, 3, 5, 8]
[6, 7, 2, 3, 8, 5, 1]
*/
// Note: 1 is the root in postorder traversal, then (3, 5, 8) belong to the right subtree. 
public class Solution {
    // inorderStart and inorderEnd needed to compute the subtree size
    public TreeNode buildTree(Map<Integer, Integer> inorderMap, int inStart, int inEnd, 
                                int[] postorder, int postStart, int postEnd) {

        if(postStart > postEnd)
            return null;
        // last element is thr root
        int rootVal = postorder[postEnd];
        // Find that node's index in the inorder sequenece
        int rootIndex = inorderMap.get(rootVal);
        // Calculate rightSubTreeSize
        int rightSubTreeSize = inEnd - rootIndex;  
        
        // Create the node
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(inorderMap, inStart, rootIndex-1, postorder, postStart, postEnd - rightSubTreeSize - 1);
        root.right = buildTree(inorderMap, rootIndex+1, inEnd, postorder, postEnd - rightSubTreeSize, postEnd-1);
        return root;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Create index of all nodes in the in order traversal for faster lookup
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for(int i=0; i<inorder.length; i++){
            inorderMap.put(inorder[i], i);
        }
        return buildTree(inorderMap, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }
}