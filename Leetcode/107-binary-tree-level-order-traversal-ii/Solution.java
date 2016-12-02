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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null)
            return Collections.EMPTY_LIST;
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);

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
                result.add(new ArrayList<>(temp));
                temp.clear();
                levCnt = Q.size();
            }
        }
        Collections.reverse(result);
        return result;
    }
}


// Without reversing
class Solution2 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null)
            return Collections.EMPTY_LIST;
            
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);

        int levCnt = Q.size();
        List<Integer> temp = new ArrayList<Integer>();
        while(!Q.isEmpty()){
            TreeNode t = Q.remove();
            levCnt--;
            temp.add(t.val);

            if(t.left != null)      Q.add(t.left);
            if(t.right != null)     Q.add(t.right);
                
            if(levCnt == 0){
                result.add(0, new ArrayList<>(temp));
                temp.clear();
                levCnt = Q.size();
            }
        }
        return result;
    }
}

// Alternative: DFS
class Solution3 {
    public void dfs(TreeNode node, int lev, List<List<Integer>> r) {
        if(node == null)
            return;
        
        if(r.size() == lev){
            r.add(0, new ArrayList<Integer>());
        }
        
        dfs(node.left, lev+1, r);
        dfs(node.right, lev+1, r);
        r.get(r.size() - lev - 1).add(node.val);
    }
    
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> r = new LinkedList<List<Integer>>();
        dfs(root, 0, r);
        return r;
    }
}
}