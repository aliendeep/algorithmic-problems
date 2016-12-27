/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a 
letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains 
lowercase letters separated by a single space.
*/

// Using one map
public class Solution {
    public boolean wordPattern(String pattern, String str) {
        int n = pattern.length();
        String[] s = str.split(" ");
        if(s.length != n)
            return false;
        Map<String, Integer> map = new HashMap<>();
        for(Integer i=0; i<n; ++i){
            StringBuilder p = new StringBuilder();
            p.append(pattern.charAt(i));
            
            // Appending double values of q as s[i] can already occur in the pattern
            StringBuilder q = new StringBuilder();
            q.append(s[i]);
            q.append(s[i]);

            if(map.put(p.toString(), i) != map.put(q.toString(), i)){
                return false;
            }
        }     
        return true;
   }
}

// Using two map
// Mapping character to index
class Solution2 {
    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        
        // Pattern: Index of the first occurrence of the string
        Map<Character, Integer> patternMap = new HashMap<>();
        for(int i=0; i<pattern.length(); i++){
            if(!patternMap.containsKey(pattern.charAt(i))){
                patternMap.put(pattern.charAt(i), i);
            }
        }
        
        // Str: Index of the first occurrence of the string
        Map<String, Integer> mapping = new HashMap<>();
        for(int i=0; i<strs.length; i++){
            if(!mapping.containsKey(strs[i])){
                mapping.put(strs[i], i);
            }
        }
        StringBuffer x = new StringBuffer();
        for(int i=0; i<pattern.length(); i++)
            x.append(patternMap.get(pattern.charAt(i)));
            
        StringBuffer y = new StringBuffer();
        for(int i=0; i<strs.length; i++)
            y.append(mapping.get(strs[i]));
            
        return x.toString().compareTo(y.toString()) == 0;
        
    }
}

// Using single set
class Solution3 {
    public boolean wordPattern(String pattern, String str) {
        int n = pattern.length();
        String[] s = str.split(" ");
        if(n != s.length)
            return false;

        Set<Character> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        Set<String> ss = new HashSet<>();
        for(int i=0; i<n; ++i){
            s1.add(pattern.charAt(i));
            s2.add(s[i]);
            ss.add(pattern.charAt(i) + " " + s[i]);
       }
       return (s1.size() == s2.size()) && (ss.size() == s1.size());
   }
}

// Simplest
class Solution4 {
    public boolean wordPattern(String pattern, String str) {
        int n = pattern.length();
        String[] s = str.split(" ");
        if(s.length != n)
            return false;
        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        for(Integer i=0; i<n; ++i){
            char c = pattern.charAt(i);
            if(map1.containsKey(c) && !map1.get(c).equals(s[i]))
                return false;
            map1.put(c, s[i]);            
            if(map2.containsKey(s[i]) && map2.get(s[i]) != c)
                return false;
            map2.put(s[i], c);            
        }     
        return true;
   }
}
