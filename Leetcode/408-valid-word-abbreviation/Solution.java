/*
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.
*/

// "a", "01" : return false
public class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        int n = word.length();
        int m = abbr.length();
        int num = 0;
        while(i < m){
            char c = abbr.charAt(i);
            if(Character.isDigit(c)){    
                // Case: "a", "01"
                if(num == 0 && c == '0')
                    return false;
                num = num*10 + c-'0';
            }
            else{
                // skip num characters
                j += num;
                //System.out.println(num);
                if(j >= n || word.charAt(j) != abbr.charAt(i))
                    return false;
                j++;    
                // reset
                num = 0;                
            }
            i++;
        }
        // last part
        if(num > 0){
            j += num;
            if(j != n)      return false;
        }        
        return j == n && i == m;
    }
}