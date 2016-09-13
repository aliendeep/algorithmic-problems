/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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