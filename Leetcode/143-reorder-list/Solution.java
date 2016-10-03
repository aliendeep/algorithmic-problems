/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
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
    public void print(ListNode head){
        ListNode cur = head;
        while(cur != null){
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }    
    // Divide the node into two parts
    public void reorderList(ListNode head) {
        if(head == null || head.next == null)
            return;

        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        // Separate two lists
        ListNode second = slow.next;
        slow.next = null;
        
        // Reverse second lists
        ListNode prev = null;
        ListNode cur = second;
        while(cur != null){
            ListNode t = cur.next;
            cur.next = prev;
            prev = cur;
            cur = t;
        }
        
        // Add two lists
        cur = head;
        second = prev;
        while(second != null){
            ListNode p = cur.next;
            ListNode q = second.next;
            cur.next = second;
            second.next = p;
            cur = p;
            second = q;
        }
        return;
    }
}