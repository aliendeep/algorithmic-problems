/*
Given a positive integer, return its corresponding column title as appear in an 
Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
*/
public class Solution {
    // 1 is mapped to A, not 0
    public String convertToTitle(int n) {
        StringBuilder r = new StringBuilder();
        while(n > 0){
            n--;
            r.append((char)(n%26 + 'A'));
            n = n/26;
        }
        r.reverse();
        return r.toString();
    }
}

// Recursive
class Solution2 {
    public String convertToTitle(int n) {
        return n == 0 ? "" :  convertToTitle(--n/26) + (char)('A' + n % 26);
    }
}