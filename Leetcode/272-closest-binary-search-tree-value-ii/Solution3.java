/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// O(n) time && O(k) space
public class Solution {
    // Sorted order
    public void inorder(TreeNode node, double target, int k, LinkedList<Integer> result){
        if(node == null)
            return;
        
        inorder(node.left, target, k, result);
        if(result.size() == k){
            if(Math.abs(node.val - target) > Math.abs(result.getFirst() - target))
                return;
            // Otherwise, remove the first node
            result.removeFirst();
        }
        // Add the current node
        result.add(node.val);
        inorder(node.right, target, k, result);
    }
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> r = new LinkedList<Integer>();
        inorder(root, target, k, r);
        return r;
    }
}