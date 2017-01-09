/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving 
only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode cur = head;
        long dup = Long.MIN_VALUE;
        while(cur != null && cur.next != null){
            while(cur.next != null  && cur.val == cur.next.val){
                if(dup == Long.MIN_VALUE){
                    dup = cur.val;
                }
                // delete the next node
                cur.next = cur.next.next;
            }
            // delete the first node in the duplicate zone
            if(cur.val == dup){
                cur = cur.next;
                prev.next = cur;
                // reset the dup
                dup = Long.MIN_VALUE;
            }
            else{
                prev = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
