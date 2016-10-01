/*
Write a function to find the longest common prefix string amongst an array of strings.
*/

// String Manipulation
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        if(strs.length == 1)
            return strs[0];
        
        // maximum possible length of the result
        int maxPossibleLength = strs[0].length();
        for(int i=0; i<maxPossibleLength; i++){
            // for all other strings
            for(int j=1; j<strs.length; j++){
                // if the length of the current string is shorted than i or characters don't match
                if(i > strs[j].length() - 1  || (strs[0].charAt(i) != strs[j].charAt(i)))
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }
}