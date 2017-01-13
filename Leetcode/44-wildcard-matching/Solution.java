/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/

public class Solution {
    boolean isCharMatch(char s, char p){
        return s == p || p == '?';
    }
    
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        int i = 0;
        int j = 0;
        int starPos = -1;
        int matchIndex = 0;
        while(i < m){
            // Character Match, Advance both indices
            if(j < n && isCharMatch(s.charAt(i), p.charAt(j))){
                i++;
                j++;
            }
            else if(j < n && p.charAt(j) == '*'){
                starPos = j;
                matchIndex = i;
                // advance p
                j++;
            }
            // Star found in the previous position 
            // Advance s pointer
            else if(starPos != -1){
                // reset j, as j can be increment in the first if part
                j = starPos + 1;
                matchIndex++;
                i = matchIndex;
            }
            else 
                return false;
        }
        
        while(j < n && p.charAt(j) == '*')
            j++;
        return j == n;
    }
}

// dp[i][j] is true if s[0..i-1] matches p[0..j-1]
class Solution2 {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n+1][m+1];
        // init
        dp[0][0] = true;
        for(int i=1; i<=n; ++i){
            // if pattern is empty
            dp[i][0] = false;
        }
        for(int j=1; j<=m; ++j){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = true;
            }
            else
                break;
        }
        for(int i=1; i<=n; ++i){
            for(int j=1; j<=m; ++j){
                if(p.charAt(j-1) != '*'){
                    dp[i][j] = dp[i-1][j-1] && (p.charAt(j-1) == '?' || s.charAt(i-1) == p.charAt(j-1));  
                }
                else{
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[n][m];
    }
}
