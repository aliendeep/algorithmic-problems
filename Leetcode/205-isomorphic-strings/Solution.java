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