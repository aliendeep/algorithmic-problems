/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n) 
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;     
        
        ListNode subListPrev = dummy;
        for(int i=1;i<m; i++){
            subListPrev = subListPrev.next;
        }
        
        // reverse sublist
        ListNode cur = subListPrev.next;
        while(m < n){
            ListNode t = cur.next;
            cur.next = t.next;
            t.next = subListPrev.next;
            subListPrev.next = t;
            m++;
        }
        return dummy.next;
    }
}

// Reverse LinkedList cleaner solution
public class Solution {
    // Reverse n nodes after this head
    // There should be at least n nodes after head
    public ListNode reversePartial(ListNode head, int n){
        if(n == 1)      return head;
        // 1. before calling 'reversePartial' state is:
        //
        // [head]-->[old_head_next]-->....-->[new_head]-->[X]
        //
        // 2. after calling:
        //
        // [new_head]-->...-->[old_head_next]-->[X]
        //                         ^
        //                         |
        //                        [head]
        //
        // 3. after putting [head] in the middle:
        //
        // [new_head]-->...-->[old_head_next]-->[head]-->[X]
        //
        ListNode newHead = reversePartial(head.next, n-1);
        ListNode oldHeadNext = head.next;
        head.next = oldHeadNext.next;
        oldHeadNext.next = head;
        return newHead;
    }
    
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // number of reveresed items is (n - m + 1)
        n = (n - m + 1);
        
        ListNode prev = null;
        ListNode start = head;
        // m - 1 times
        while(--m > 0){
            prev = start;
            start = start.next;
        }
        ListNode newSubHead = reversePartial(start, n);
        if(prev == null)    return newSubHead;
        prev.next = newSubHead;        
        return head;
    }
}
