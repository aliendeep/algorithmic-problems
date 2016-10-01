/*
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// Iterative
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode pprev = null;
        ListNode prev = head;
        ListNode cur = head.next;
        while(cur != null){
            prev.next = cur.next;
            cur.next = prev;
            if(pprev == null){
                head = cur;
            }
            else{
                pprev.next = cur;
            }
            pprev = prev;
            prev = prev.next;
            cur = (prev == null) ? null : prev.next;
        }
        return head;
    }
}
