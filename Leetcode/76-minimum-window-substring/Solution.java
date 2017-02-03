/*
Given a string S and a string T, find the minimum window in S which will contain 
all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the 
empty string "".

If there are multiple such windows, you are guaranteed that there will always 
be only one unique minimum window in S.
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

class Solution2 {
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

class Solution3 {
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
                // Check if we can remove the starting character (if the starting 
                // character does not exist or cur count contains more characters 
                // than required)                
                while(start < end && target[s.charAt(start)] < cur[s.charAt(start)]){
                    cur[s.charAt(start)]--;
                    start++;
                }
                if(minLen > (end + 1 - start)){
                    minLen = (end + 1) - start;
                    resultStart = start;
                }
                break;
            }
        }
        
        end++;
        // right now, substring is always valid
        for( ; end < n; end++){
            cur[s.charAt(end)]++;
            // Current s.charAt(end) contributes
            if(target[s.charAt(end)] < cur[s.charAt(end)]){
                while(target[s.charAt(start)] < cur[s.charAt(start)]){
                    cur[s.charAt(start)]--;
                    start++;
                }
                if(minLen > (end+1 - start)){
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

// EPI
public class Solution {
    public String minWindow(String s, String t) {
        int n = t.length();
        Map<Character, Integer> map = new HashMap<>();
        int remainingToCover = 0;
        for(int i=0; i<n; ++i){
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
            remainingToCover++;
        }
        int start = 0, end = 0;
        int rs = -1, len = -1;
        while(end < s.length()){
            Integer cnt = map.get(s.charAt(end));
            if(cnt != null){
                map.put(s.charAt(end), --cnt);
                if(cnt >= 0)
                    remainingToCover--;
            }
            while(remainingToCover == 0){
                if(rs == -1 || len > (end - start + 1)){
                    rs = start;
                    len = end - start + 1;
                }
                cnt = map.get(s.charAt(start));
                if(cnt != null){
                    map.put(s.charAt(start), ++cnt);
                    if(cnt > 0){
                        remainingToCover++;    
                    }
                }
                start++;
            }
            end++;
        }
        return rs == -1 ? "" : s.substring(rs, rs+len);
     }
}
