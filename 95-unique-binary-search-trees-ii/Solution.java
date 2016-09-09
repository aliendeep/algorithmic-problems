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
    public List<TreeNode> generateTree(int start, int end) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        if(start > end){
            result.add(null);
            return result;
        }
        for(int i=start; i<=end; i++){
            List<TreeNode> leftSubtree = generateTree(start, i-1);
            List<TreeNode> rightSubtree = generateTree(i+1, end);
            for(TreeNode leftNode: leftSubtree){
                for(TreeNode rightNode: rightSubtree){
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    result.add(root);
                }
            }
        }
        return result;
    }
    
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> r = new ArrayList<TreeNode>();
        if(n == 0)
            return r;
            
        return generateTree(1, n);
    }
}