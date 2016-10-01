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