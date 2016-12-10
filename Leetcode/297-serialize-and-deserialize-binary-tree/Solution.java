/*
Serialization is the process of converting a data structure or object into a 
sequence of bits so that it can be stored in a file or memory buffer, or 
transmitted across a network connection link to be reconstructed later in the same 
or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no 
restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary tree can be serialized to a string and 
this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary 
tree. You do not necessarily need to follow this format, so please be creative 
and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. 
Your serialize and deserialize algorithms should be stateless.

Sample Input:
[1, 2, 3, 4, 5, 6, 7, 8]
[1, 2, 3, 4, 5, 6, 7]
[1, 2, 3]
[1, 2]
[1]
[]

Sample Output:
[1,2,3,4,5,6,7,8]
[1,2,3,4,5,6,7]
[1,2,3]
[1,2]
[1]
[]
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
// Recursive: Cleaner
public class Codec {
    public static String nullNode = "#";
    // Preorder traversal (root, left, right)
    public void serializeTree(TreeNode root, StringBuilder sb){   
        if(root == null){
            // # to denote null node
            sb.append(nullNode).append(" ");
            return;
        }
        // Space as the separator
        sb.append(root.val).append(" ");
        serializeTree(root.left, sb);
        serializeTree(root.right, sb);
    }        
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeTree(root, sb);
        return sb.toString();
    }
    
    int index;
    public TreeNode buildTree(String[] data){
        if(index == data.length)
            return null;
        if(data[index].equals(nullNode)){
            index++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(data[index]));
        index++;
        root.left = buildTree(data);
        root.right = buildTree(data);
        return root;
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data){
        String[] s = data.split(" ");
        index = 0;
        return buildTree(s);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

// Recursive
class Codec2 {
    // Preorder traversal
    public StringBuffer serializeTree(TreeNode root){   
        if(root ==  null){
            return new StringBuffer("# ");
        }
        StringBuffer r = new StringBuffer();
        r.append(root.val);
        // Space is the seperator
        r.append(" ");
        r.append(serializeTree(root.left));
        r.append(serializeTree(root.right));
        return r;
    }        
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer s = serializeTree(root);
        return s.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserializeTree(StringBuffer data) {
        if(data.length() == 0)
            return null;
            
        int index = data.indexOf(" ");
        if(index == -1)
            return null;
            
        String val = data.substring(0, index);
        // erase part upto index (data[index] contains the space character)
        data.delete(0, index+1);
        if(val.compareTo("#") == 0)
            return null;
        
        int rootVal = Integer.parseInt(val);
        //System.out.println(val + " " + rootVal);   
        TreeNode root = new TreeNode(rootVal);
        root.left = deserializeTree(data);
        root.right = deserializeTree(data);
        return root;
    }
    public TreeNode deserialize(String data){
        StringBuffer s = new StringBuffer(data);
        return deserializeTree(s);
    }
}

// Queue
public class Codec {
    public static String nullNode = "#";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        while(!Q.isEmpty()){
            TreeNode front = Q.remove();
            if(front == null){
                sb.append(nullNode).append(" ");
            }
            else{
                sb.append(front.val).append(" ");
                Q.add(front.left);
                Q.add(front.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] s = data.split(" ");
        if(s[0].equals(nullNode))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        int i = 1;
        while(i<s.length){
            TreeNode parent = Q.remove();
            // left
            if(!s[i].equals("#")){
                parent.left = new TreeNode(Integer.parseInt(s[i]));
                Q.add(parent.left);
            }
            i++;
            // right node
            if(!s[i].equals("#")){
                parent.right = new TreeNode(Integer.parseInt(s[i]));
                Q.add(parent.right);
            }
            i++;
        }
        return root;
    }
}
