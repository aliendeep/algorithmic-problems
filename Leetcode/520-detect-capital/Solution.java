/*
Given a word, you need to judge whether the usage of capitals in it is right or 
not.

We define the usage of capitals in a word to be right when one of the following 
cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, 
like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
Example 1:
Input: "USA"
Output: True
Example 2:
Input: "FlaG"
Output: False
Note: The input will be a non-empty word consisting of uppercase and lowercase 
latin letters.
*/

public class Solution {
    public boolean allSameCase(String word, int start, boolean lower){
        for(int i=start; i<word.length(); ++i){
            char c = word.charAt(i);
            if(lower){
                if(!Character.isLowerCase(c))     return false;    
            } 
            else if(!Character.isUpperCase(c))    return false;    

        }    
        return true;
    }
    
    public boolean detectCapitalUse(String word) {
        if(word.length() <= 1)      return true;
        char f = word.charAt(0);
        if(Character.isLowerCase(f))          
            return allSameCase(word, 1, true);

        char s = word.charAt(1);
        if(Character.isUpperCase(f))          
            return allSameCase(word, 1, false) || allSameCase(word, 1, true);
        return false;
    }
}