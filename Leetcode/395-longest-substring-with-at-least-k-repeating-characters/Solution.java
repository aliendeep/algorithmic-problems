/*
Find the length of the longest substring T of a given string (consists of l
owercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
*/

public class Solution {
    // Divide & Conquer
    public int longestSubstring(String s, int k) {
        return longestSubstringHelper(s, k, 0, s.length());
    }
    
    // end exclusive
    public int longestSubstringHelper(String s, int k, int start, int end){
        if(s.length() < k || end < start)
            return 0;
        // lowercase characters
        int[] cnt = new int[26];
        for(int i=start; i<end; i++){
            cnt[s.charAt(i) - 'a']++;
        }
        
        // for all lowercase letters
        for(int i=0; i<26; i++){
            if(cnt[i] == 0)
                continue;
            // the character can't be a part of the result
            if(cnt[i] < k){
                for(int j=start; j<end; j++){
                    if(s.charAt(j) == i + 'a'){
                        int leftLen = longestSubstringHelper(s, k, start, j);
                        int rightLen = longestSubstringHelper(s, k, j+1, end);
                        return Math.max(leftLen, rightLen);
                    }
                }
            }
        }
        // all characters of the array contains >= k elements
        return end-start;
    }
}   
