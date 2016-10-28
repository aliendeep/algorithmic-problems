/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.
*/
public class Solution {
    // O(n^2)
    public int maxProduct(String[] words) {
        int n = words.length;
        if(n == 0)      return 0;
        boolean[][] cnt = new boolean[n][26];
        
        for(int k=0; k<n; ++k){
            String t = words[k];
            for(int i=0; i<t.length(); ++i){
                cnt[k][t.charAt(i) - 'a'] = true;
            }
        }
        
        int maxLen = 0;
        for(int i=0; i<n-1; ++i){
            for(int j=1; j<n; ++j){
                int k = 0;
                for(k=0; k<26; ++k){
                    // if common character
                    if(cnt[i][k] && cnt[j][k]){
                        break;
                    }
                }
                if(k == 26){
                    maxLen = Math.max(maxLen, words[i].length()*words[j].length());
                }
            }
        }
        return maxLen;
    }
}