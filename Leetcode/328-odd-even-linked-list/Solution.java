/*
Given a singly linked list, group all odd nodes together followed by the even nodes. 
Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and 
O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input. 
The first node is considered odd, the second node even and so on ...
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


// Cleaner
public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode odd = head, even = head.next, evenHead = even;
        while(even != null && even.next != null){
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        // Connect the two lists;
        odd.next = evenHead;
        return head;
    }
}

class Solution2 {
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
