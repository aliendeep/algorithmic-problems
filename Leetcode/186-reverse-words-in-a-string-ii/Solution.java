/*
Given an input string, reverse the string word by word. A word is defined as a 
sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are 
always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?

Related problem: Rotate Array
*/

public class Solution {
    void reverse(char[] s, int start, int end){
        for(int i=start, j=end-1; i<j; i++, j--){
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
        }
    }    
    public void reverseWords(char[] s) {
        int n = s.length;
        // reverse the whole array
        reverse(s, 0, n);
       
        int start = 0;
        int end = 0;
        while(end < n){
            // Found a space
            if(s[end] == ' '){
                reverse(s, start, end);
                // reset start (skip this space)
                start = end + 1;
            }            
            end++;
        }
        // reverse the last word
        reverse(s, start, end);
    }
    public static void main(String[] args){
        Solution ob = new Solution();
        String str = "the sky is blue";
        char[] s = str.toCharArray();
        ob.reverseWords(s);
        for(char c : s)
            System.out.print(c);
    }
}
