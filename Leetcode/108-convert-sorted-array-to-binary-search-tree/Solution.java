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
    public TreeNode sortedArrayToBST(int[] nums, int l, int h){
        if(l > h)
            return null;
        int mid = (l+h)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, l, mid-1);
        root.right = sortedArrayToBST(nums, mid+1, h);
        return root;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length-1);
    }
}