/*
Given an array of numbers, verify whether it is the correct preorder traversal 
sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
*/

import java.util.*;

// O(nlogn)
public class Solution {
    public boolean preorderDivideSubTree(int[] preorder, int start, int end, int min, int max) {
        if(start > end)
            return true;
            
        int root = preorder[start];
        if(root < min || root > max)
            return false;
        // Find the next largest number greater than root
        int i = start;
        for(i=start+1; i<=end; i++){
            if(preorder[i] > root)
                break;
        }

        if(!preorderDivideSubTree(preorder, start+1, i-1, min, root))
            return false;

        if(!preorderDivideSubTree(preorder, i, end, root, max))
            return false;
        
        return true;
    }
    public boolean verifyPreorder(int[] preorder) {
        return preorderDivideSubTree(preorder, 0, preorder.length-1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public static void main(String[] args){
        Solution ob = new Solution();
        int[] preorder = {10, 7, 16, 8, 15, 11, 18};
        System.out.println(ob.verifyPreorder(preorder));
        int[] a = {10, 7, 6, 8, 15, 11, 18};
        System.out.println(ob.verifyPreorder(a));
    }    
}

// O(n^2)
class Solution2 {
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

class Solution3 {
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

class Solution4 {
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