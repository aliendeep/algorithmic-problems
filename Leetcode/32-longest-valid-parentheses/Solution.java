/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/


public class Solution {
    // Time & Space: O(n)
    public int longestValidParentheses(String s) {
        // Stack contains indices of the left parenthesis
        Deque<Integer> stk = new LinkedList<>();
        stk.push(-1);
        int maxLength = 0;
        // Update the result when we find a valid parenthesis
        for(int i=0; i<s.length(); i++){
            // matched pair (> 1 as pushed -1 as the first)
            if(s.charAt(i) == ')' && stk.size() > 1 && s.charAt(stk.peekFirst()) == '('){
                stk.removeFirst();
                // If we find a pair, throw this pair away and see how big the gap is between current and previous invalid one.
                maxLength = Math.max(maxLength, i - stk.peekFirst());
            }
            else
                stk.addFirst(i);
        }
        return maxLength;
    }
}