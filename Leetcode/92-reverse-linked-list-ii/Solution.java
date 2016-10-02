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