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