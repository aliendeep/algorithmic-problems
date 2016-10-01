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