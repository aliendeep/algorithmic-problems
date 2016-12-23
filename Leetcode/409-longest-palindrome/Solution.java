/*
iven a string which consists of lowercase or uppercase letters, find the length 
of the longest palindromes that can be built with those letters.

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
        
        int even = 0, odd = 0;
        boolean foundOdd = false;
        for(int i=0; i<256; i++){
            if(cnt[i] == 0)
                continue;
            if(cnt[i] % 2 == 0)
                even += cnt[i];
            else{
                // add even contribution of odd characters
                odd += (cnt[i] > 1 ? cnt[i] - 1: 0);
                foundOdd = true;
            }
        }
        return even + (foundOdd ? odd + 1 : 0);
    }
}

class Solution2 {
    // Alternative: use set to count odd occurrences
    public int longestPalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for(char c : s.toCharArray()){
            // Already exists
            if(!set.add(c))
                set.remove(c);
        }
        int odd = set.size();
        // Can include at most 1 full odd length
        return s.length() - ((odd == 0) ? 0 : odd - 1);
    }
}
