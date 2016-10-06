/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
*/

public class Solution {
    // Time: O(n)
    // Stack
    public boolean verifyPreorder(int[] preorder) {
        Deque<Integer> stk = new LinkedList<>();
        int root = Integer.MIN_VALUE;
        for(int pre : preorder){
            if(pre < root)
                return false;
            //  Remove elements from stack while preorder[i] is greater then stack top.    
            while(!stk.isEmpty() && stk.peekFirst() < pre){
                root = stk.removeFirst();
            }
            stk.addFirst(pre);
        }
        return true;
    }
}