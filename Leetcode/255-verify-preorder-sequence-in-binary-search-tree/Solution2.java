/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
*/

public class Solution {
    // Time: O(n)
    // Use the given array as a proxy for stack
    // O(1) Extra space
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        int i = -1;
        for(int pre : preorder){
            if(pre < low)
                return false;
            while(i >= 0 && preorder[i] < pre){
                low = preorder[i];
                i--;
            }
            preorder[++i] = pre;
        }
        return true;
    }
}