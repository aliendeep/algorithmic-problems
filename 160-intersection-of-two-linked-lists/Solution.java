/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public int getLength(ListNode n){
        int cnt = 0;
        while(n != null){
            n = n.next;
            cnt++;
        }
        return cnt;
    }
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int l1 = getLength(headA);
        int l2 = getLength(headB);
        ListNode p = headA;
        ListNode q = headB;
        
        if(l1 < l2){
            int diff = l2 - l1;
            for(int i=0; i<diff; i++){
                if(q == null)
                    return null;
                q = q.next;
            }
        }
        else if(l2 < l1){
            int diff = l1 - l2;
            for(int i=0; i<diff; i++){
                if(p == null)
                    return null;
                p = p.next;
            }
        }
        
        while(p != null && q != null){
            if(p == q)
                return p;
            p = p.next;
            q = q.next;
        }
        return null;
    }
}