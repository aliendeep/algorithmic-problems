
/*Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

 A height balanced BST : a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1. 
Example :


Given A : 1 -> 2 -> 3
A height balanced BST  :

      2
    /   \
   1     3

*/
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class Solution {
    ListNode node;
    
    int getLength(ListNode node){
        int len = 0;
        while(node != null){
            len++;
            node = node.next;
        }
        return len;
    }

  public TreeNode sortedListToBST(ListNode head) {
      if(head == null)
          return null;
      int n = getLength(head);
      this.node = head;
      return inorder(0, n-1);
  }
  
  public TreeNode inorder(int start, int end){
      if(end < start)
          return null;
         
     int mid = (start + end)/2;
     TreeNode left = inorder(start, mid-1);
     
     TreeNode root = new TreeNode(node.val);
     root.left = left;

     node = node.next;
    
     root.right = inorder(mid+1, end);
     return root;
  }
}
