/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
*/

public class Solution {
    public int firstUniqChar(String s) {
        int[] cnt = new int[26];
        int[] loc = new int[26];

        for(int i=0; i<s.length(); i++){
            cnt[s.charAt(i) - 'a']++;
            loc[s.charAt(i) - 'a'] = i;
        }
        
        int minIndex = s.length();
        for(int i=0; i<s.length(); i++){
            if(cnt[s.charAt(i) - 'a'] == 1)
                minIndex = Math.min(minIndex, loc[s.charAt(i) - 'a']);
        }
        return minIndex == s.length() ? - 1 : minIndex;
    }
}