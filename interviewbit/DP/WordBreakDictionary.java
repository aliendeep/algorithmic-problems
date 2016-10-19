/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given

s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].
A solution is
 [ "cats and dog", "cat sand dog" ] 

Make sure the strings are sorted in your result.
*/
public class WordBreakDictionary {
    public ArrayList<String> bktk(String s, Map<String,  ArrayList<String>> dp,
                                    Set<String> dict){
        
        ArrayList<String> result = new ArrayList<>();
        // base case
        if(s.length() == 0){
            return result;    
        }
        
        // if full word is a dictionary word
        if(dict.contains(s)){
            result.add(s);
        }
            
        if(dp.containsKey(s)){
            return dp.get(s);
        }
        
        for(int i=0; i<s.length(); i++){
            String first = s.substring(0, i+1);
            if(dict.contains(first)){
                ArrayList<String> rest = bktk(s.substring(i+1), dp, dict);
                
                for(String next : rest){
                    result.add(first + " " + next);
                }            
            }
        }
        dp.put(s, result);
        return result;
    }
    
  public ArrayList<String> wordBreak(String s, ArrayList<String> dictionary) {
      Set<String> dict = new HashSet<>();
      for(String t : dictionary)
          dict.add(t);
          
      Map<String,  ArrayList<String>> dp = new HashMap<>();
      ArrayList<String> result = bktk(s, dp,  dict);
      Collections.sort(result);
      return result;
  }
}
