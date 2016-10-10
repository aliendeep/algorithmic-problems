/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.
*/

import java.util.*;
public class Solution {
    String patternIndex; 

    public boolean wordPattern(List<String> strs) { 
        // Str: Index of the first occurrence of the string
        Map<String, Integer> mapping = new HashMap<>();
        for(int i=0; i<strs.size(); i++){
            if(!mapping.containsKey(strs.get(i))){
                mapping.put(strs.get(i), i);
            }
        }

        StringBuilder y = new StringBuilder();
        for(int i=0; i<strs.size(); i++)
            y.append(mapping.get(strs.get(i)));

        return patternIndex.startsWith(y.toString());
    }
    public boolean bktk(String str, int lev, int n, List<String> cur){
        if(lev > 0 && lev <= n){
            if(!wordPattern(cur))
                return false;
            // Divided into n parts
            if(lev == n && str.isEmpty())
                return true;
        }
        if(lev > n)         return false;
        for(int i=0; i<str.length(); i++){
            String t = str.substring(0, i+1);
            cur.add(t);
            if(bktk(str.substring(i+1), lev+1, n, cur))
                return true;
            cur.remove(cur.size()-1);
        }
        return false;
    }
    public boolean wordPatternMatch(String pattern, String str) {
        int n = pattern.length();
        if(n == 0)      return str.length() == 0;
        // Pattern: Index of the first occurrence of the string
        Map<Character, Integer> patternMap = new HashMap<>();
        for(int i=0; i<pattern.length(); i++){
            if(!patternMap.containsKey(pattern.charAt(i))){
                patternMap.put(pattern.charAt(i), i);
            }
        }
        
        StringBuilder patternInd= new StringBuilder();
        for(int i=0; i<pattern.length(); i++)
            patternInd.append(patternMap.get(pattern.charAt(i)));
        
        patternIndex = patternInd.toString();
        List<String> cur = new ArrayList<>();
        return bktk(str, 0, n, cur);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.wordPatternMatch("abab", "redblueredblue"));    
        System.out.println(s.wordPatternMatch("aaaa", "asdasdasdasd"));    
        System.out.println(s.wordPatternMatch("aabb", "xyzabcxzyabc"));    
        System.out.println(s.wordPatternMatch("itwasthebestoftimes", "ittwaastthhebesttoofttimes"));    
        System.out.println(s.wordPatternMatch("itwasthebestoftimes", "ittwaastthhebesttoofttimesss"));    
    }
}