/*
One way to serialize a binary tree is to use pre-order traversal. 
When we encounter a non-null node, we record the node's value. If it is a 
null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string 
"9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder 
traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character 
'#' representing null pointer.

You may assume that the input format is always valid, for example it could never 
contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false
*/

public class Solution {
    // Stack
    public boolean isValidSerialization(String preorder) {
        String[] pre = preorder.split(",");
        Deque<String> stk = new LinkedList<>();
        for(int i=0; i<pre.length; i++){
            String s = pre[i];
            // current char i #
            if(s.equals("#")){
                while(!stk.isEmpty() && stk.peekFirst().equals("#")){
                    // pop the previous # (Left child)
                    stk.pop(); 
                    if(stk.isEmpty())
                        return false;
                    // pop the parent of the left child (number)
                    stk.pop();
                    // Current # will work as the processed left subtree of immediate upper level
                }    
            }
            // if s is a number, push this into stack
            // if s is #, then push it
            stk.push(s);
        }        
        return stk.size() == 1 && stk.pop().equals("#");
    }
}

class Solution2 {
    // Keep removing the leaf nodes
    // Each leaf node is followed by two #
    // Replace two ## by one #
    // At the end, if # exists, then it's a valid serialization
    public boolean isValidSerialization(String preorder) {
        String[] s = preorder.split(",");
        Stack<String> stk = new Stack<String>();
        for(int i=0; i<s.length; i++){
            stk.push(s[i]);
            while(stk.size() >= 3){
                String c = stk.pop();
                String b = stk.pop();
                String a = stk.pop();
                // push # to the stack if last three strings are two # and one number
                if(c.compareTo("#") == 0 && b.compareTo("#") == 0 &&  a.compareTo("#") != 0){
                    stk.push(new String("#"));                    
                }
                // restore stack
                else{
                    stk.push(a);
                    stk.push(b);
                    stk.push(c);
                    break;
                }
            }
        }
        if(stk.size() != 1)
            return false;
        return stk.pop().compareTo("#") == 0;
    }
}

class Solution3 {
    // https://discuss.leetcode.com/topic/35976/7-lines-easy-java-solution
    // difference of indegree and outdegree
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int diff = 1;
        for(int i=0; i<nodes.length; ++i){
            if(--diff < 0)
                return false;
            if(!nodes[i].equals("#"))
                diff += 2;
                
        }
        return diff == 0;
    }
}

class Solution4 {
    // Counting Indegree and Outdegree
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        // Indegree of root is 0 
        int diff = -1;
        for(int i=0; i<nodes.length; ++i){
            // All nodes have one incoming edge
            diff++;
            if(diff > 0)
                return false;
            // Non leaf nodes have two outdegrees
            if(!nodes[i].equals("#"))
                diff -= 2;
                
        }
        return diff == 0;
    }
}