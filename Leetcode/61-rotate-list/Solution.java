/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
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
    public int getLength(ListNode head){
        if(head == null)    return 0;
        ListNode cur = head;
        int len = 0;
        while(cur != null){
            len++;
            cur = cur.next;
        }
        return len;
    }
    
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null)  
            return head;

        int len = getLength(head);
        k = k%len;
        if(k == 0)  return head;
        
        int rotatePos = len - k;
        // Move one element k times
        ListNode secondPrev = head;
        for(int i=0; i<rotatePos-1; i++)
            secondPrev = secondPrev.next;

        // Separate the two lists
        ListNode second = secondPrev.next;
        secondPrev.next = null;
        
        ListNode cur = second;
        // Find the last element
        while(cur.next != null)
            cur = cur.next;
    
        // Append the previous head to the tail
        cur.next = head;
        return second;        
    }
}
