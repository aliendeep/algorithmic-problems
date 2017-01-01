/*
You are given a string, S, and a list of words, L, that are all of the same length.

Find all starting indices of substring(s) in S that is a concatenation of each 
word in L exactly once and without any intervening characters.

Example :

S: "barfoothefoobarman"
L: ["foo", "bar"]
You should return the indices: [0,9].
(order does not matter).
*/
public class Solution {
  public ArrayList<Integer> findSubstring(String s, final List<String> words) {
        ArrayList<Integer> result = new ArrayList<>();
        Map<String, Integer> cnt = new HashMap<>();
        for(String word : words){
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        int n = s.length();
        int nwords = words.size();
        int len = words.get(0).length();
        
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
