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
    // Alternative
    public void getPathSum(TreeNode root, int sum, List<Integer> curList, List<List<Integer>> result) {
        // root to leaf path
        if(sum == 0 && root.left == null && root.right == null){
            result.add(new ArrayList<>(curList));
            return;
        }
        // left subtree
        if(root.left != null){
            curList.add(root.left.val);
            sum -= root.left.val;
            getPathSum(root.left, sum, curList, result);
            sum += root.left.val;
            curList.remove(curList.size()-1);
        }
        // right subtree
        if(root.right != null){
            curList.add(root.right.val);
            sum -= root.right.val;
            getPathSum(root.right, sum, curList, result);
            sum += root.right.val;
            curList.remove(curList.size()-1);
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null)
            return Collections.EMPTY_LIST;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        curList.add(root.val);        
        getPathSum(root, sum - root.val, curList, result);
        return result;
    }
}