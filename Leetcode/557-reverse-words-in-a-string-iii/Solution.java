/*
Given a string, you need to reverse the order of characters in each word within 
a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not 
be any extra space in the string.
*/

public class Solution {
    public String reverseWords(String s) {
        String[] tokens = s.split(" ");
        
        StringBuilder result = new StringBuilder();
        for(String token : tokens) {
            for(int i=token.length()-1; i>=0; --i) {
                result.append(token.charAt(i));
            }
            result.append(" ");
        }
        return result.toString().trim();
    }
}
