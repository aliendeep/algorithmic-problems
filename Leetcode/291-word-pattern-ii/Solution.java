/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter 
in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.
*/

import java.util.*;


public class Solution {

    public boolean wordPatternMatch(String pattern, String str) {
        // pattern, string
        Map<Character, String> map = new HashMap<>();
        // to enfornce bijection
        Set<String> set = new HashSet<>();
        return match(pattern, 0, str, 0, map, set);
    }

    public boolean match(String pattern, int i, String str, int j, 
                            Map<Character, String> map, Set<String> set){
        int n = pattern.length();
        int m = str.length();
        // matched all character
        if(i == n && j == m)  return true;
        // only one of them reached end
        if(i == n || j == m)  return false;
        if(n - i > m - j)   
            return false;
        // Current pattern char
        char cur = pattern.charAt(i);
        // if we have matched the current pattern char to some string before
        if(map.containsKey(cur)){
            String s = map.get(cur);
            if(!str.startsWith(s, j))
                return false;
            return match(pattern, i+1, str, j+s.length(), map, set);
        }
        else{
            // try with all possible length of substring in str to match with
            // pattern
            for(int end = j+1; end<=m; ++end){
                String s = str.substring(j, end);
                // if there is already a bijection exists for s
                if(set.contains(s))
                    continue;
                map.put(cur, s);
                set.add(s);

                if(match(pattern, i+1, str, end, map, set))
                    return true;

                // revert
                map.remove(cur);                
                set.remove(s);
            }
            return false; 
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        // false
        System.out.println(s.wordPatternMatch("ab", "aa"));    
        // true
        System.out.println(s.wordPatternMatch("aba", "aaaa"));    
        // true
        System.out.println(s.wordPatternMatch("abab", "redblueredblue"));    
        // true
        System.out.println(s.wordPatternMatch("aaaa", "asdasdasdasd"));    
        // false
        System.out.println(s.wordPatternMatch("aabb", "xyzabcxzyabc"));    
        // true
        System.out.println(s.wordPatternMatch("itwasthebestoftimes", "ittwaastthhebesttoofttimes"));    
        // false
        System.out.println(s.wordPatternMatch("itwasthebestoftimes", "ittwaastthhebesttoofttimesss"));    
    }
}

// Bruteforce
class Solution2 {
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
}

class Solution3 {
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
    public boolean bktk(StringBuilder str, int start, int lev, List<String> cur){
        if(lev < 0)     return false;
        if(lev >= 0){            
            if(!wordPattern(cur))
                return false;
            // Divided into n parts
            if(lev == 0 && start == str.length())
                return true;
        }
        for(int i=start; i<str.length(); i++){
            String t = str.substring(start, i+1);
            cur.add(t);
            if(bktk(str, i+1, lev-1, cur))
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
        StringBuilder s = new StringBuilder(str);
        List<String> cur = new ArrayList<>();
        return bktk(s, 0, n, cur);
    }
}
