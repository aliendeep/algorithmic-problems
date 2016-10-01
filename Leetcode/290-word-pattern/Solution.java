public class Solution {
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