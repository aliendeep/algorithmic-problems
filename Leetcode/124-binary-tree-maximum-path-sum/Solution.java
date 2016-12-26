/*
Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting 
node to any node in the tree along the parent-child connections. The path must 
contain at least one node and does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
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
    class PathInfo{
       // best path that ends at root
       int bestPathSum;
       // ultimate result
       int bestAnswer;
       public PathInfo(int i, int j){
           bestPathSum = i;
           bestAnswer =  j;
       }
    };
    public PathInfo getMaxPath(TreeNode root){
        if(root == null)
            return new PathInfo(Integer.MIN_VALUE, Integer.MIN_VALUE);
        PathInfo left = getMaxPath(root.left);    
        PathInfo right = getMaxPath(root.right);    
        // best path that ends at root
        // Maximum of (left height and right height) + value of the node;
        int bestPathEnd = Math.max(Math.max(left.bestPathSum, right.bestPathSum), 0) + root.val; 
        // Maximum of (MaxPath in left subtree, MaxPath in right subtree, Path that goes through root) 
        int bestAnswer = Math.max(Math.max(left.bestAnswer, right.bestAnswer), 
                         Math.max(left.bestPathSum, 0) + root.val + Math.max(right.bestPathSum, 0));
        return new PathInfo(bestPathEnd, bestAnswer);
    }
    
    public int maxPathSum(TreeNode root) {
        PathInfo result = getMaxPath(root);
        return result.bestAnswer;
    }
}

class Solution2 {
    int maxPath;
    public int getMaxPath(TreeNode node){
        if(node == null)
            return 0;
        // max path starting from left node
        int leftPath = Math.max(0, getMaxPath(node.left));
        // max path starting from right node
        int rightPath = Math.max(0, getMaxPath(node.right));
                                    // path that goes through root
        maxPath = Math.max(maxPath, leftPath + node.val + rightPath);
        return Math.max(leftPath, rightPath) + node.val;
    }
    public int maxPathSum(TreeNode root) {
        maxPath = Integer.MIN_VALUE;
        getMaxPath(root);
        return maxPath;
    }
}
