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