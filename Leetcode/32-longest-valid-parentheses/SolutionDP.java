public class Solution {
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