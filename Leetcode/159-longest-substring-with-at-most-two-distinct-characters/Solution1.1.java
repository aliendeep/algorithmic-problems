/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.
*/

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if(n <= 2)
            return n;

        Character p1 = s.charAt(0);
        // index of last seen first distinct character
        int lastP1 = 0;
        int end = 1;
        while(end < n && s.charAt(0) == s.charAt(end)){
            // update lastP1
            lastP1 = end;
            end++;
        }
    
        // s.charAt(end) is different than p1
        Character p2 = null;
        int lastP2 = end;
        if(end < n){
            p2 = s.charAt(end);
            end++;
        }
        
        // initial maxLength        
        int maxLength = end;
        int start = 0;
        for(; end < n; end++){
            char c = s.charAt(end);
            maxLength = Math.max(maxLength, end - start);
            // current character is the same as the p1, then update last occurrence
            if(p1.charValue() == c){
                lastP1 = end;
            }
            // current character is the same as the p2, then update last occurrence
            else if(p2.charValue() == c){
                lastP2 = end;
            }
            else{
                // Another distinct character other than p1 and p2
                // p1 occurreed before p2
                if(lastP2 > lastP1){
                    // update start position
                    start = lastP1 + 1;
                    // change both p1 and p2
                    p1 = p2;
                    p2 = c;
                    lastP1 = lastP2;
                    lastP2 = end;
                }
                // p2 occurred before p1
                else if(lastP1 > lastP2){
                    start = lastP2 + 1;
                    // Reset only p2
                    // update start position
                    p2 = c;
                    lastP2 = end;
                }
            }
        }
        // last window
        maxLength = Math.max(maxLength, end - start);
        return maxLength;
    }
}