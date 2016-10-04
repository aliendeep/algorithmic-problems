/*
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

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
    // Find the longest palindrome substring starts from index 0. Answer: reverse part of rest of subtring + s
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