/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/binary-tree-serialization
@Language: Java
@Datetime: 16-11-09 20:54
*/

/**
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
class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public void serialize(TreeNode root, StringBuilder s) {
        if(root == null){
            s.append("# ");
            return;
        }
        s.append(root.val);
        // space as the separator
        s.append(" ");
        serialize(root.left, s);
        serialize(root.right, s);
    }

    public String serialize(TreeNode root) {
        if(root == null)
            return "";
        StringBuilder s = new StringBuilder();
        serialize(root, s);
        return s.toString();
    }
    
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
     
    // Decodes your encoded data to tree.
    public TreeNode deserialize(StringBuilder data) {
        if(data.length() == 0)
            return null;
            
        int index = data.indexOf(" ");
        if(index == -1)
            return null;
            
        String val = data.substring(0, index);
        data.delete(0, index+1);
        if(val.compareTo("#") == 0)
            return null;
        
        int rootVal = Integer.parseInt(val);
        TreeNode root = new TreeNode(rootVal);
        root.left = deserialize(data);
        root.right = deserialize(data);
        return root;
    }
    public TreeNode deserialize(String data){
        StringBuilder s = new StringBuilder(data);
        return deserialize(s);
    }
}
