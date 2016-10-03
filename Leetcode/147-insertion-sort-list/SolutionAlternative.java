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
    public void insertList(ListNode prev, ListNode cur){
        while(prev.next != null && prev.next.val < cur.val)  
            prev = prev.next;
        
        ListNode t = prev.next;
        prev.next = new ListNode(cur.val);
        prev.next.next = t;        
    }
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
            
        ListNode dummy = new ListNode(Integer.MIN_VALUE); 
        ListNode prev = dummy;
        for(ListNode cur = head; cur != null; cur = cur.next){
            insertList(prev, cur);
        }
            
        return dummy.next;
    }
}