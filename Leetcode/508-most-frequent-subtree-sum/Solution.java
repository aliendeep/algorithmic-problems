/*
Given the root of a tree, you are asked to find the most frequent subtree sum. 
The subtree sum of a node is defined as the sum of all the node values formed 
by the subtree rooted at that node (including the node itself). So what is the 
most frequent subtree sum value? If there is a tie, return all the values with 
the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in 
any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit 
signed integer.
*/
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
    // Subtree sum, frequency
    Map<Integer, Integer> map;
    
    public int getSubtreeSum(TreeNode node){
        if(node == null)    return 0;
        int leftSum = getSubtreeSum(node.left);
        int rightSum = getSubtreeSum(node.right);
        int sum = leftSum + node.val + rightSum;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null)    return new int[0];
        map = new HashMap<>();
        getSubtreeSum(root);

        // find the max frequency
        int maxFreq = 0;
        int cnt = 0;
        for(int freq : map.values()){
            if(freq > maxFreq){
                maxFreq = freq;
                cnt = 1;
            }
            else if(freq == maxFreq){
                cnt++;
            }
        }
        int[] result = new int[cnt];
        int i = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == maxFreq){
                result[i++] = entry.getKey();
            }
        }
        return result;
    }
}

// concise
public class Solution {
    // Subtree sum, frequency
    Map<Integer, Integer> map;
    int maxFreq;
    int cnt;
    
    public int getSubtreeSum(TreeNode node){
        if(node == null)    return 0;
        int leftSum = getSubtreeSum(node.left);
        int rightSum = getSubtreeSum(node.right);
        int sum = leftSum + node.val + rightSum;
        int freq = map.getOrDefault(sum, 0) + 1;
        map.put(sum, freq);
        if(maxFreq < freq)  {
            maxFreq = freq;
            cnt = 1;
        }
        else if(maxFreq == freq)
            cnt++;
        return sum;
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null)    return new int[0];
        map = new HashMap<>();
        maxFreq = 0;
        cnt = 0;
        getSubtreeSum(root);

        int i = 0;
        int[] result = new int[cnt];
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == maxFreq){
                result[i++] = entry.getKey();
            }
        }
        return result;
    }
}
