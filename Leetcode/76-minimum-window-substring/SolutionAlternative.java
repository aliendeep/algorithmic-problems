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

class Solution {
    public boolean isValid(int[] cur, int[] target){
        for(int i = 0; i<target.length; i++){
            if(target[i] > cur[i])
                return false;
        }        
        return true;
    }    
    
    public String minWindow(String s, String t) {
        int[] target = new int[256];
        for(int i=0; i<t.length(); i++)
            target[t.charAt(i)]++;
        
        int tn = t.length();
        int[] cur = new int[256];
        int n = s.length();
        int minLen = n + 1;
        int start = 0, end = 0;
        int rStart = 0, rEnd = 0;
        while(end < n){
            cur[s.charAt(end++)]++;
            if(isValid(cur, target) == true){
                // remove character until it remains valid
                while((end - start) > tn && start < end){ 
                    char r = s.charAt(start);
                    // check if we can remove the starting character (if the starting character does not exist or cur count contains more characters than required)
                    if(target[r] == 0 || target[r] < cur[r]){
                        cur[r]--;
                        start++;
                    }
                    else
                        break;
                }
                if(minLen > end - start){
                    minLen = end - start;
                    rStart = start;
                    rEnd = end;
                }
            }
        }
        return s.substring(rStart, rEnd);
    }
}
