// https://leetcode.com/articles/longest-substring-without-repeating-characters/
// O(1) space
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n <= 1)
            return n;
        int[] pos = new int[256];
        Arrays.fill(pos, -1);
        
        int start = 0, end = 0;
        // create the first valid window
        while(end < n && pos[s.charAt(end)] == -1){
            pos[s.charAt(end)] = end;
            end++;
        }
        int maxLength = end - start;
        while(end < n){
            int c = s.charAt(end);
            if(pos[c] != -1){
                maxLength = Math.max(maxLength, end - start);
                // consider the case : abba
                start = (pos[c] + 1 > start) ? pos[c] + 1 : start;
            }
            // update position
            pos[c] = end;
            end++;
            maxLength = Math.max(maxLength, end - start - 1);
        }
        maxLength = Math.max(maxLength, end - start);
        return maxLength;
    }
}

// Shorter
class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int[] pos = new int[256];
        int maxLength = 0;
        for(int start = 0, end = 0; end < n; end++){
            int c = s.charAt(end);
            // Extend the range [start .. end]
            start = Math.max(start, pos[c]);
            maxLength = Math.max(maxLength, end - start+1);
            pos[c] = end+1;
        }
        return maxLength;
    }
}