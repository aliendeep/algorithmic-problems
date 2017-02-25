/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
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
    public void getPathSum(TreeNode root, int target, int curSum, List<Integer> curList, List<List<Integer>> result) {
        if(root == null)
            return;
            
        curSum += root.val;
        curList.add(root.val);
        
        // root to leaf path
        if(curSum == target && root.left == null && root.right == null){
            result.add(new ArrayList<>(curList));
            curList.remove(curList.size()-1);
            curSum -= root.val;
            return;
        }
        getPathSum(root.left, target, curSum, curList, result);
        getPathSum(root.right, target, curSum, curList, result);
        // retrace
        curSum -= root.val;
        curList.remove(curList.size()-1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null)
            return Collections.EMPTY_LIST;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        getPathSum(root, sum, 0, curList, result);
        return result;
    }
}

public class Solution {
    List<List<Integer>> result;
    int target;
    
    public void getPathSum(TreeNode node, int curSum, List<Integer> cur) {
        if(node == null)    return;

        curSum += node.val;
        cur.add(node.val);
        if(node.left == null && node.right == null && curSum == target){
            result.add(new ArrayList<>(cur));
        }
        else{
            getPathSum(node.left, curSum, cur);
            getPathSum(node.right, curSum, cur);
        }
        curSum -= node.val;
        cur.remove(cur.size()-1);
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        result = new ArrayList<>();
        if(root == null)
            return result;
        target = sum;
        getPathSum(root, 0, new ArrayList<>());
        return result;
    }
}