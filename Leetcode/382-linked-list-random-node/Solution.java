/*
Given a singly linked list, return a random node's value from the linked list. 
Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? 
Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have 
equal probability of returning.
solution.getRandom();
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 // http://www.geeksforgeeks.org/select-a-random-node-from-a-singly-linked-list/
 //  Reservoir Sampling
 // Probability of selecting any node = 1/n
/*
(1) Initialize result as first node
   result = head->key 
(2) Initialize n = 2
(3) Now one by one consider all nodes from 2nd node onward.
    (3.a) Generate a random number from 0 to n-1. 
         Let the generated random number is j.
    (3.b) If j is equal to 0 (we could choose other fixed number 
          between 0 to n-1), then replace result with current node.
    (3.c) n = n+1
    (3.d) current = current->next
*/
  
public class Solution {
    public static final int FIXED_NUM = 0;
    ListNode head;
    Random randomNum;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;        
        randomNum = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int result = head.val;

        ListNode cur = head.next;
        int n = 2;
        while(cur != null){
            // change result with probability 1/n
            if(randomNum.nextInt() % n == FIXED_NUM)
                result = cur.val;    
            cur = cur.next;
            n++;
        }
        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */

// Alternative
class Solution2 {
    ListNode head;
    int len;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
        
        len = 0;
        ListNode cur = head;
        while(cur != null){
            cur = cur.next;
            len++;
        }
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        if(head.next == null)
            return head.val;
        Random r = new Random();
        // Generate a random number between [0 .. len-1]
        int randIndex = r.nextInt(len);
        ListNode cur = head;
        for(int i=0; i<randIndex; ++i)
            cur = cur.next;
        return cur.val;
    }
}
