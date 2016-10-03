// Sort a linked list in O(n log n) time using constant space complexity.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    // Merge sort
    ListNode merge(ListNode a, ListNode b){
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        while(a != null && b != null){
            if(a.val <= b.val){
                prev.next = a;
                a = a.next;
            }
            else{
                prev.next = b;
                b = b.next;
            }
            prev = prev.next;
        }    
        
        if(a != null){
            prev.next = a;
        }
        else if(b != null){
            prev.next = b;
        }
        return dummy.next;
    }
    
    ListNode sortList(ListNode head) {
        if(head == null || head.next == null)  
            return head;

        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode left = head;
        ListNode right = slow.next;
        slow.next = null;
        
        ListNode m1 = sortList(left);
        ListNode m2 = sortList(right);
        return merge(m1, m2);
    }}