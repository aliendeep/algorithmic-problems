/*
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeLinkNode getNext(TreeLinkNode root){
        TreeLinkNode cur = root;
        while(cur != null){
            // if the cur has a left child
            if(cur.left != null)
                return cur.left;
            
            // if the cur has a right child
            if(cur.right != null)
                return cur.right;
            
            // otherwise go to the next node of the cur
            cur = cur.next;
        }
        return null;
    }
    
    public void connectNextLevel(TreeLinkNode start){
        TreeLinkNode cur = start;
        while(cur != null){
            // left child
            if(cur.left != null){
                if(cur.right != null)
                    cur.left.next = cur.right;
                else
                    cur.left.next = getNext(cur.next);
            }
            // Right child (left child can be empty)
            if(cur.right != null){
                cur.right.next = getNext(cur.next);
            }
            // next of the previous already set up. No need to call getNext(cur)
            cur = cur.next;
        }
    }
    
    public void connect(TreeLinkNode root) {
        TreeLinkNode cur = root;
        while(cur != null){
            connectNextLevel(cur);
            // As given tree could be any binary tree, get the next valid starting node
            cur = getNext(cur);
        }
    }
}