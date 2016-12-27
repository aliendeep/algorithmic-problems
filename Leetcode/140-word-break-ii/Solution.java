/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/
// Cleaner
public class Solution {
    // Memoization
    // index (0, i)
    Map<Integer, Set<String>> map;
    String s;
    int n;
    Set<String> wordDict;
    
    public Set<String> bktk(int k){
        Set<String> result = new HashSet<>();
        if(k <= 0){
            return result;
        }
        if(map.containsKey(k))
            return map.get(k);

        // whole word
        String sub = s.substring(0, k);
        if(wordDict.contains(sub)){
            result.add(sub);
        }

        for(int i=1; i<k; ++i){
            String end = s.substring(i, k);
            if(wordDict.contains(end)){
                Set<String> all = bktk(i);
                for(String str : all){
                    result.add(str + " " + end);
                }
            }            
        }
        map.put(k, result);
        return result;
    }
    
    public List<String> wordBreak(String s, Set<String> dict) {
        n = s.length();
        wordDict = dict;
        this.s = s;
        map = new HashMap<>();
        Set<String> r = bktk(n);
        return new ArrayList<>(r);
    }
}

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
