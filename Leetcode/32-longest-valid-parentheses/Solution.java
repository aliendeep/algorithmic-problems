/*
Given a string containing just the characters '(' and ')', find the length of 
the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is 
"()()", which has length = 4.
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

class Solution2 {
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
            // If a prefix of a string is not a valid one, then no extension of that prefix will be considered as a valid.
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

class Solution3 {
    // longest[i] denotes the longest set of parenthesis ending at index i
    public int longestValidParentheses(String s) {
        // DP
        int n = s.length();
        if(n <= 1)
            return 0;
        
        int maxLength = 0;
        int[] longest = new int[n+1];
        
        for(int i=1;i<n; i++){
            // update longest length only when ) found
            if(s.charAt(i) == ')' && i - longest[i-1] - 1 >= 0 && s.charAt(i-longest[i-1]-1) == '('){
                longest[i] =  longest[i-1] + 2 + (i-longest[i-1]-2 >= 0 ? longest[i-longest[i-1]-2] : 0);
                maxLength = Math.max(maxLength, longest[i]);
            }
        }
        return maxLength;
    }
}

class Solution4 {
    // Two traversals
    public int longestValidParentheses(String s) {
        int n = s.length();
        int left = 0, right = 0;
        int maxLen = 0;
        for(int i=0; i<n; ++i){
            if(s.charAt(i) == '(')
                left++;
            else
                right++;
            if(left == right){
                maxLen = Math.max(maxLen, 2*right);
            }
            else if(right > left){
                left = 0;
                right = 0;
            }
        }
        left = 0;
        right = 0;
        for(int i=n-1; i>=0; --i){
            if(s.charAt(i) == '(')
                left++;
            else
                right++;
            if(left == right){
                maxLen = Math.max(maxLen, 2*left);
            }
            else if(left > right){
                left = 0;
                right = 0;
            }
        }
        return maxLen;
    }
}
