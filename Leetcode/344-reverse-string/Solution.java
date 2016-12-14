/*
Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".
*/
public class Solution {
    // As for writing a character at an index: java Strings are immutable, 
    // so you can't change individual characters.    
    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i=s.length()-1; i>=0; i--)
            sb.append(s.charAt(i));
        return sb.toString();
    }
}

// Recursive
class Solution2 {
    public String reverseString(String s) {
        int len = s.length();
        if(len <= 1)
            return s;
        
        int mid = len/2;
        return reverseString(s.substring(mid)) + reverseString(s.substring(0, mid));
    }
}
