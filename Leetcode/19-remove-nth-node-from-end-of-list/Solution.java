/*
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// One pass
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return head;
    
        // Create a dummy node to handle special case
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode fast = dummy;
        for(int i=0; i<=n; i++)
            fast = fast.next;
            
        ListNode prevNthNode = dummy;
        while(fast != null){
            fast = fast.next;
            prevNthNode = prevNthNode.next;
        }
        
        // remove Node prevNthNode.next
        prevNthNode.next = prevNthNode.next.next; 
        return dummy.next;
   }
}

// Recursive
public class Solution {
    int n;
    int cur;
    ListNode prev;
    
    public void findnthPrevNode(ListNode head){
        if(head == null)    return;
        findnthPrevNode(head.next);
        cur++;
        if(cur == n + 1){
            prev = head;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        this.n = n;         
        cur = 0;
        prev = null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        findnthPrevNode(dummy);
        prev.next = prev.next.next;
        return dummy.next;
    }
}
