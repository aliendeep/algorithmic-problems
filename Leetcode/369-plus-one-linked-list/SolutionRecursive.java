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
class Solution {
    public int getCarry(ListNode node) {
        if(node == null)
            return 1;
        int c = getCarry(node.next);
        if(c == 0)      return 0;
        // carry
        node.val += 1;
        int sum = node.val;
        node.val = sum%10;
        return sum/10;
    }
    public ListNode plusOne(ListNode node) {
        if(node == null)        return node;
        int c = getCarry(node);
        if(c == 0)              return node;
        // insert new node at the head
        ListNode nh =  new ListNode(1);
        nh.next = node;
        return nh;
    }
    
}