/*
Given a linked list and a value x, partition it such that all nodes less than x 
come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two 
partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
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
    class ListInfo{
        public ListNode tail; 
        public int length;
        ListInfo(ListNode tail, int l){
            this.tail = tail;
            length = l;
        }
    }
    
    public ListInfo getTail(ListNode head){
        if(head == null)
            return new ListInfo(head, 1);
        int cnt = 0;
        ListNode cur = head;
        while(cur.next != null){
            cur = cur.next;
            cnt++;
        }
        return new ListInfo(cur, cnt+1);
    }
    
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null)
            return head;
        
        ListInfo l = getTail(head);
        ListNode tail = l.tail;
        int len = l.length;
        
        // Create a dummy node
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while(len > 0){
            if(prev.next.val < x)
                prev = prev.next;
            else{
                // update tail
                tail.next = new ListNode(prev.next.val);
                tail = tail.next;
                prev.next = prev.next.next;
            }
            len--;    
        }
        
        return dummy.next;
    }
}

// One pass Solution
class Solution2 {
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
