/*
iven a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
*/

public class Solution {
    public int longestPalindrome(String s) {
        int[] cnt = new int[256];
        for(int i=0; i<s.length(); i++){
            cnt[s.charAt(i)]++;
        }
        
        int even = 0, odd = 0, maxOdd = 0;
        for(int i=0; i<256; i++){
            if(cnt[i] == 0)
                continue;
            if(cnt[i] % 2 == 0)
                even += cnt[i];
            else{
                // add even contribution
                odd += (cnt[i] > 1 ? cnt[i] - 1: 0);
                maxOdd = Math.max(maxOdd, cnt[i]);
            }
        }
        int len = even;
        if(maxOdd <= 1)
            return len + maxOdd;
        
        len += odd + 1;
        return len;
    }
}