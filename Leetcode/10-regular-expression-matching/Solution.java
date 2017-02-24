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

/*
Sample Input:
"aa"
"a"
"aab"
"c*a*b"
"abcd"
"d*"
"aasdfasdfasdfasdfas"
"aasdf.*asdf.*asdf.*asdf.*s"
Sample Output:
false
true
false
true
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


class Solution2 {
    public boolean matches(char sc, char pc) {
        return pc == '.' || sc == pc;
    }
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
                    dp[i][j] = dp[i-1][j-1] && matches(s.charAt(i-1), p.charAt(j-1));  
                }
                else{
                    // previous pattern char: p[j-2] (assume X)
                    // current pattern char: p[j-1] == *
                    // together, X*.
                    dp[i][j] = dp[i][j-2];  // X* matches 0 chars from s.
                    
                    for (int k = i; k >= 1; --k) {
                        if (!matches(s.charAt(k-1), p.charAt(j-2))) break;
                        
                        // if we are here, then we know that s[k-1], s[k], ... s[i-1] all matches p[j-2].
                        // 'dp[k-1][j-2]':
                        // - first (k-1) matched by first (j-2) chars of pattern.
                        dp[i][j] = dp[i][j] || dp[k-1][j-2];
                    }
                }
            }
        }
        return dp[n][m];
         
    }
}

// Memoization O(n^3)
class Solution3 {
    String s;
    String p;
    int n, m;
    int[][] dp;
    
    public boolean isCharMatch(char s, char p){
        return (s == p || p == '.');
    }
    
    public char sc(int x){  // x is 1-indexing
        return s.charAt(x-1);
    }
    public char pc(int x){  // x is 1-indexing
        return p.charAt(x-1);
    }
    
    public boolean match(int i, int j) {
        if(i == 0 && j == 0)    return true;
        // pattern is empty
        if(i == 0)              return false;
        if(dp[i][j] != -1)      return dp[i][j] == 1;
        boolean flag = false;
        if(pc(i) != '*'){
            if(j > 0){
                flag = isCharMatch(sc(j), pc(i)) && match(i-1, j-1);
            }
        }
        else{
            // pc(i) contains the *
            // pc(i-1) contains the pattern char
            char patternChar = pc(i-1);
            flag = match(i-2, j);
            for(int k=j; k>0; --k){
                if(!isCharMatch(sc(k), patternChar))
                    break;
                flag = flag || match(i-2, k-1);
                // early break
                if(flag)
                    break;
            }
        }
        dp[i][j] = flag ? 1 : 0;
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
        return match(m, n);
    }
}
