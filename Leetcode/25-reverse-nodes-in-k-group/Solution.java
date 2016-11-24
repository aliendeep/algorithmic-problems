/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
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
    // Returns the last node of the this segment
    // prev -> last -> cur ->cur.next
    // Example: 1 -> 2 -> 3 -> 4
    // Goal: 1 -> 3 -> 2 -> 4
    // And make 4 the next cur
    ListNode reverseBetween(ListNode prev, ListNode next){
        ListNode last = prev.next;
        ListNode cur = last.next;
        while(cur != next){
            last.next = cur.next;
            cur.next = prev.next;
            prev.next = cur;
            cur = last.next;
        }
        return last;
    }
    
    public ListNode reverseKGroup(ListNode head, int k)  {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode cur = head;
        int cnt = 0;
        while(cur != null){
            cnt++;
            if(cnt % k == 0){
                prev = reverseBetween(prev, cur.next);
                // start of next segment
                cur = prev.next;
            }
            else
                cur = cur.next;
        }
        return dummy.next;
    }   
}
// Alternative: Recursive
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int cnt = 0;
        while(cur != null && cnt != k){
            cur = cur.next;
            cnt++;
        }
        
        if(cnt == k){
            cur = reverseKGroup(cur, k);
            // reverse current k group
            ListNode prev = cur;
            ListNode curr = head;
            while(cnt-- > 0){
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            head = prev;
        }
        return head;
    }
}