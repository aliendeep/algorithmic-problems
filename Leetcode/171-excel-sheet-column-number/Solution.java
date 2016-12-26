/*
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
*/
public class Solution {
    public int titleToNumber(String s) {
        int n = 0;
        for(int i=0; i<s.length(); i++){
            n = n*26 + (s.charAt(i) - 'A' + 1);
        }
        return n;
    }
}

// Recursive
public class Solution {
    public int titleToNumber(String s) {
        int n = s.length();
        return n == 0 ? 0 : 26*titleToNumber(s.substring(0, n-1)) + (s.charAt(n-1) - 'A' + 1); 
    }
}
