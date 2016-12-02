/*

Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n =  s.length();
        if(n <= 1)
            return n;

        HashSet<Character> map = new  HashSet<Character>();
        int start = 0, end = 0;
        int maxLength = 0;
        while(start < n && end < n){  
            char c = s.charAt(end);
            // remove elements from the start
            if(map.contains(c)){
                map.remove(s.charAt(start));
                start++;
            }
            else{
                map.add(c);
                end++;
                maxLength = Math.max(maxLength, end-start);
            }
        }
        return maxLength;
    }
}

// https://leetcode.com/articles/longest-substring-without-repeating-characters/
// O(1) space
class Solution2 {
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
class Solution3 {
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