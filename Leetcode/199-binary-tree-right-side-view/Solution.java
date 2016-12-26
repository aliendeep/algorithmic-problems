/*
Given a binary tree, imagine yourself standing on the right side of it, return 
the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
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
// Print the last node on each level order traversal 
// Time complexity: O(n)
// Space complexity: O(n)
public class Solution {
    public void rightSide(TreeNode node, int lev, List<Integer> r){
        if(node == null)
            return;
        if(lev == r.size()){
            r.add(node.val);
        }
        rightSide(node.right, lev+1, r);
        rightSide(node.left, lev+1, r);
    }
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        rightSide(root, 0, r);
        return r;
    }
}

// Iterative
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        if(root == null)
            return r;
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        while(!Q.isEmpty()){
            int size = Q.size();
            for(int i=0; i<size; ++i){
                TreeNode front = Q.remove();
                if(i == 0)      
                    r.add(front.val);
                if(front.right != null)
                    Q.add(front.right);
                if(front.left != null)
                    Q.add(front.left);
            }
        }
        return r;
    }
}
