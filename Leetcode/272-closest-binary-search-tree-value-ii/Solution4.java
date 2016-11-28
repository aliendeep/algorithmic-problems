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
    public int getPred(Deque<TreeNode> pred){
        TreeNode r = pred.pop();
        TreeNode cur = r.left;
        while(cur != null){
            pred.push(cur);
            cur = cur.right;
        }
        return r.val;
    }
    public int getSucc(Deque<TreeNode> succ){
        TreeNode r = succ.pop();
        TreeNode cur = r.right;
        while(cur != null){
            succ.push(cur);
            cur = cur.left;
        }
        return r.val;
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<TreeNode> succ = new LinkedList<>();
        Deque<TreeNode> pred = new LinkedList<>();

        TreeNode cur = root;
        // logn
        while(cur != null){
            if(target <= cur.val){
                // successor - larger than target
                succ.push(cur);
                cur = cur.left;
            }
            // target > cur.val
            else{
                pred.push(cur);
                cur = cur.right;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        while(k-- > 0){
            if(succ.isEmpty() && pred.isEmpty())
                break;
            else if(succ.isEmpty()){
                result.add(getPred(pred));
            }
            else if(pred.isEmpty()){
                result.add(getSucc(succ));
            }
            else if(Math.abs(succ.peekFirst().val - target) > Math.abs(pred.peekFirst().val - target)){
                result.add(getPred(pred));
            } 
            else{
                result.add(getSucc(succ));
            }
        }
        return result;
    }
}