/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/

public class Solution {
    int n;
    // <Word, all possible valid constructions of that word>
    Map<String, List<String>> wordMap;

    public List<String> bktk(String s, Set<String> wordDict){
        List<String> result = new ArrayList<>();

        if(s.length() == 0){
            return result;
        }
        
        if(wordMap.containsKey(s)){
            return wordMap.get(s);
        }
            
        // full word is a dictionary word
        if(wordDict.contains(s)){
            result.add(s);            
        }
        
        for(int i=1; i<=s.length(); i++){
            String first = s.substring(0, i);
            if(wordDict.contains(first)){
                String rest = s.substring(i);
                List<String> candidates = bktk(rest, wordDict);
                for(String str : candidates){
                    result.add(first + " " + str);
                }
            }
        }
        wordMap.put(s, result);
        return result;
    }
    
    public List<String> wordBreak(String s, Set<String> wordDict) {
        this.n = s.length();
        wordMap = new HashMap<>();
        return bktk(s, wordDict);
    }
}