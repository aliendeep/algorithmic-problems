/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @param x: an integer
     * @return: a ListNode 
     */
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
