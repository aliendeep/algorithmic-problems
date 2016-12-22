/*
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the 
square brackets is being repeated exactly k times. Note that k is guaranteed to 
be a positive integer.

You may assume that the input string is always valid; No extra white spaces, 
square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and 
that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
*/

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

class Solution2{
    // Use two stacks
    public String decodeString(String s) {
        Deque<String> stk = new LinkedList<>();
        Deque<Integer> numStk = new LinkedList<>();
        
        StringBuilder cur = new StringBuilder();
        int num = 0;
        for(int i=0; i<s.length(); ++i){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num*10 + (c - '0');
            }
            else if(c == '['){
                numStk.push(num);
                stk.push(cur.toString());
                // reset
                cur.setLength(0);
                num = 0;
            }
            else if(c == ']'){
                int k = numStk.pop();
                String t = cur.toString();
                cur = new StringBuilder(stk.pop());
                for(int j=0; j<k; ++j){
                    cur.append(t);
                }
            }
            else{
                // Letter
                cur.append(c);
            }
        }
        return cur.toString();
    }
}   

class Solution3 {
    // Recursive
    int i;
    public String decode(String s) {
        StringBuilder r = new StringBuilder();
        int n = s.length();
        while(i < n && s.charAt(i) != ']'){
            char c = s.charAt(i);
            if(!Character.isDigit(c)){
                r.append(c);
                ++i;
            }
            else{
                int num = 0;
                while(i < n && Character.isDigit(s.charAt(i))){
                    num = num*10 + (s.charAt(i) - '0');
                    ++i;
                }
                // skip the [
                ++i;
                String temp = decode(s);
                // skip the ]
                ++i;
                while(num-- > 0){
                    r.append(temp);
                }
            }
        }
        return r.toString();
    }

    public String decodeString(String s) {
        int i = 0;
        return decode(s);
    }
}
