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