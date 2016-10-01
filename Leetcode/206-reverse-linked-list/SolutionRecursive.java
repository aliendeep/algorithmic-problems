/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    // Recursive
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode rest = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return rest;
    }
}