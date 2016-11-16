/*
iven two strings s and t, determine if they are isomorphic.

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
    public boolean update(String s, String t){
    int n = s.length();
    if(n == 0)
        return true;
    Map<Character, Character> map = new HashMap<>();
    for(int i=0; i<n; ++i){
        if(!map.containsKey(s.charAt(i)))
            map.put(s.charAt(i), t.charAt(i));
        else if(map.put(s.charAt(i), t.charAt(i)) != t.charAt(i))
            return false;
    }        
    return true;
    }
    public boolean isIsomorphic(String s, String t) {
        return (update(s, t) && update(t, s));
    }
}