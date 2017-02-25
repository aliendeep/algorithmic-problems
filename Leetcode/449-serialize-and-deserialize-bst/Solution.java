/*
Serialization is the process of converting a data structure or object into a 
sequence of bits so that it can be stored in a file or memory buffer, or 
transmitted across a network connection link to be reconstructed later in the 
same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is 
no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary search tree can be serialized to a string 
and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your 
serialize and deserialize algorithms should be stateless.
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
public class Codec {
    public void serialize(TreeNode node, StringBuilder s) {
        if(node == null){
            return;
        }
        s.append(node.val);
        s.append(" ");
        serialize(node.left, s);
        serialize(node.right, s);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder s = new StringBuilder();
        serialize(root, s);
        return s.toString();
    }
    
    static int index;
    public TreeNode buildTree(String[] s, int min, int max) {
        if(index == s.length)
            return null;

        int rootVal = Integer.parseInt(s[index]);
        if(rootVal < min || rootVal > max)
            return null;

        index++;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(s, min, root.val);
        root.right = buildTree(s, root.val, max);
        return root;
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0)    return null;
        String[] s = data.split(" ");
        index = 0;
        return buildTree(s, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
