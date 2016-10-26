/*
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the Largest possible.
*/

import java.util.*;

public class SolutionMaximumNumber {
    // Stack
    public static String removeKdigits(String num, int k) {
        if(num.length() == 0 || k == 0)     return num;
        if(num.length() == k)               return "0";
            
        Deque<Character> stk = new LinkedList<>();
        for(int i=0; i<num.length(); i++){
            char c = num.charAt(i);
            // if previous number is smaller than current, pop it
            while(k > 0 && !stk.isEmpty() && stk.peekFirst() < c){
                stk.removeFirst();
                k--;
            }
            stk.addFirst(c);    
        }
        
        // For cases like: 111, 2222 etc
        while(k > 0){
            stk.removeFirst();
            k--;
        }
        
        StringBuffer result = new StringBuffer();
        while(!stk.isEmpty())
            result.append(stk.removeFirst());
        
        // remove extra 0s from the start before reverse
        int i = result.length()-1;
        while(i > 0){
            if(result.charAt(i) != '0')
                break;
            i--;    
        }
        result.setLength(i+1);
        result.reverse();
        return result.toString();
    }

    public static void main(String[] args){
        String r = removeKdigits("869", 2);
        System.out.println(r);
    }
}