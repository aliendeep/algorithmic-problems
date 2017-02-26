/*
Given a string and a string dictionary, find the longest string in the dictionary 
that can be formed by deleting some characters of the given string. If there are 
more than one possible results, return the longest word with the smallest 
lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output: 
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output: 
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
*/
public class Solution {
    // check if s is a subsequence of t
    public  boolean isSubsequence(String s, String t) {
      if(s.length() > t.length())
          return false;
    
      if(s.length() == 0)
          return true;
    
      int curPos = 0;
      for(int i=0; i<t.length(); i++){
          if(s.charAt(curPos) == t.charAt(i))
              curPos++;
          if(curPos == s.length())
              return true;
      }  
      return false;
    }
  
  public String findLongestWord(String s, List<String> d) {
        Set<Character> set = new HashSet<>();
        for(char c : s.toCharArray())
            set.add(c);
        
        List<String> sorted = new ArrayList<>();            
        for(String w : d){
            boolean valid = true;
            for(int i=0; i<w.length(); ++i) {
                if(!set.contains(w.charAt(i))) {
                    valid = false;
                    break;
                }
            }
            if(valid)   sorted.add(w);
        }
        int n = sorted.size();
        if(n == 0)
            return "";
            
        Collections.sort(sorted, new Comparator<String>(){
            @Override
            public int compare(String x, String y){
                // larger length
                if(x.length() != y.length())
                    return Integer.compare(y.length(), x.length());
                // lexicographically smaller
                return x.compareTo(y);
            }
        });
        
        for(String w : sorted){
            if(isSubsequence(w, s))
                return w;
        }
        
        return "";
    }
}
