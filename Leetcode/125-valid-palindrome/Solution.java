/*
Given a string, determine if it is a palindrome, considering only alphanumeric 
characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask 
during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/
public class Solution {
    public boolean isPalindrome(String s) {
        if(s.length() == 0)
            return true;
        String str = s.toLowerCase();
        int n = str.length();
        for(int i=0, j=n-1; i<j; i++, j--){
            while(i < j && !Character.isLetterOrDigit(str.charAt(i)))
                i++;
            while(i < j && !Character.isLetterOrDigit(str.charAt(j)))
                j--;
            if(str.charAt(i) != str.charAt(j))
                return false;
        }
        return true;
    }
}