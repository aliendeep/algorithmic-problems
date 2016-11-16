/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
*/
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