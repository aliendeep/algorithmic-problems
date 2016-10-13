/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/

public class Solution {
    public String minWindow(String s, String t) {
        int[] target = new int[256];

        Set<Character> charsToCheck = new HashSet<>();
        int n = s.length();
        int tLen = t.length();
        for(int i=0; i<tLen; i++){
            target[t.charAt(i)]++;
            charsToCheck.add(t.charAt(i));
        }        

        // Find first valid minimum window
        int counter = tLen;
        int[] cur = new int[256];
        int start = 0, end;
        int minLen = Integer.MAX_VALUE;
        int resultStart = 0;
        
        for(end = 0; end < n; end++){
            cur[s.charAt(end)]++;
            if(cur[s.charAt(end)] >= target[s.charAt(end)]){
                charsToCheck.remove(s.charAt(end));
            }
            
            if(charsToCheck.size() == 0){
                // Check if we can remove the starting character (if the starting character does not exist or cur count contains more characters than required)                
                while(start < end && target[s.charAt(start)] < cur[s.charAt(start)]){
                    cur[s.charAt(start)]--;
                    start++;
                }
                if(minLen > (end + 1 - start)){
                    minLen = (end + 1) - start;
                    resultStart = start;
                }
            }
        }
        
       // Empty String
        if(minLen == Integer.MAX_VALUE)
            return "";
        return s.substring(resultStart, resultStart+minLen);
    }
}