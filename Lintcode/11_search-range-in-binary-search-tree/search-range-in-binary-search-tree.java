/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/search-range-in-binary-search-tree
@Language: Java
@Datetime: 16-11-09 20:09
*/

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
     * @param k1 and k2: range k1 to k2.
     * @return: Return all keys that k1<=key<=k2 in ascending order.
     */
    ArrayList<Integer> result;
    
    public void print(TreeNode node, int k1, int k2){
        if(node == null)
            return;
        
        // Sorted order        
        print(node.left, k1, k2);
        if(node.val >= k1 && node.val <= k2)
            result.add(node.val);
        print(node.right, k1, k2);
    }
    
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        result = new ArrayList<>();
        print(root, k1, k2);
        return result;
    }
}