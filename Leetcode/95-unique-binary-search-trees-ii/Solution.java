/*
Given an integer n, generate all structurally unique BST's (binary search trees) 
that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

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
    // Time complexity: Catalan Number (2n)! / (n! (n+1)!)
    // C(n) = /sum_{i=1_n} C(i)*C(n-1)
    // inclusive
    public List<TreeNode> gen(int min, int max) {
        List<TreeNode> result = new ArrayList<>();
        if(min > max){
            result.add(null);
            return result;
        }
        for(int root=min; root<=max; ++root){
            List<TreeNode> leftSubtrees = gen(min, root-1);
            List<TreeNode> rightSubtrees = gen(root+1, max);
            // Generate all combinations of left and right subtrees
            for(TreeNode left : leftSubtrees){
                for(TreeNode right : rightSubtrees){
                    TreeNode r = new TreeNode(root);
                    r.left = left;
                    r.right = right;
                    result.add(r);
                }
            }
        }
        return result;
    }
    public List<TreeNode> generateTrees(int n){
        if(n == 0)      return Collections.EMPTY_LIST;
        return gen(1, n);
    }
}
