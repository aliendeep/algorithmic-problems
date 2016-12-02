/*
Given a binary tree, return the level order traversal of its nodes’ values. (ie, from left to right, level by level).

Example :
Given binary tree

    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]
Also think about a version of the question where you are asked to do a level order traversal of the tree when depth of the tree is much greater than number of nodes on a level.
*/

/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
  public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> r = new ArrayList<>();

        if(root == null)    return r;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int cntLevel = q.size();
        ArrayList<Integer> t = new ArrayList<Integer>();        
        while(!q.isEmpty()){
            TreeNode n = q.remove();
            t.add(n.val);

            cntLevel--;
            if(n.left != null)      q.add(n.left);
            if(n.right != null)     q.add(n.right);

            if(cntLevel == 0){
                cntLevel = q.size();                
                r.add(t);
                t = new ArrayList<Integer>();      
            }
        }
        return r;
  }
}

/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */ 

// recursive
class Solution2{
    public void dfs(TreeNode node, int lev, ArrayList<ArrayList<Integer>> r) {
        if(node == null)
            return;
        
        if(r.size() == lev){
            r.add(new ArrayList<>());
        }
        
        r.get(lev).add(node.val);
        dfs(node.left, lev+1, r);
        dfs(node.right, lev+1, r);
    }
    
    public ArrayList<ArrayList<Integer>>  levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> r = new ArrayList<>();
        dfs(root, 0, r);
        return r;
    }
}
