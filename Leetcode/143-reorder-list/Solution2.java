/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void reorderList(ListNode head) {  
        if(head == null || head.next == null)
            return;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        // Separate two lists
        ListNode second = slow.next;
        slow.next = null;
        
        // reverse second half
        ListNode prev = null;
        ListNode cur = second;
        while(cur != null){
            ListNode t = cur.next;
            cur.next = prev;
            prev = cur;
            cur = t;
        }
        
        // Join two lists
        ListNode result = new ListNode(-1);
        ListNode resultPrev = result;
        ListNode first = head;
        second = prev;
        
        boolean turn = true;
        while(first != null && second != null){
            if(turn){
                resultPrev.next = first;
                first = first.next;
            }
            else{
                resultPrev.next = second;
                second = second.next;
            }
            turn = !turn;
            resultPrev = resultPrev.next;
        }
        if(first != null)
            resultPrev.next = first;
        if(second != null)
            resultPrev.next = second;
        head = result.next;
    }
}
