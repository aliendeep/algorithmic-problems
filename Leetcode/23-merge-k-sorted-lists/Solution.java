/*
Merge k sorted linked lists and return it as one sorted list. 
Analyze and describe its complexity.
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Heap
// Time complexity: O(nlogK)
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0)
            return null;
            
        Comparator<ListNode> listComparator = new Comparator<ListNode>(){
            @Override
            public int compare(ListNode a, ListNode b){
                return a.val - b.val;
            }   
        }; 

        // minHeap contains at most k items
        int k = lists.length;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(k, listComparator);
        
        // Add first item from each list into the min heap
        for(ListNode head : lists){
            if(head != null)
                minHeap.add(head);
        }
        
        // Create a dummy node
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        
        while(!minHeap.isEmpty()){
            ListNode t = minHeap.poll();
            
            // update prev
            prev.next = t;
            prev = prev.next;
            
            t = t.next;
            // insert the next item from the same list
            if(t != null)
                minHeap.add(t);
        }
        return dummy.next;
    }
}
