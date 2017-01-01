/*
A linked list is given such that each node contains an additional random pointer 
which could point to any node in the list or NULL.

Return a deep copy of the list.

Example

Given list

   1 -> 2 -> 3
with random pointers going from

  1 -> 3
  2 -> 1
  3 -> 1
You should return a deep copy of the list. The returned answer should not 
contain the same node as the original list, but a copy of them. The pointers in 
the returned list should not link to any node in the original input list.
*/
**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null)        return null;
        RandomListNode cur = head;
        // create a copy of each node after each node
        while(cur != null){
            RandomListNode t = cur.next;
            cur.next = new RandomListNode(cur.label);
            cur.next.next = t;
            cur = cur.next.next;
        }
        
        // Fix random pointers
        cur = head;
        while(cur != null){
            if(cur.random != null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }  
        
        // Seperate two lists
        cur = head;
        RandomListNode copyHead = head.next;
        RandomListNode prev = copyHead;
        while(cur != null){
           cur.next = cur.next.next;
           if(prev.next != null)
                prev.next = prev.next.next;
        
            prev = prev.next;
            cur = cur.next;
        }
        
        return copyHead;    
    }
}
