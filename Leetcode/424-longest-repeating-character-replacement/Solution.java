/*
Given a string that consists of only uppercase English letters, you can replace 
any letter in the string with another letter at most k times. Find the length of 
a longest substring containing all repeating letters you can get after 
performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
*/

public class Solution {
    // Replace any letter in the string with another letter at most k times
    // Length of the substring - frequency of the maxOccuringCharacter <= k
    // Sliding window
    public int characterReplacement(String s, int k) {
        int start = 0, end = 0;
        int maxLength = 0;
        int maxOccuringCharacterCnt = 0;
        int[] cnt = new int[26];
        
        for(end = 0; end<s.length(); end++){
            int c = s.charAt(end) - 'A';
            cnt[c]++;
            if(maxOccuringCharacterCnt < cnt[c]){
                maxOccuringCharacterCnt = cnt[c];    
            }
            // Adjust window
            if(end - start + 1 - maxOccuringCharacterCnt > k){
                cnt[s.charAt(start) - 'A']--;
                if(cnt[s.charAt(start) - 'A'] + 1 == maxOccuringCharacterCnt){    
                    int r = 0;
                    // find the next maxOccuringCharacter
                    for(int i=0; i<26; i++){
                        r = Math.max(r, cnt[i]);
                    }
                    maxOccuringCharacterCnt = r;
                }
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
}

// Cleaner
class Solution2 {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int start = 0;
        int maxCnt = 0;
        int maxLen = 0;
        int[] cnt = new int[26];
        for(int end = 0; end < n; ++end){
            int c = s.charAt(end) - 'A';
            cnt[c]++;
            // max Occurring character
            maxCnt = Math.max(maxCnt, cnt[c]);
            // k replacement done (Maximum allowable length)
            // the following while loop is not necessary as the condition will violate
            // only for the new character
            while(k < end - start + 1 - maxCnt){
                // remove the first character of the window
                cnt[s.charAt(start) - 'A']--;
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1); 
        }
        return maxLen;
    }
}

// Better than Solution 2
class Solution3 {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int start = 0;
        int maxCnt = 0;
        int maxLen = 0;
        int[] cnt = new int[26];
        for(int end = 0; end < n; ++end){
            int c = s.charAt(end) - 'A';
            cnt[c]++;
            // max Occurring character
            maxCnt = Math.max(maxCnt, cnt[c]);
            // k replacement done (Maximum allowable length)
            if(k < end - start + 1 - maxCnt){
                // remove the first character of the window
                cnt[s.charAt(start) - 'A']--;
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1); 
        }
        return maxLen;
    }
}
