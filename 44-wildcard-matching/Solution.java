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