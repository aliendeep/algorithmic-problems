/*
Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", 
so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".

Now we have another string p. Your job is to find out how many unique non-empty substrings of 
p are present in s. In particular, your input is the string p and you need to output the number 
of different non-empty substrings of p in the string s.

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
Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
*/
public class Solution {
    public int findSubstringInWraproundString(String p) {
        int n = p.length();
        if(n == 0)
            return 0;
        int[] len = new int[26];
        Arrays.fill(len, 0);

        int prev = p.charAt(0) - 'a';
        len[prev] = 1;
        int cur;
        int l = 1;
        for(int i=1; i<n; ++i){
            cur = p.charAt(i) - 'a';
            if((prev+1)%26 == cur){
                l++;
                len[cur] = Math.max(len[cur], l);                
            }
            else{
                // reset 
                l = 1;
                len[cur] = Math.max(len[cur], l);                
            }
            prev = cur;
        }
        int sum = 0;
        for(int x : len)
            sum += x;
        return sum;
    }
}