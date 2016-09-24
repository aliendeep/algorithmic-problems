// Manacher's Algorithm
class Solution {
    public String expandPalindrome(String s, int i, int j){
        int n = s.length();
        while(i >= 0 && j < n && (s.charAt(i) == s.charAt(j))){
            i--;
            j++;
        }
        return s.substring(i+1, j);
    }
    // Time: O(n^2)
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n <= 1)
            return s;
        String longest = s.substring(0, 1);
        int maxLength = 1;
        for(int i=0;i<n; i++){
            String oddLengthPalindrome = expandPalindrome(s, i, i);
            if(oddLengthPalindrome.length() > maxLength){
                maxLength = oddLengthPalindrome.length();
                longest = oddLengthPalindrome;
            }
            String evenLengthPalindrome = expandPalindrome(s, i, i+1);
            if(evenLengthPalindrome.length() > maxLength){
                maxLength = evenLengthPalindrome.length();
                longest = evenLengthPalindrome;
            }
        }    
        return longest;
    }
}