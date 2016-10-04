/*
Given preorder and inorder traversal of a tree, construct the binary tree.

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
/*
Testcase:
[1, 2, 6, 7, 5, 3, 8]
[6, 2, 7, 1, 3, 5, 8]
*/
public class Solution {
    // inorderStart and inorderEnd needed to compute the subtree size
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, 
                                int inorderStart, int inorderEnd, Map<Integer, Integer> inorderMap) {
        if(preStart > preEnd)
            return null;
        int rootVal = preorder[preStart];
        // Find that node's index in the inorder sequenece
        int rootIndex = inorderMap.get(rootVal);
        int leftSubTreeSize = rootIndex - inorderStart;  
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(preorder, preStart+1, preStart+leftSubTreeSize, inorderStart, rootIndex-1, inorderMap);
        root.right = buildTree(preorder, preStart+leftSubTreeSize+1, preEnd, rootIndex+1, inorderEnd, inorderMap);
        return root;
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Create index of all nodes in the in order traversal for faster lookup
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for(int i=0; i<inorder.length; i++){
            inorderMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length-1, 0, inorder.length-1, inorderMap);
    }
}