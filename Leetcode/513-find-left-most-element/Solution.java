/*
Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2: 
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.
*/
public class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        
        TreeNode result = null;
        while(!Q.isEmpty()){
            int size = Q.size();
            for(int i=0; i<size; ++i){
                TreeNode f = Q.remove();
                if(i == 0)
                    result = f;
                if(f.left != null)
                    Q.add(f.left);
                if(f.right != null)
                    Q.add(f.right);
            }
        }
        return result == null ? -1 : result.val;
    }
}