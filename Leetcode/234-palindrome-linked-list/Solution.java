/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    // Solution 1 : Create a reverse list
    // Time: O(n) Space: O(n)
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
            return true;

        ListNode original = head;
        // Create
        ListNode prev = new ListNode(head.val);
        while(original.next != null){
            ListNode cur = new ListNode(original.next.val);;
            cur.next = prev;
            prev = cur;
            original = original.next;
        }

        // Head of the reversed linked list
        ListNode t = head;
        ListNode rev = prev;
        while(t != null){
           if(t.val != rev.val)
                return false;
            t = t.next;
            rev = rev.next;
        }
        return true;
    }
}

// Solution 2 : Time: O(n) Space: O(1)
class Solution2 {
    ListNode reverse(ListNode head){
        if(head == null)
            return head;
        ListNode prev = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }    
    public boolean isPalindrome(ListNode head) {
        // Find the mid point
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode first = head;
        ListNode second = reverse(slow);
        
        // If the length of the list is odd, we don't want to compare the middle element
        while(second != null && first != null){
            if(second.val != first.val)
                return false;
            second = second.next;
            first = first.next;
        }
        return true;
    }
}