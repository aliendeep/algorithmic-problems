/*
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
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
    public void binaryTreePaths(TreeNode root, List<Integer> cur, List<String> result) {
        if(root == null)
            return;
        // leaf
        if(root.left == null && root.right == null){
            // add the leaf
            cur.add(root.val);
            
            StringBuffer r = new StringBuffer();
            for(int i=0; i<cur.size(); i++){
                if(i > 0)
                    r.append("->");
                r.append(Integer.toString(cur.get(i)));
            }
            result.add(r.toString());
            // remove the leaf
            cur.remove(cur.size()-1);
            return;
        }
        // left subtree
        cur.add(root.val);
        binaryTreePaths(root.left, cur, result);
        binaryTreePaths(root.right, cur, result);
        cur.remove(cur.size()-1);
   }

    public List<String> binaryTreePaths(TreeNode root) {
         List<String> result = new ArrayList<>();
         List<Integer> cur = new ArrayList<>(); 
         binaryTreePaths(root, cur, result);
         return result;
    }
}

class Solution2 {
    public void binaryTreePaths(TreeNode root, List<Integer> cur, List<String> result) {
        if(root == null)
            return;
        // leaf
        if(root.left == null && root.right == null){
            // add the leaf
            cur.add(root.val);
            
            StringBuffer r = new StringBuffer();
            for(int i=0; i<cur.size(); i++){
                if(i > 0)
                    r.append("->");
                r.append(Integer.toString(cur.get(i)));
            }
            result.add(r.toString());
            // remove the leaf
            cur.remove(cur.size()-1);
            return;
        }
        // left subtree
        cur.add(root.val);
        binaryTreePaths(root.left, cur, result);
        cur.remove(cur.size()-1);

        // right subtree
        cur.add(root.val);
        binaryTreePaths(root.right, cur, result);
        cur.remove(cur.size()-1);
   }

    public List<String> binaryTreePaths(TreeNode root) {
         List<String> result = new ArrayList<>();
         List<Integer> cur = new ArrayList<>(); 
         binaryTreePaths(root, cur, result);
         return result;
    }
}