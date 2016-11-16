/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
*/
public class Solution {
    public String process(String s){
        // S: Index of the first occurrence of the string
        Map<Character, Integer> patternMap = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            if(!patternMap.containsKey(s.charAt(i))){
                patternMap.put(s.charAt(i), i);
            }
        }
        
        StringBuffer x = new StringBuffer();
        for(int i=0; i<s.length(); i++)
            x.append(patternMap.get(s.charAt(i)));
            
        return x.toString();
    }
    
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length())    return false;
        if(s.length() == 0)               return true;
        String x = process(s);        
        String y = process(t);
        return x.compareTo(y) == 0;
    }
}