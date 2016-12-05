/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
*/

public class Solution {
    boolean match(String s, int start, Map<String, Integer> dict , int wordSize, int numWords){
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        for(int i=0; i<numWords; i++){
            String word = s.substring(start+i*wordSize, start+i*wordSize+wordSize);
            if(!dict.containsKey(word))
                return false;
                
            if(cnt.containsKey(word))
                cnt.put(word, cnt.get(word)+1);
            else
                cnt.put(word, 1);
                
            if(cnt.get(word) > dict.get(word))
                return false;
        }
        return true;
    }
    
    public List<Integer> findSubstring(String s, String[] words) {
        // List of words, words, that are all of the same length. 
        // One word can contain multiple times
        Map<String, Integer> dict = new HashMap<String, Integer>();
        for(String word : words){
            if(dict.containsKey(word)){
                dict.put(word, dict.get(word)+1);
            }
            else
                dict.put(word, 1);
        }
        
        int wordSize = words[0].length();
        List<Integer> r = new ArrayList<>();
        // for all starting index
        for(int i=0;  i+wordSize*words.length <= s.length(); i++){
            if(match(s, i, dict, wordSize, words.length)){
                r.add(i);    
            }
        }
        return r;
    }
}