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

// #############################################################################
// Cleaner: O(n^3) Solution
// dp[i][j] is true if s[0..i-1] matches p[0..j-1]
class Solution3 {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n+1][m+1];
        // init
        dp[0][0] = true;

        // Start i from 0 and j should be at least 1 as ("", "*") should return true
        for(int i=0; i<=n; ++i){
            for(int j=1; j<=m; ++j){
                if(p.charAt(j-1) != '*'){
                    if (i==0) continue;
                    dp[i][j] = dp[i-1][j-1] && (p.charAt(j-1) == '?' || s.charAt(i-1) == p.charAt(j-1));  
                }
                else{
                    // k - number of characters matched by *
                    // s[0 .. i-k-1] matches p[0..j-2] and p[j-1]('*') covers the s[i-k..i-1]
                    for(int k=0; k<=i; ++k)
                         dp[i][j] = dp[i][j] || dp[i-k][j-1]; 
                }
            }
        }
        return dp[n][m];
    }
}

// i & j reversed
class Solution4 {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        // init
        dp[0][0] = true;
        for(int i=1; i<=m; ++i){
            for(int j=0; j<=n; ++j){
                if(p.charAt(i-1) != '*'){
                    if(j == 0)  continue;
                    dp[i][j] = dp[i-1][j-1] && (p.charAt(i-1) == '?' || p.charAt(i-1) == s.charAt(j-1));
                }
                else{
                    for(int k=0; k<=j; ++k){
                        dp[i][j] = dp[i][j] || dp[i-1][k];  
                    }
                }
            }
        }
        return dp[m][n];
    }
}

// O(n^2)
class Solution5 {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        // init
        dp[0][0] = true;
        for(int i=1; i<=m; ++i){
            boolean prevFlag = false;
            for(int j=0; j<=n; ++j){
                prevFlag = prevFlag || dp[i-1][j];
                
                if(p.charAt(i-1) != '*'){
                    if(j == 0)  continue;
                    dp[i][j] = dp[i-1][j-1] && (p.charAt(i-1) == '?' || p.charAt(i-1) == s.charAt(j-1));
                }
                else{
                    dp[i][j] = dp[i][j] || prevFlag;
                }
            }
        }
        return dp[m][n];
    }
}

// Memoization : O(n^3)
class Solution6 {
    String s;
    String p;
    int n, m;
    int[][] dp;

    public int match(int i, int j) {
        if(i == 0 && j == 0)    return 1;
        // pattern is empty
        if(i == 0)              return 0;
        if(dp[i][j] != -1)      return dp[i][j];
        
        char pi = p.charAt(i-1);
        int flag = 0;
        if(pi != '*'){
            if(j > 0){
                char si = s.charAt(j-1);
                flag = ((si == pi || pi == '?') && match(i-1, j-1) == 1) ? 1 : 0;
            }
        }
        else{
            for(int k=j; k>=0; --k){
                if(match(i-1, k) == 1){
                    flag = 1;
                    break;
                }
            }
        }
        dp[i][j] = flag;
        return flag;
    }

    public boolean isMatch(String s1, String p1) {
        s = s1;
        p = p1;
        n = s.length();
        m = p.length();
        dp = new int[m+1][n+1];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        return match(m, n) == 1 ? true : false;
    }
}

// Memoization : O(n^2)
class Solution7 {
    String s;
    String p;
    int n, m;
    int[][] dp;
    int[][] cumuDp;
    
    // match(i, 0) || match(i, 1) || ... || match(i, j)
    public int cmatch(int i, int j) {
        if(i < 0 || j < 0)   return 0;
        if(cumuDp[i][j] != -1)
            return cumuDp[i][j];
        
        cumuDp[i][j] = (cmatch(i, j-1) == 1 || match(i, j) == 1) ? 1 : 0;
        return cumuDp[i][j];
    }

    public int match(int i, int j) {
        if(i == 0 && j == 0)    return 1;
        // pattern is empty
        if(i == 0)              return 0;
        if(dp[i][j] != -1)      return dp[i][j];
        
        char pi = p.charAt(i-1);
        int flag = 0;
        if(pi != '*'){
            if(j > 0){
                char si = s.charAt(j-1);
                flag = ((si == pi || pi == '?') && match(i-1, j-1) == 1) ? 1 : 0;
            }
        }
        else{
            // pi is the * character
            flag = cmatch(i-1, j);
        }
        dp[i][j] = flag;
        return flag;
    }

    public boolean isMatch(String s1, String p1) {
        s = s1;
        p = p1;
        n = s.length();
        m = p.length();
        dp = new int[m+1][n+1];
        for(int[] t : dp)
            Arrays.fill(t, -1);
        cumuDp = new int[m+1][n+1];
        for(int[] t : cumuDp)
            Arrays.fill(t, -1);

        return match(m, n) == 1 ? true : false;
    }
}
