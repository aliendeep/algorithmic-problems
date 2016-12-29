/*

Given inorder and postorder traversal of a tree, construct the binary tree.

 Note: You may assume that duplicates do not exist in the tree. 
Example :

Input : 
        Inorder : [2, 1, 3]
        Postorder : [2, 3, 1]

Return : 
            1
           / \
          2   3

*/
/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    Map<Integer, Integer> inorderMap;
    ArrayList<Integer> postorder;
    public TreeNode buildTree(int inStart, int inEnd, int postStart, int postEnd) {
        if(postStart > postEnd)
            return null;
        // last element is thr root
        int rootVal = postorder.get(postEnd);
        // Find that node's index in the inorder sequenece
        int rootIndex = inorderMap.get(rootVal);
        // Calculate rightSubTreeSize
        int rightSubTreeSize = inEnd - rootIndex;  
        // Create the node
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(inStart, rootIndex-1, postStart, postEnd - rightSubTreeSize - 1);
        root.right = buildTree(rootIndex + 1, inEnd, postEnd - rightSubTreeSize, postEnd-1);
        return root;
    }
    
  public TreeNode buildTree(ArrayList<Integer> inorder, ArrayList<Integer> postorder) {
      this.postorder = postorder;
        // Create index of all nodes in the in order traversal for faster lookup
        inorderMap = new HashMap<>();
        for(int i=0; i<inorder.size(); i++){
            inorderMap.put(inorder.get(i), i);
        }
        return buildTree(0, inorder.size()-1, 0, postorder.size()-1);
  }
}
