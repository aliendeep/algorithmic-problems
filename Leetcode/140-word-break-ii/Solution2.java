public class Solution {
    // Memoization
    // Word, all possible valid constructions of that word
    Map<String, List<String>> wordMap;
    
    public List<String> wordBreakHelper(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<>();
        if(s.length() == 0)
            return result;
        if(wordMap.containsKey(s))
            return wordMap.get(s);
        
        // full word is a dictionary word
        if(wordDict.contains(s)){
            result.add(s);
        }
        
        for(int i=1; i<s.length(); i++){
            // if the rest of the strinsg starting from i is a valid dictionary word
            String rest = s.substring(i);
            if(wordDict.contains(rest)){
                String prevWord = s.substring(0, i);
                List<String> prev = wordBreakHelper(prevWord, wordDict);
                for(String str : prev){
                    result.add(str + " " + rest);
                }
            }    
        }
        
        wordMap.put(s, result);
        return result;            
    }
    
    public List<String> wordBreak(String s, Set<String> wordDict) {
        wordMap = new HashMap<String, List<String>>();
        return wordBreakHelper(s, wordDict);
    }
}