/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Returns [4, 5, 3], [2], [1].

Explanation:
1. Removing the leaves [4, 5, 3] would result in this tree:

          1
         / 
        2          
2. Now removing the leaf [2] would result in this tree:

          1          
3. Now removing the leaf [1] would result in the empty tree:

          []         
Returns [4, 5, 3], [2], [1].
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
import java.util.*;

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
// Extra: Space: O(h)
public class Solution {
    // (height, node value)
    List<List<Integer>> r;
    
    // O(h) space, O(n) time
    public int computeHeight(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            if(r.size() == 0){
                List<Integer> l = new ArrayList<>();
                l.add(root.val);
                r.add(l);
            }
            else{
                r.get(0).add(root.val);
            }
            return 1;
        }
        int lH = computeHeight(root.left);
        int rH = computeHeight(root.right);
        int height = Math.max(lH, rH) + 1;
        if(r.size() == height - 1){
            List<Integer> l = new ArrayList<>();
            l.add(root.val);
            r.add(l);
        }
        else{
            r.get(height-1).add(root.val);
        }
        return height;
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        r = new ArrayList<>();
        if(root == null)
            return r;
        computeHeight(root);
        return r;
    }
}