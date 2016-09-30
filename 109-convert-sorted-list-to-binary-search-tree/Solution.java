/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
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
public class Solution {
    private ListNode node;
    
    int getLength(ListNode n){
        int l = 0;
        while(n != null){
            l++;
            n = n.next;
        }
        return l;
    }
    
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)
            return null;
        this.node = head;  
        int n = getLength(head);
        return inOrderHelper(0, n-1);
    }
    
    public TreeNode inOrderHelper(int start, int end){
        if(start > end){
            return null;
        }
        
        int mid = (start + end)/2;
        TreeNode left = inOrderHelper(start, mid-1);
        
        // Create root
        TreeNode root = new TreeNode(node.val);
        root.left = left;
        node = node.next;
        
        TreeNode right = inOrderHelper(mid+1, end);
        root.right = right;

        return root;
    }
}