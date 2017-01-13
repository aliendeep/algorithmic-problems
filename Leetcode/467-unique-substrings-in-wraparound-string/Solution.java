/*
Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", 
so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".

Now we have another string p. Your job is to find out how many unique non-empty 
substrings of p are present in s. In particular, your input is the string p and 
you need to output the number of different non-empty substrings of p in the string s.

Note: p consists of only lowercase English letters.

Example 1:
Input: "a"
Output: 1

Explanation: Only the substring "a" of string "a" is in the string s.
Example 2:
Input: "cac"
Output: 2
Explanation: There are two substrings "a", "c" of string "cac" in the string s.
Example 3:
Input: "zab"
Output: 6
Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string 
"zab" in the string s.
*/
public class Solution {
    public int findSubstringInWraproundString(String p) {
        int n = p.length();
        if(n == 0)  return 0;
        int[] cnt = new int[26];
        int maxLen = 1;
        int prev = p.charAt(0) - 'a';
        cnt[prev] = 1;
        for(int i=1; i<n; ++i){
            int cur = p.charAt(i) - 'a';
            if((prev + 1) % 26 == cur){
                maxLen++;
            }
            else
                maxLen = 1;

            cnt[cur] = Math.max(cnt[cur], maxLen);
            prev = cur;
        }
        int sum = 0;
        for(int x : cnt)
            sum += x;
        return sum;
    }
}
