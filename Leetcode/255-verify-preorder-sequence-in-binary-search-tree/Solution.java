/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?

*/

public class Solution {
    // Time: O(n^2)
    public boolean preorderDivideSubTree(int[] preorder, int start, int end) {
        if(start > end)
            return true;
            
        int root = preorder[start];
        // Find the next largest number greater than root
        int i = start;
        for(i=start+1; i<=end; i++){
            if(preorder[i] > root)
                break;
        }
        // All values after the above found greater value should be greater than current node.
        for(int j=i+1; j<=end; j++){
            if(preorder[j] < root)
                return false;
        }

        boolean l = preorderDivideSubTree(preorder, start+1, i-1); 
        if(!l)        return false;

        boolean r = preorderDivideSubTree(preorder, i, end); 
        if(!r)        return false;
        
        return true;
    }
    public boolean verifyPreorder(int[] preorder) {
        return preorderDivideSubTree(preorder, 0, preorder.length-1);
    }
}