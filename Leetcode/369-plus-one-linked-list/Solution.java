/*
Given a non-negative number represented as a singly linked list of digits, 
plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Iterative
public class Solution {
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // Find the last value that is less than 9
        ListNode lastLessThanNine = dummy;
        ListNode cur = head;
        while(cur != null){
            if(cur.val != 9)
                lastLessThanNine = cur;
            cur = cur.next;
        }
        lastLessThanNine.val = lastLessThanNine.val + 1;
        cur = lastLessThanNine.next;
        while(cur != null){
            cur.val = 0;
            cur = cur.next;
        }
        return dummy.val == 1 ? dummy : dummy.next;
    }
}

class Solution {
    public int carry(ListNode node) {
        if(node == null)
            return 0;
        // last node (Add 1 to this node)
        if(node.next == null){
            node.val += 1;
            if(node.val == 10){
                node.val = 0;
                return 1;
            }
            return 0;
        }
        
        int c = carry(node.next);
        if(c == 0)
            return 0;
        node.val += 1;
        if(node.val == 10){
            node.val = 0;
            return 1;
        }
        return 0;
    }
    public ListNode plusOne(ListNode node) {
        if(node == null)
            return node;
        int c = carry(node);
        if(c == 0)
            return node;
        // insert new node at the head
        ListNode nh =  new ListNode(1);
        nh.next = node;
        return nh;
    }
    
}

// Iterative
class Solution {
    public ListNode reverse(ListNode head){
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
    public ListNode plusOne(ListNode head) {
        if(head == null)
            return head;
        ListNode rh = reverse(head);
        ListNode cur = rh;
        // Add 1 to the node
        cur.val = cur.val + 1;
        while(cur.next != null && cur.val == 10){
            cur.val = 0;
            cur.next.val = cur.next.val + 1;
            cur = cur.next;
        }
        if(cur.val == 10){
            cur.val = 0;
            // insert 1 new one
            cur.next = new ListNode(1);
        }
        return reverse(rh);
    }
}

// Alternative: Recursion
class Solution {
    public int getCarry(ListNode node) {
        if(node == null)
            return 1;
        int c = getCarry(node.next);
        if(c == 0)      return 0;
        // carry
        node.val += 1;
        int sum = node.val;
        node.val = sum%10;
        return sum/10;
    }
    public ListNode plusOne(ListNode node) {
        if(node == null)        return node;
        int c = getCarry(node);
        if(c == 0)              return node;
        // insert new node at the head
        ListNode nh =  new ListNode(1);
        nh.next = node;
        return nh;
    }
    
}
