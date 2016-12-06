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

// map.put returns the previous value of key or null 
// Single map
class Solution2 {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        if(n == 0)
            return true;
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer i=0; i<n; ++i){
            int a = s.charAt(i);
            // need to pad char of b with some offset.
            int b = t.charAt(i) + 256;

            if(map.put(a, i) != map.put(b, i)){
                return false;
            }
        }        
        return true;
    }
}

// Alternative: three sets
class Solution3 {
    public boolean isIsomorphic(String s, String t) {
        Set<Character> s1 = new HashSet<>();
        Set<Character> s2 = new HashSet<>();
        Set<String> ss = new HashSet<>();
        int n = s.length();
        for(int i=0; i<n; ++i){
            s1.add(s.charAt(i));
            s2.add(t.charAt(i));
            ss.add(s.charAt(i) + " " + t.charAt(i));
       }
       return (s1.size() == s2.size()) && (ss.size() == s1.size());
    }
}

class Solution4 {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        if(n == 0)
            return true;
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<n; ++i){
            StringBuilder p = new StringBuilder();
            p.append(s.charAt(i));
            
            StringBuilder q = new StringBuilder();
            q.append(t.charAt(i));
            q.append(t.charAt(i));
            
            Integer x = map.put(p.toString(), i);
            Integer y = map.put(q.toString(), i);
            if(x == null && y == null)
                continue;
            if(x == null || y == null)
                return false;
    
            if(Integer.compare(x, y) != 0){
                return false;
            }
        }
        return true;
    }        
}