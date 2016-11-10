/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null)
            return head;
        // less than x
        ListNode firstHead = new ListNode(-1);
        ListNode firstPrev = firstHead;
        // >= x
        ListNode secondHead = new ListNode(-1);
        ListNode secondPrev = secondHead;

        ListNode cur = head;
        while(cur != null){
            if(cur.val < x){
                firstPrev.next = cur;
                firstPrev = firstPrev.next;
            }
            else{
                secondPrev.next = cur;
                secondPrev = secondPrev.next;
            }
            cur = cur.next;
        }
        secondPrev.next = null;
        firstPrev.next = secondHead.next;
        return firstHead.next;
    }
}