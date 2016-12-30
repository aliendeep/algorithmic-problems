/*
Given a binary search tree, write a function to find the kth smallest 
element in the tree.

Example :

Input : 
  2
 / \
1   3

and k = 2

Return : 2

As 2 is the second smallest element in the tree.
 NOTE : You may assume 1 <= k <= Total number of nodes in BST 
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
// in order traversal
public class Solution {
    int cnt;
    int result;
    
    public void kth(TreeNode node, int k) {
        if(node == null)
            return;
        kth(node.left, k);
        cnt++;
        if(cnt == k){
            result = node.val;
            return;
        }
        kth(node.right, k);
    }

    public int kthsmallest(TreeNode root, int k) {
        cnt = 0;
        kth(root, k);
        return result;
    }
}
