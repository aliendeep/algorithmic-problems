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