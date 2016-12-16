/*
The thief has found himself a new place for his thievery again. There is only 
one entrance to this area, called the "root." Besides the root, each house has 
one and only one parent house. After a tour, the smart thief realized that "all 
houses in this place forms a binary tree". It will automatically contact the 
police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting 
the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.
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
// Testcase: [4,1,null,2,null,3]
public class Solution {
    class Profit{
        int profitNode;
        int profitChildren;
        public Profit(int n, int c){
            profitNode = n;
            profitChildren = c;
        }
    }
    
    public int rob(TreeNode root) {
        Profit p = robProfit(root);
        return Math.max(p.profitNode, p.profitChildren);
    }

    public Profit robProfit(TreeNode root){
        if(root == null)
            return new Profit(0, 0);

        // Leaf
        if(root.left == null && root.right == null)
            return new Profit(root.val, 0);
        
        Profit left = robProfit(root.left);
        Profit right = robProfit(root.right);

        // proft including root, root's direct children can't be included
        int profitIncludingRoot = root.val + left.profitChildren + right.profitChildren;
        int profitExcludingRoot = Math.max(left.profitNode, left.profitChildren) + Math.max(right.profitNode, right.profitChildren); 
        return new Profit(profitIncludingRoot, profitExcludingRoot);
    }
}

// Cleaner
class Solution2 {
    public int[] robHelper(TreeNode root) {
        if(root == null)
            return new int[2];
        
        int[] left = robHelper(root.left);
        int[] right = robHelper(root.right);
        int[] result = new int[2];
        // include root
        result[0] = root.val + left[1] + right[1];
        // exclude root
        result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return result;
    }
    
    public int rob(TreeNode root) {
        int[] r = robHelper(root);
        return Math.max(r[0], r[1]);        
    }
}

class Solution3 {
    // Alternative: Bruteforce
    public int rob(TreeNode root) {
        if(root == null)
            return 0;
        
        int grandChildrenSum = 0;
        if(root.left != null)
            grandChildrenSum = rob(root.left.left) + rob(root.left.right);
        if(root.right != null)
            grandChildrenSum += rob(root.right.left) + rob(root.right.right);
        // Rob root or child
        return Math.max(root.val + grandChildrenSum, rob(root.left) + rob(root.right));
    }
}


class Solution4 {
    // Alternative: Bruteforce + Map
    public int rob(TreeNode root, Map<TreeNode, Integer> map) {
        if(root == null)
            return 0;
        
        if(map.containsKey(root))
            return map.get(root);
            
        int grandChildrenSum = 0;
        if(root.left != null)
            grandChildrenSum = rob(root.left.left, map) + rob(root.left.right, map);
        if(root.right != null)
            grandChildrenSum += rob(root.right.left, map) + rob(root.right.right, map);
        // Rob root or child
        int val = Math.max(root.val + grandChildrenSum, rob(root.left, map) + rob(root.right, map));
        map.put(root, val);
        return val;
    }
    public int rob(TreeNode root){
        return rob(root, new HashMap<>());
    }
}