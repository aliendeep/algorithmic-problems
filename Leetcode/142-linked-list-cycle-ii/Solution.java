/*
Given a linked list, return the node where the cycle begins. If there is no cycle, 
return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?
*/
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null)
            return null;

        ListNode fast = head;
        ListNode slow = head;
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
            if(fast == null)
                return null;
            fast = fast.next;
            if(slow == fast)
                break;
        }
        
        if(fast == null)
            return null;
            
        fast = head;
        while(fast != slow){
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;        
    }
}

// Cleaner
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null)
            return null;

        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                break;
        }
        
        if(fast == null || fast.next == null)
            return null;
            
        fast = head;
        while(fast != slow){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;        
    }
}
