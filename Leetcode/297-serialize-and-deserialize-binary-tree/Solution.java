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

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));