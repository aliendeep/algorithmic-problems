/*
Given a binary search tree T, where each node contains a positive integer, and an integer K, 
you have to find whether or not there exist two different nodes A and B such 
that A.value + B.value = K.

Return 1 to denote that two such nodes exist. Return 0, otherwise.

Notes 
- Your solution should run in linear time and not take memory more than O(height of T).
- Assume all values in BST are distinct.

Example :

Input 1: 

T :       10
         / \
        9   20

K = 19

Return: 1

Input 2: 

T:        10
         / \
        9   20

K = 40

Return: 0
*/

/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// O(nlogn) Solution
public class Solution {
    public boolean isfound(TreeNode node, int target){
        if(node == null)
            return false;
        if(node.val == target)
            return true;
        
        if(target > node.val){
            return isfound(node.right, target);
        }
        else
            return isfound(node.left, target);
    }

    public boolean tSum(TreeNode root, TreeNode node, int sum) {
        if(node == null)
            return false;
        
        int diff = sum - node.val;
        if(diff != node.val && isfound(root, diff))
            return true;
        return tSum(root, node.left, sum) || tSum(root, node.right, sum);
    }
    
    public int t2Sum(TreeNode node, int sum) {
        return tSum(node, node, sum) ? 1 : 0;
    }
}

// O(n) Solution
// In order
class ForwardIterator {
    Deque<TreeNode> stk;
    public ForwardIterator(TreeNode root) {
        stk = new LinkedList<>();
        TreeNode cur = root;
        while(cur != null){
            stk.push(cur);
            cur = cur.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stk.isEmpty() == false;    
    }

    /** @return the next smallest number */
    public int next() {
        if(!hasNext())
            return -1;
        TreeNode ret = stk.pop();
        TreeNode cur = ret.right;
        while(cur != null){
            stk.push(cur);
            cur = cur.left;
        }
        return ret.val;
    }
}

// Reverse In order
class BackwardIterator {
    Deque<TreeNode> stk;
    public BackwardIterator(TreeNode root) {
        stk = new LinkedList<>();
        TreeNode cur = root;
        // Right subtree
        while(cur != null){
            stk.push(cur);
            cur = cur.right;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stk.isEmpty() == false;    
    }

    /** @return the next smallest number */
    public int next() {
        if(!hasNext())
            return -1;
        TreeNode ret = stk.pop();
        TreeNode cur = ret.left;
        while(cur != null){
            stk.push(cur);
            cur = cur.right;
        }
        return ret.val;
    }
}

public class Solution {
    public boolean tSum(TreeNode root, int sum) {
        ForwardIterator fi = new ForwardIterator(root);
        BackwardIterator bi = new BackwardIterator(root);
        int a = fi.next();
        int b = bi.next();
        while(a < b){
            int curSum = a + b;
            if(curSum == sum)
                return true;
            else if(curSum < sum){
                if(!fi.hasNext())
                    break;
                a = fi.next();
            }
            else{
                if(!bi.hasNext())
                    break;
                b = bi.next();
            }
        }
        return false;
    }
    
    public int t2Sum(TreeNode node, int sum) {
        return tSum(node, sum) ? 1 : 0;
    }
}
