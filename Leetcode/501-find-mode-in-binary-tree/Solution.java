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
// Extra space
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

/*
Given a binary search tree (BST) with duplicates, find all the mode(s) (the most 
frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to 
the node's key.
The right subtree of a node contains only nodes with keys greater than or equal 
to the node's key.
Both the left and right subtrees must also be binary search trees.
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the 
implicit stack space incurred due to recursion does not count).
*/
// BST
public class Solution {
    int maxCnt;
    int prev;
    int curCnt;
    List<Integer> result;
    public void inorder(TreeNode node) {
        if(node == null)    return;
        inorder(node.left);
        if(prev != node.val){
            prev = node.val;
            curCnt = 0;
        }
        curCnt++;
        
        if(curCnt > maxCnt){
            maxCnt = curCnt;
            result.clear();
            result.add(node.val);
        }
        else if(curCnt == maxCnt){
            result.add(node.val);
        }
        inorder(node.right);
    }

    public int[] findMode(TreeNode root) {
        result = new ArrayList<>();
        inorder(root);
        int[] r = new int[result.size()];
        int i = 0;
        for(int x : result)
            r[i++] = x;
        return r;
    }
}