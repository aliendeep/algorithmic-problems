/*
Given a binary tree with duplicates. You have to find all the mode(s) in given 
binary tree.

For example:
Given binary tree [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.
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
    // number, count
    Map<Integer, Integer> map;
    int modeFreq;
    public void traverse(TreeNode node){
        if(node == null)    return;
        int cnt = map.getOrDefault(node.val, 0) + 1;
        map.put(node.val, cnt);
        modeFreq = Math.max(modeFreq, cnt);
        traverse(node.left);
        traverse(node.right);
    }
    
    public int[] findMode(TreeNode root) {
        if(root == null)    return new int[0];
        map = new HashMap<>();
        modeFreq = 0;
        traverse(root);
        
        List<Integer> r = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == modeFreq)
                r.add(entry.getKey());
        }
        int[] result = new int[r.size()];
        int i = 0;
        for(int t : r)
            result[i++] = t;
        return result;
    }
}
