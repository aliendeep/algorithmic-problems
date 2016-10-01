import java.util.*;

public class Solution {
    public void print(List<String> s){
        for(String str : s){
            System.out.print(str + " ");
        }
        System.out.println();
    }
    
    public String decodeString(String s) {
        // preprocess
        List<String> tokens = new ArrayList<>();
        int num = 0;
        
        StringBuffer buf = new StringBuffer();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '[' || c == ']'){
                tokens.add(String.valueOf(c));
            }
            else if(Character.isDigit(c) == true){
                num = num*10 + c - '0';
                if(i < s.length() - 1 && !Character.isDigit(s.charAt(i+1))){
                    tokens.add(Integer.toString(num));
                    // reset num
                    num = 0;
                }
            }
            else{
                buf.append(c);
                if(i < s.length() - 1 && !Character.isLetter(s.charAt(i+1))){
                    tokens.add(new String(buf.toString()));
                    // reset buff
                    buf.setLength(0);
                }
            }
        }
        
        // if buffer is not empty, then add the last entry
        if(buf.length() > 0)
            tokens.add(new String(buf.toString()));

        Deque<String> stk = new LinkedList<>();
        // contains the number
        Deque<Integer> cntStk = new LinkedList<>();
        num = 0;

        stk.push("");
        for(String token : tokens){ 
            if(Character.isDigit(token.charAt(0)) == true)
                cntStk.addFirst(Integer.parseInt(token));
            else if(token.charAt(0)  == '[')
                stk.addFirst("");
            else if(token.charAt(0) == ']'){
                StringBuffer t = new StringBuffer();
                t.append(stk.removeFirst());

                int dupCount = cntStk.removeFirst();
                String repeated = new String(new char[dupCount]).replace("\0", t.toString());
                stk.addFirst(stk.removeFirst() + repeated);
            }
            else{
                // characters
                stk.push(stk.removeFirst() + token);
            }
        }
        return stk.removeFirst();
    }
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.decodeString(s.decodeString("3[a]2[bc]")));    
        System.out.println(s.decodeString(s.decodeString("3[a2[c]]")));    
        System.out.println(s.decodeString(s.decodeString("2[abc]3[cd]ef")));    
    }
}