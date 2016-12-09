/*
Given a non-empty binary search tree and a target value, find k values in the 
BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are 
closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime 
(where n = total nodes)?

Hint:
Consider implement these two helper functions:
- getPredecessor(N), which returns the next smaller node to N.
- getSuccessor(N), which returns the next larger node to N.
Try to assume that each node has a parent pointer, it makes the problem much easier.
Without parent pointer we just need to keep track of the path from the root 
to the current node using a stack.
You would need two stacks to track the path in finding predecessor and successor 
node separately.
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
/*
Sample Input:
[1]
0.000000
1
[15, 4, 19, 3, 6, 17, 22]
15.000000
4
[15, 4, 19, 3, 6, 17, 22]
12.000000
3

Sample Output:
[1]
[15,17,19,22]
[15,17,6]
*/
 
public class Solution {
    // Inorder traversal gives us sorted predecessors, whereas reverse-inorder 
    // traversal gives us sorted successors.
    public void inorder(TreeNode root, boolean inorderFlag, double target, Deque<Integer> stk){
        if(root == null)        return;
        inorder(inorderFlag ? root.left : root.right, inorderFlag, target, stk);
        
        if((inorderFlag && root.val > target) || (!inorderFlag && root.val <= target))
            return;

        stk.addFirst(root.val);
        inorder(inorderFlag ? root.right : root.left, inorderFlag, target, stk);
    }    

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<Integer> s1, s2;
        s1 = new LinkedList<>();
        s2 = new LinkedList<>();
        // inorder traversal - predecessor
        inorder(root, true, target, s1);
        // reverse inorder traversal - successor
        inorder(root, false, target, s2);
        
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<k; i++){
            if(!s1.isEmpty() && !s2.isEmpty()){
                int t1 = s1.peekFirst();
                int t2 = s2.peekFirst();
                if(Math.abs(t1 - target) < Math.abs(t2 - target)){
                    result.add(t1);
                    s1.removeFirst();
                }
                else{
                    result.add(t2);
                    s2.removeFirst();
                }
            }
            else if(!s1.isEmpty()){
                result.add(s1.removeFirst());
            }
            else if(!s2.isEmpty()){
                result.add(s2.removeFirst());
            }
        }
        return result;
    }
}

class Solution2 {
    PriorityQueue<Integer> maxHeap;
    public void getClosest(TreeNode node, double target, int k){
        if(node == null)
            return;
            
        if(maxHeap.size() < k){
            maxHeap.add(node.val);
        } 
        else{
           if(Math.abs(maxHeap.peek() - target) > Math.abs(node.val - target)){
               maxHeap.poll();
               maxHeap.add(node.val);
           }   
        }
        getClosest(node.left, target, k);
        getClosest(node.right, target, k);
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>(){
           @Override
           public int compare(Integer a, Integer b){
               return Double.compare(Math.abs(b - target), Math.abs(a - target));
           }
        });
        getClosest(root, target, k);
        List<Integer> result = new ArrayList<>(maxHeap);
        return result;
    }
}

// O(n) time && O(k) space
class Solution3 {
    // Sorted order
    public void inorder(TreeNode node, double target, int k, LinkedList<Integer> result){
        if(node == null)
            return;
        
        inorder(node.left, target, k, result);
        if(result.size() == k){
            if(Math.abs(node.val - target) > Math.abs(result.getFirst() - target))
                return;
            // Otherwise, remove the first node
            result.removeFirst();
        }
        // Add the current node
        result.add(node.val);
        inorder(node.right, target, k, result);
    }
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> r = new LinkedList<Integer>();
        inorder(root, target, k, r);
        return r;
    }
}

class Solution4 {
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