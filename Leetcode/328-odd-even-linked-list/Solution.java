/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode oddPrev = head;
        ListNode evenHead = head.next;
        ListNode evenPrev = head.next;
        ListNode cur = evenPrev.next;
        while(cur != null){
            oddPrev.next = cur;
            evenPrev.next = cur.next;
            oddPrev = oddPrev.next;
            evenPrev = evenPrev.next;
            if(evenPrev != null)
                cur = evenPrev.next;
            else
                break;
        }
        
        // connect
        oddPrev.next = evenHead;
        return head;
    }
}