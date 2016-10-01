/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return head;
        
        // Create a dummy node to handle special cases
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;
        while(prev.next != null){
            if(prev.next.val == val){
                prev.next = prev.next.next;
            }
            else 
                prev = prev.next;
        }
        
        return dummy.next;
    }
}