/*
https://leetcode.com/problems/remove-k-digits/
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
*/
// Greedy

public class MinimumNumberRemoveKDigit {
    // Stack
    public String removeKdigits(String num, int k) {
        if(num.length() == 0 || k == 0)         return num;
        if(num.length() == k)                   return "0";
            
        Deque<Character> stk = new LinkedList<>();
        for(int i=0; i<num.length(); i++){
            char c = num.charAt(i);
            // While previous number is larger than current, pop it
            while(k > 0 && !stk.isEmpty() && stk.peekFirst() > c){
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
}