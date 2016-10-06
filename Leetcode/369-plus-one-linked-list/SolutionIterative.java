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
public class Solution {
    public ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
    public ListNode plusOne(ListNode head) {
        if(head == null)
            return head;
        ListNode rh = reverse(head);
        ListNode cur = rh;
        // Add 1 to the node
        cur.val = cur.val + 1;
        while(cur.next != null && cur.val == 10){
            cur.val = 0;
            cur.next.val = cur.next.val + 1;
            cur = cur.next;
        }
        if(cur.val == 10){
            cur.val = 0;
            // insert 1 new one
            cur.next = new ListNode(1);
        }
        return reverse(rh);
    }
}