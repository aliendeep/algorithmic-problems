// Sort a linked list using insertion sort.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        // dummy head
        ListNode dummy = new ListNode(-1);
        ListNode prev;
        
        ListNode cur = head;
        while(cur != null){
            // find the insert position
            prev = dummy;
            while(prev.next != null && prev.next.val <= cur.val)
                prev = prev.next;
                
            ListNode t = prev.next;
            prev.next = new ListNode(cur.val);
            prev.next.next = t; 
            cur = cur.next;
        }
        return dummy.next;
    }
}