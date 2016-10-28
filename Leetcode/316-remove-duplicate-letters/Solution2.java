/*
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
*/
public class Solution {
    // Greedy
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        if(n == 0)
            return "";
        int[] cnt = new int[26];
        for(int i=0; i<n; i++){
            cnt[s.charAt(i) - 'a']++;
        }
        int lowestPosition = 0;
        // Find the postion 
        for(int i=0; i<n; i++){
            if(s.charAt(i) < s.charAt(lowestPosition))
                lowestPosition = i;
            cnt[s.charAt(i) - 'a']--;
            if(cnt[s.charAt(i) - 'a'] == 0)
                break;
        }
        
        char firstChar = s.charAt(lowestPosition);
        // replace all: first parameter String
        String rest = s.substring(lowestPosition+1).replaceAll("" + firstChar, "");
        return  firstChar + removeDuplicateLetters(rest);             
    }
}