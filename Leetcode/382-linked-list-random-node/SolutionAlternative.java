/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// Alternative
public class Solution {
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

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */