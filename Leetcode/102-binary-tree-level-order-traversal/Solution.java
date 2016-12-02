/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>();

        if(root == null)    return r;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int cntLevel = q.size();
        
        List<Integer> t = new ArrayList<Integer>();        
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

// Alternative: DFS
class Solution2 {
    public void dfs(TreeNode node, int lev, List<List<Integer>> r) {
        if(node == null)
            return;
        
        if(r.size() == lev){
            r.add(new ArrayList<>());
        }
        
        r.get(lev).add(node.val);
        dfs(node.left, lev+1, r);
        dfs(node.right, lev+1, r);
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>();
        dfs(root, 0, r);
        return r;
    }
}