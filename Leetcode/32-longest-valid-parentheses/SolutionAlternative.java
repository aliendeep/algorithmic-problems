/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/
public class Solution {
    // Time & Space: O(n)
    public int longestValidParentheses(String s) {
        // contains indices
        Deque<Integer> stk = new LinkedList<>();
        int maxLength = 0;
        int end = -1;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '(')
                stk.addFirst(i);
            //If a prefix of a string is not a valid one, then no extension of that prefix will be considered as a valid.
            // unmatched ) char
            else if(stk.isEmpty()){
                end  = i;
            }
            else{            
                // pop index of the last matching (
                stk.removeFirst();
                // When we process the right parenthesis for a given prefix, and if its matches (popped), then we can use the index of the top of the stack, as the starting index 
                // if the stack is empty then use the index when the stack was clear.
                int start = stk.isEmpty() ? end : stk.peekFirst();
                maxLength = Math.max(maxLength, i - start);
            }
        }
        return maxLength;
    }
}