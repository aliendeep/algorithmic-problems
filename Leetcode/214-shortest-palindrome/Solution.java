/*
Given a string S, you are allowed to convert it to a palindrome by adding characters 
in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".
*/

public class Solution {
    // O(n^2) - Manacher's Algorithm
    int start = 0, end = 0;
    public void expandPalindromeAroundCenter(String s){
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
            start--;
            end++;
        }
    }
    // Find the longest palindrome substring starts from index 0. 
    // Answer: reverse part of rest of substring + s
    public String shortestPalindrome(String s) {
        int n = s.length();
        int maxLen = 0;
        for(int i=0;i<n; i++){
            start = i;
            end = i;
            expandPalindromeAroundCenter(s);
            // expanded upto 0
            if(start == -1 && maxLen < end){
                maxLen = end;
            }
            start = i;
            end = i+1;
            expandPalindromeAroundCenter(s);
            // expanded upto 0
            if(start == -1 && maxLen < end){
                maxLen = end;
            }
        }
        if(maxLen != -1){
            StringBuilder rev = new StringBuilder(s.substring(maxLen)).reverse();
            rev.append(s);
            return rev.toString();
        }
        return new StringBuilder(s.substring(1)).reverse().toString() + s; 
    }
}

// O(n) Solution
// https://discuss.leetcode.com/topic/27261/clean-kmp-solution-with-super-detailed-explanation/
class Solution2 {
    // KMP Modification
    // Compute lps array
    public String shortestPalindrome(String s) {
        if(s.length() <= 1)     return s;
        StringBuilder rev = new StringBuilder(s);
        rev.reverse();
        StringBuilder r = new StringBuilder();
        r.append(s);
        r.append("#");
        r.append(rev);
        
        int n = r.length();
        int[] lps = new int[n];
        // Compute lps array
        int len = 0;
        int i = 1;
        while(i < n){
            if(r.charAt(len) == r.charAt(i)){
                len++;
                lps[i] = len;
                i++;
            }
            else{
                if(len != 0){
                    len = lps[len-1];
                }
                else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
        // Can be appended only at the beginning
        return new StringBuilder(s.substring(lps[n - 1])).reverse().toString() + s;
    }
}

// Recursive Solution O(n^2)
class Solution3 {
    public String shortestPalindrome(String s) {
        int n = s.length();
        int j = 0;
        for(int i=n-1; i>=0; --i){
            if(s.charAt(i) == s.charAt(j))
                j++;
        }
        if(j == n)      return s;
        String suffix = s.substring(j);
        return new StringBuilder(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix; 
    }
}