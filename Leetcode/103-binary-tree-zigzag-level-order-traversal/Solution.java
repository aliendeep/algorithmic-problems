/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
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
// cleaner
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null)
            return Collections.EMPTY_LIST;
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);

        boolean right_to_left = false;
        while(!Q.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = Q.size();
            for(int i=0; i<size; ++i) {
                TreeNode t = Q.remove();
                temp.add(t.val);

                if(t.left != null)
                    Q.add(t.left);
                if(t.right != null)
                    Q.add(t.right);
            }
            if(right_to_left == true){
                Collections.reverse(temp);
            }
            result.add(new ArrayList<>(temp));
            right_to_left = !right_to_left;
        }
        return result;
    }
}

public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null)
            return Collections.EMPTY_LIST;
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);

        boolean right_to_left = false;
        int levCnt = Q.size();
        
        List<Integer> temp = new ArrayList<>();
        while(!Q.isEmpty()){
            TreeNode t = Q.remove();
            temp.add(t.val);
            levCnt--;

            if(t.left != null)
                Q.add(t.left);
            if(t.right != null)
                Q.add(t.right);
                
            if(levCnt == 0){
                if(right_to_left == true){
                    Collections.reverse(temp);
                }
                result.add(new ArrayList<>(temp));
                temp.clear();
                right_to_left = !right_to_left;
                levCnt = Q.size();
            }
        }
        return result;
    }
}

// Iterative (LinkedList)
public class Solution {
    void traverse(TreeNode node, int lev, List<List<Integer>> r){
        if(node == null)    return;
        if(lev == r.size()){
            r.add(new LinkedList<>());
        }    
        List<Integer> cur = r.get(lev);
        if(lev % 2 == 0)    cur.add(node.val);
        else                cur.add(0, node.val);
        traverse(node.left, lev+1, r);           
        traverse(node.right, lev+1, r);           
    }
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>();
        traverse(root, 0, r);
        return r;
    }
}
