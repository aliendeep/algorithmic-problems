/*
You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)    return result;
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        while(!Q.isEmpty()){
            int size = Q.size();
            int max = Integer.MIN_VALUE;
            for(int i=0; i<size; ++i){
                TreeNode f = Q.remove();
                max = Math.max(max, f.val);
                if(f.left != null)
                    Q.add(f.left);
                if(f.right != null)
                    Q.add(f.right);
            }
            result.add(max);
        }
        return result;        
    }
}

// Recursive
public class Solution {
    List<Integer> result;
    
    void findLargestLev(TreeNode node, int depth){
        if(node == null)    return;
        if(result.size() == depth){
            result.add(node.val);
        }    
        int max = Math.max(result.get(depth), node.val);
        result.set(depth, max);
        
        findLargestLev(node.left, depth + 1);
        findLargestLev(node.right, depth + 1);
    }
    
    public List<Integer> largestValues(TreeNode root) {
        result = new ArrayList<>();
        if(root == null)    return result;
        findLargestLev(root, 0);
        return result;        
    }
}
