/*
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Time complexity: O(n)
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Create a dummy node 
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        
        int sum = 0;
        int carry = 0;
        
        while(l1 != null || l2 != null || carry != 0){
            sum = carry;
            
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            
            carry = sum / 10;
            sum = sum % 10;
            
            prev.next = new ListNode(sum);
            prev = prev.next;
        }
        return dummy.next;
          
    }
}