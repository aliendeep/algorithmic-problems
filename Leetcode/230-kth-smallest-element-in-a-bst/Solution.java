/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public static int cur = 0;
    // inorder traversal : left, root, right
    public TreeNode kthSmallestNode(TreeNode root, int k) {
        if(root == null)
            return null;
            
        TreeNode l = kthSmallestNode(root.left, k);
        if(l != null)
            return l;

        cur++;
        if(cur == k){
            return root;
        }

        TreeNode r = kthSmallestNode(root.right, k);
        if(r != null)
            return r;
        return null;
    }

    public int kthSmallest(TreeNode root, int k) {
        cur = 0;
        TreeNode r = kthSmallestNode(root, k);
        if(r != null)   
            return r.val;
        return -1;
    }
}

class Solution2 {
    int cur;
    int result;
    
    public void kth(TreeNode node, int k) {
        if(node == null)
            return;
        kth(node.left, k);
        cur++;
        if(cur == k){
            result = node.val;
            return;
        }
        kth(node.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        if(root == null)    return 0;
        cur = 0;
        result = 0;
        kth(root, k);
        return result;
    }
}


// Follow up: Modifty the TreeNode structure to keep a node count
class Solution3 {
    // Number of nodes in the subtree
    int countNodes(TreeNode node){
        if(node == null)    return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
    public int kthSmallest(TreeNode root, int k) {
        // countNodes will be kept in the TreeNode
        int leftSize = countNodes(root.left);
        // Left subtree
        if(leftSize + 1 == k)           return root.val;
        // the element is in the right subtree
        else if(leftSize < k){
            return kthSmallest(root.right, k - leftSize - 1);
        }
        // leftsize >= k
        else{
            return kthSmallest(root.left, k);
        }
    }
}