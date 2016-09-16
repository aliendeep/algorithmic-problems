public class Solution {
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