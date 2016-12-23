/*
Given a string, find the first non-repeating character in it and return it's index. 
If it doesn't exist, return -1.

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
        Arrays.fill(cnt, 0);
        
        for(int i=0; i<s.length(); i++){
            cnt[s.charAt(i) - 'a']++;
        }

        for(int i=0; i<s.length(); i++)
            if(cnt[s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
}

class Solution2 {
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

class Solution3 {
    // Scan the string once
    public int firstUniqChar(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        int[] index = new int[26];
        Arrays.fill(index, n+1);
        
        // Keep the index 
        for(int i=0; i<n; i++){
            cnt[s.charAt(i) - 'a']++;
            index[s.charAt(i) - 'a'] = i;
        }
        
        int minIndex = n+1;
        for(int i=0; i<26; ++i){
            if(cnt[i] > 1)
                continue;
            minIndex = Math.min(minIndex, index[i]);
        }
        return minIndex == n+1 ? -1 : minIndex;
    }
}
