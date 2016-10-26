/*
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
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
// Alternative
// Time complexity: O(n)
// Space Complexity: O(height)
public class Solution {
    public int pathSumHelper(TreeNode root, int target, int curSum, Map<Integer, Integer> map) {
        if(root == null)
            return 0;

        curSum += root.val;
        int sum = curSum - target;
        int npaths = map.getOrDefault(sum, 0);
        
        if(curSum == target)
            npaths++;
        
        // increment path Count
        if(!map.containsKey(curSum)){
            map.put(curSum, 1);
        }
        else{
            map.put(curSum, map.get(curSum) + 1);
        }
        npaths += pathSumHelper(root.left, target, curSum, map);
        npaths += pathSumHelper(root.right, target, curSum, map);
        // decrement path Count
        map.put(curSum, map.get(curSum) - 1);
        if(map.get(curSum) == 0)
            map.remove(curSum);
            
        return npaths;
    }
    
    public int pathSum(TreeNode root, int sum) {
       // <Number, Count>
       Map<Integer, Integer> map = new HashMap<>();
       return pathSumHelper(root, sum, 0, map);
    }
}