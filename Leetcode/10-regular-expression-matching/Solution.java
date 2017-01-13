/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/

// DP
public class Solution {
    public boolean isCharMatch(char s, char p){
        return (s == p || p == '.');
    }
    
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // dp[i][j] is true if s[0..i-1] matches p[0..j-1]
        boolean[][] dp = new boolean[m+1][n+1];

        dp[0][0] = true;
        // Initialization
        for(int i=1; i<=m; i++)
            dp[i][0] = false;

        // The string p[0.., j - 3, j - 2, j - 1] matches empty s if and only if p[j - 1] is '*' and p[0..j - 3] matches empty string
        for(int j=1; j<=n; j++){
            dp[0][j] = false;
            if(j > 1){
                dp[0][j] = (p.charAt(j-1) == '*') && dp[0][j-2];
            }
        }
        
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(p.charAt(j-1) != '*')
                    dp[i][j] = dp[i-1][j-1] && isCharMatch(s.charAt(i-1), p.charAt(j-1)); 
                else{
                    // '*' Matches zero or at least one time
                    dp[i][j] = dp[i][j-2] || (dp[i-1][j] && isCharMatch(s.charAt(i-1), p.charAt(j-2)));
                }
            }
        }
   
        return dp[m][n];
    }
}

