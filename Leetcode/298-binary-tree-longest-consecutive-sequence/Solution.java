/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in 
the tree along the parent-child connections. The longest consecutive path need 
to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
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

// Time: O(n)
// Space: O(n)
public class Solution {
    int result = 0;
    public int lcs(TreeNode root) {
        if(root == null) 
            return 0;

        int left = lcs(root.left);
        int right = lcs(root.right);
        
        int cnt = 1;
        if (root.left != null && root.left.val == root.val + 1) {
            cnt = Math.max(cnt, left+1);
        }
        if (root.right != null && root.right.val == root.val + 1) {
            cnt = Math.max(cnt, right+1);
        }
        result = Math.max(result, cnt);
        return cnt;
    }
    
    public int longestConsecutive(TreeNode root) {
        if (root == null) 
            return 0;
        lcs(root);
        return result;
    }    
}

// cleaner
// https://leetcode.com/articles/binary-tree-longest-consecutive-sequence/
public class Solution2 {
    public int longestConsecutive(TreeNode node, int parent, int length) {
        if(node == null)
            return length;
        if(parent != -1 && parent + 1 == node.val)
            length++;
        else
            // reset length
            length = 1;
        return Math.max(length, Math.max(longestConsecutive(node.left, node.val, length), 
                                         longestConsecutive(node.right, node.val, length)));
    }
    
    public int longestConsecutive(TreeNode root) {
        return longestConsecutive(root, -1, 0);
    }
}

class Solution3 {
    public int longestConsecutive(TreeNode root) {
        if(root == null)
            return 0;
        return Math.max(helper(root.left, 1, root.val), helper(root.right, 1, root.val));
    }
    
    public int helper(TreeNode node, int len, int parentVal){
        if(node == null)
            return len;
        // increasing
        if(node.val - parentVal == 1)
            len++;
        // reset the length
        else
            len = 1;
        
        int left = helper(node.left, len, node.val);
        int right = helper(node.right, len, node.val);
        return Math.max(Math.max(left, right), len);
    }
}