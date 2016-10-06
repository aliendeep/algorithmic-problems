/*
Given a non-negative number represented as a singly linked list of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// Alternative: Recursion
public class Solution {
    public int carry(ListNode node) {
        if(node == null)
            return 0;
        // last node (Add 1 to this node)
        if(node.next == null){
            node.val += 1;
            if(node.val == 10){
                node.val = 0;
                return 1;
            }
            return 0;
        }
        
        int c = carry(node.next);
        if(c == 0)
            return 0;
        node.val += 1;
        if(node.val == 10){
            node.val = 0;
            return 1;
        }
        return 0;
    }
    public ListNode plusOne(ListNode node) {
        if(node == null)
            return node;
        int c = carry(node);
        if(c == 0)
            return node;
        // insert new node at the head
        ListNode nh =  new ListNode(1);
        nh.next = node;
        return nh;
    }
    
}