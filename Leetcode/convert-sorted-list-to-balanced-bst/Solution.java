/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    // O(nlogn) Solution
    public TreeNode sortedListToBST(ListNode head) {  
        if(head == null)
            return null;
        if(head.next == null)
            return new TreeNode(head.val);
            
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = head;
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // Split the two lists
        prev.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }
}

public class Solution2 {
    /**
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    ListNode node;
    
    int getLength(ListNode n){
        int l = 0;
        while(n != null){
            l++;
            n = n.next;
        }
        return l;
    }
    
    public TreeNode inOrderHelper(int start, int end){
        if(start > end) 
            return null;
        int mid = (start + end)/2;
        TreeNode left = inOrderHelper(start, mid-1);

        TreeNode root = new TreeNode(node.val);
        node = node.next;
                
        TreeNode right = inOrderHelper(mid+1, end);

        root.left = left;
        root.right = right;
        return root;
    }
    
    public TreeNode sortedListToBST(ListNode head) {  
        if(head == null)        return null;
        this.node = head;  
        int n = getLength(head);
        return inOrderHelper(0, n-1);
    }
}
