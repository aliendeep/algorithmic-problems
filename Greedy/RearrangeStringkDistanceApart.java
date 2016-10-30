/*
https://leetcode.com/problems/rearrange-string-k-distance-apart/

Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
str = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
str = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.
Example 3:
str = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.
*/

public class RearrangeStringkDistanceApart {
    public int getNextValidIndex(int index, int[] cnt, int[] validPosition){
        int maxCount = 0;
        int nextIndex = -1;
        for(int i=0; i<26; i++){
            // Index should be >= minimum viable valid index
            if(cnt[i] > 0 && validPosition[i] <= index && maxCount < cnt[i]){
                 maxCount = cnt[i];
                 nextIndex = i;
            }
        }
        return nextIndex;
    }
    
    // Greedy, Find the valid character with highest occurrence
    public String rearrangeString(String str, int k) {
        int n = str.length();
        if(n == 0)          return "";
        // All input strings are given in lowercase letters
        int[] cnt = new int[26];
        for(int i=0; i<n; ++i){
            cnt[str.charAt(i) - 'a']++;
        }
        
        // next valid position of char i + 'a'
        int[] validPosition = new int[26];
        StringBuilder result = new StringBuilder();
        for(int i=0; i<n; i++){
            int nextValidCharIndex = getNextValidIndex(i, cnt, validPosition);
            if(nextValidCharIndex == -1)     return "";
            // Decrease Count
            cnt[nextValidCharIndex]--;
            // next valid position
            // At least distance k from each other.
            validPosition[nextValidCharIndex] = i+k;
            result.append((char)(nextValidCharIndex + 'a'));
        }
        return result.toString();
    }
}