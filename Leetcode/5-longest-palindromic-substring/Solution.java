/*
Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and 
there exists one unique longest palindromic substring.
*/
public class Solution {
    // DP
    // Time Complexity: O(n^2)
    // Space Compleixty: O(n^2)
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        
        int maxLength = 1;
        int start = 0;
        // Initialization
        // 1 Length 
        for(int i=0; i<n; i++){
            dp[i][i] = true;
            maxLength = 1;
        }
            
        // 2 Length
        for(int i=0; i<n-1; i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                start = i;
                maxLength = 2;
            }
        }
        
        // All possible length
        for(int k=3; k<=n; k++){
            for(int i=0; i<n-k+1; i++){
                int j = i+k-1;
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]){
                    dp[i][j] = true;
                    if(maxLength < k){
                        maxLength = k;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start+maxLength);
    }
}

// Time: O(n^2)
class Solution2 {
    public String expandPalindrome(String s, int i, int j){
        int n = s.length();
        while(i >= 0 && j < n && (s.charAt(i) == s.charAt(j))){
            i--;
            j++;
        }
        return s.substring(i+1, j);
    }
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n <= 1)
            return s;
        String longest = s.substring(0, 1);
        int maxLength = 1;
        for(int i=0;i<n; i++){
            String oddLengthPalindrome = expandPalindrome(s, i, i);
            if(oddLengthPalindrome.length() > maxLength){
                maxLength = oddLengthPalindrome.length();
                longest = oddLengthPalindrome;
            }
            String evenLengthPalindrome = expandPalindrome(s, i, i+1);
            if(evenLengthPalindrome.length() > maxLength){
                maxLength = evenLengthPalindrome.length();
                longest = evenLengthPalindrome;
            }
        }    
        return longest;
    }
}

