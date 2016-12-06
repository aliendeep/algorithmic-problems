/*
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your 
solution to such case?
*/
public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
            
        Map<Character, Integer> mapping = new HashMap<Character, Integer>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(!mapping.containsKey(c))
                mapping.put(c, 1);   
            else
                mapping.put(c, mapping.get(c)+1);   
        }
        for(int i=0; i<t.length(); i++){
            char c = t.charAt(i);
            if(!mapping.containsKey(c))
                return false;
            mapping.put(c, mapping.get(c)-1);   
        }
        
        for(Map.Entry<Character, Integer> entry : mapping.entrySet()){
            if(entry.getValue() > 0)
                return false;
        }
        return true;
    }
}

    // Alternative : Sorting
class Solution2 {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        
        int n = s.length();
        char[] s1 =  s.toCharArray();
        Arrays.sort(s1);

        char[] t1 =  t.toCharArray();
        Arrays.sort(t1);
        
        for(int i=0; i<n; i++)
            if(s1[i] != t1[i])
                return false;
        return true;
    }
}

class Solution3 {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        int n = s.length();
        int[] cnt = new int[256];        
        for(int i=0; i<n; ++i){
            cnt[s.charAt(i)-'0']++;    
        }
        for(int i=0; i<n; ++i){
            cnt[t.charAt(i)-'0']--;    
        }
        
        for(int i=0; i<256; ++i)
            if(cnt[i] > 0)
                return false;
        return true;
    }
}