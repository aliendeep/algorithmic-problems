/*
You are given a string, s, and a list of words, words, that are all of the same 
length. Find all starting indices of substring(s) in s that is a concatenation 
of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
*/
public class Solution {
    Map<String, Integer> dict;
    int wordSize, numWords;
    String s;
    
    boolean match(int start){
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        int offset = start;
        for(int i=0; i<numWords; i++) {
            String nextWord = s.substring(offset, offset + wordSize);
            offset += wordSize;

            if(!dict.containsKey(nextWord))     return false;
            cnt.put(nextWord, cnt.getOrDefault(nextWord, 0) + 1);
            if(cnt.get(nextWord) > dict.get(nextWord))
                return false;
        }
        return true;
    }
    
    public List<Integer> findSubstring(String s, String[] words) {
        // List of words, words, that are all of the same length. 
        // One word can contain multiple times
        dict = new HashMap<String, Integer>();
        for(String word : words) {
            dict.put(word, dict.getOrDefault(word, 0) + 1);
        }
        
        int n = s.length();
        wordSize = words[0].length();
        numWords = words.length;
        this.s = s;
        int windowLength = wordSize*numWords;
        // for all starting index
        List<Integer> r = new ArrayList<>();
        for(int i=0;  i + windowLength <= n; i++) {
            if(match(i)) {
                r.add(i);   
            }
        }
        return r;
    }
}

class Solution2 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> cnt = new HashMap<>();
        for(String word : words){
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        int n = s.length();
        int nwords = words.length;
        int len = words[0].length();
        
        for(int i=0; i<n - nwords*len + 1; ++i){
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while(j < nwords){
                String cur = s.substring(i+j*len, i+j*len+len);
                // The word doesn't contain in the list
                if(!cnt.containsKey(cur))
                    break;
                seen.put(cur, seen.getOrDefault(cur, 0) + 1);
                // Extra word
                if(cnt.get(cur) < seen.get(cur)){
                    break;                
                }
                j++;
            }
            // Found all the words
            if(j == nwords){
                result.add(i);
            }
        }
        return result;
    }
}
