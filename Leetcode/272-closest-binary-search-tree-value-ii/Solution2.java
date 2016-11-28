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